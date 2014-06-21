package de.swat.mapCreator.gui.ribbon;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.TreeMultimap;
import de.swat.constants.IRibbonConstants;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.mapCreator.MapCreator;
import de.swat.mapCreator.ribbon.IRibbonAction;
import de.swat.mapCreator.ribbon.RibbonAction;
import de.swat.utils.LookupUtil;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.api.ribbon.resize.CoreRibbonResizePolicies;
import org.pushingpixels.flamingo.api.ribbon.resize.RibbonBandResizePolicy;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

/**
 * Hier wird der Ribbon zusammengebastelt.
 * Er holt sich die Buttons aus den Klassen,
 * die mit @RibbonAction() annotiert wurden.
 * Die Buttons besitzen hier allerdings
 * noch keine ActionListener, diese werden erst
 * vom Parent hinzugefügt
 *
 * @author W. Glanzer, 05.12.13
 */
public class Ribbon extends JRibbon
{
  public List<IRibbonAction> actions = new ArrayList<>();

  public Ribbon()
  {
    _createRibbon();
  }

  /**
   * Erstellt den Ribbon. Hierzu wird _getRibbonComponents,
   * _getRibbonTasks und addTask aufgerufen.
   */
  private void _createRibbon()
  {
    //Alle CommandButtons holen, die mit Annotation markiert wurden
    ArrayListMultimap<ERibbonCategory, IRibbonAction> commandButtons = _getRibbonComponents();
    //Aus diesen CommandButtons nun RibbonTasks erstellen, die zum Ribbon hinzugefügt werden können
    List<RibbonTask> ribbonTasks = _getRibbonTasks(commandButtons);
    for (RibbonTask currTask : ribbonTasks)
      addTask(currTask);
  }

  /**
   * Liefert die fertigen RibbonTasks anhand einer
   * ArrayListMultiMap.
   *
   * @param pCommandButtons ArrayListMultimap mit Componentkategorie
   *                        und dem ButtonContainer, der wiederrum
   *                        die Subkategorie und den Button
   *                        enthält
   * @return Liste aus RibbonTasks
   */
  private List<RibbonTask> _getRibbonTasks(ArrayListMultimap<ERibbonCategory, IRibbonAction> pCommandButtons)
  {
    List<RibbonTask> returnList = new ArrayList<>();

    for (ERibbonCategory currCategory : pCommandButtons.keySet())
    {
      List<JRibbonBand> ribbonBands = new ArrayList<>();
      ArrayListMultimap<ERibbonSubCategory, IRibbonAction> ribbonBandSubCategories = ArrayListMultimap.create();

      for (IRibbonAction currAction : pCommandButtons.get(currCategory))
      {
        ribbonBandSubCategories.put(currAction.getSubCategory(), currAction);
        actions.add(currAction);
      }
      TreeMultimap<ERibbonSubCategory, IRibbonAction> sortedSubCategories = _sortSubcategoriesByOrdinal(ribbonBandSubCategories);

      //Hier werden die RibbonBands erzeugt, mit Buttons versehen, der RibbonTask erzeugt und zur returnList hinzugefügt
      for (ERibbonSubCategory currSubCategory : sortedSubCategories.keySet())
      {
        //JRibbonBand initialisieren und ResizePolicy hinzufügen
        JRibbonBand ribbonBand = new JRibbonBand(currSubCategory.getDisplayName(), null);
        List<RibbonBandResizePolicy> result = new ArrayList<>();
        result.add(new CoreRibbonResizePolicies.None(ribbonBand.getControlPanel()));
        ribbonBand.setResizePolicies(result);

        Collection<IRibbonAction> commandButtonActions = ribbonBandSubCategories.asMap().get(currSubCategory);  //null kann hier nicht vorkommen
        List<IRibbonAction> sortedCommandButtonActions = _sortListByComponentID(commandButtonActions);

        for (IRibbonAction currAction : sortedCommandButtonActions)
          ribbonBand.addCommandButton(_getCommandButtonFromRibbonAction(currAction), RibbonElementPriority.TOP);

        //ribbonBand.setMinimumSize(new Dimension(800, 120));

        ribbonBands.add(ribbonBand);
      }

      JRibbonBand[] ribbonTasks = ribbonBands.toArray(new JRibbonBand[ribbonBands.size()]);
      RibbonTask task = new RibbonTask(currCategory.getDisplayName(), ribbonTasks);
      task.setPosition(currCategory.getPosition());
      returnList.add(task);
    }

    _sortTasksByName(returnList);
    return returnList;
  }

  private void _sortTasksByName(List<RibbonTask> pTasks)
  {
    Collections.sort(pTasks, new Comparator<RibbonTask>()
    {
      @Override
      public int compare(RibbonTask o1, RibbonTask o2)
      {
        return o1.getPosition() - o2.getPosition();
      }
    });
  }

  private List<IRibbonAction> _sortListByComponentID(Collection<IRibbonAction> pComponentContainers)
  {
    List<IRibbonAction> componentContainers = new ArrayList<>(pComponentContainers);
    Collections.sort(componentContainers, new Comparator<IRibbonAction>()
    {
      @Override
      public int compare(IRibbonAction o1, IRibbonAction o2)
      {
        return (o1.getPosition() - o2.getPosition());
      }
    });
    return componentContainers;
  }

  private TreeMultimap<ERibbonSubCategory, IRibbonAction> _sortSubcategoriesByOrdinal(ArrayListMultimap<ERibbonSubCategory, IRibbonAction> pContainers)
  {
    Comparator<ERibbonSubCategory> compKeys = new Comparator<ERibbonSubCategory>()
    {
      @Override
      public int compare(ERibbonSubCategory o1, ERibbonSubCategory o2)
      {
        return o1.getPosition() - o2.getPosition();
      }
    };

    Comparator<IRibbonAction> compValues = new Comparator<IRibbonAction>()
    {
      @Override
      public int compare(IRibbonAction o1, IRibbonAction o2)
      {
        return 0;
      }
    };

    TreeMultimap<ERibbonSubCategory, IRibbonAction> returnMap = TreeMultimap.create(compKeys, compValues);
    returnMap.putAll(pContainers);
    return returnMap;
  }

  private JCommandButton _getCommandButtonFromRibbonAction(final IRibbonAction pAction)
  {
    ResizableIcon icon = pAction.getIcon();
    String title = pAction.getTitle();
    final JCommandButton commandButton = new JCommandButton(title, icon == null ? new EmptyResizableIcon(IRibbonConstants.ICON_SIZE) : icon);
    JRibbonComponent component = new JRibbonComponent(commandButton);
    commandButton.setPreferredSize(new Dimension(IRibbonConstants.BUTTON_SIZE, IRibbonConstants.BUTTON_SIZE));
    commandButton.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        pAction.actionPerformed(e, commandButton, MapCreator.getMapCreatorImage());
      }
    });
    return commandButton;
  }

  /**
   * Gibt alle Components zurück, die mit "RibbonAction"
   * annotiert wurden. Sie werden auch gleich nach Kategorie
   * sortiert in einer ArrayListMultimap abgelegt
   *
   * @return ArrayListMultimap mit JCommandButtons, sortiert nach
   *         Kategorie
   */
  private ArrayListMultimap<ERibbonCategory, IRibbonAction> _getRibbonComponents()
  {
    ArrayListMultimap<ERibbonCategory, IRibbonAction> allCommandButtons = ArrayListMultimap.create();

    Set<IRibbonAction> ribbonActions = getChildren();
    for (IRibbonAction currRibbonAction : ribbonActions)
    {
      ERibbonCategory category = currRibbonAction.getCategory();
      allCommandButtons.put(category, currRibbonAction);
    }

    return allCommandButtons;
  }

  /**
   * @return Liefert alle Buttons des Ribbons
   */
  public Set<IRibbonAction> getChildren()
  {
    Set<IRibbonAction> children = new HashSet<>();
    Set<Class<?>> classesByAnnotation = LookupUtil.getClassByAnnotation(RibbonAction.class, "de.swat.MapCreator.ribbon.actions");
    for (Class<?> currClass : classesByAnnotation)
    {
      try
      {
        Object instance = currClass.newInstance();
        if(instance instanceof IRibbonAction)
        {
          children.add((IRibbonAction) instance);
        }
        //Sichergehen, dass die instanz wieder verworfen wird
        //noinspection UnusedAssignment
        instance = null;
      }
      catch (InstantiationException | IllegalAccessException e)
      {
        e.printStackTrace();
      }
    }

    return children;
  }

}
