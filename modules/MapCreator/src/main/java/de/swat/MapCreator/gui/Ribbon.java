package de.swat.MapCreator.gui;

import com.google.common.collect.*;
import de.swat.annotations.*;
import de.swat.datamodels.RibbonDataModel;
import de.swat.enums.*;
import de.swat.exceptions.DataModelException;
import de.swat.utils.LookupUtil;
import org.pushingpixels.flamingo.api.ribbon.*;
import org.pushingpixels.flamingo.api.ribbon.resize.*;

import javax.swing.*;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Hier wird der Ribbon zusammengebastelt.
 * Er holt sich die Buttons aus den Klassen,
 * die mit @RibbonComponent() annotiert wurden.
 * Die Buttons besitzen hier allerdings
 * noch keine ActionListener, diese werden erst
 * vom Parent hinzugefügt
 *
 * @author W. Glanzer, 05.12.13
 */
public class Ribbon extends JRibbon
{
  public List<ComponentContainer> buttons = new ArrayList<>();

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
    ArrayListMultimap<ERibbonCategory, ComponentContainer> commandButtons = _getRibbonComponents();
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
  private List<RibbonTask> _getRibbonTasks(ArrayListMultimap<ERibbonCategory, ComponentContainer> pCommandButtons)
  {
    List<RibbonTask> returnList = new ArrayList<>();

    for (ERibbonCategory currCategory : pCommandButtons.keySet())
    {
      List<JRibbonBand> ribbonBands = new ArrayList<>();
      ArrayListMultimap<ERibbonSubCategory, ComponentContainer> ribbonBandSubCategories = ArrayListMultimap.create();
      for (ComponentContainer currCommandButton : pCommandButtons.get(currCategory))
      {
        ribbonBandSubCategories.put(currCommandButton.subCategory, currCommandButton);
        buttons.add(currCommandButton);
      }
      TreeMultimap<ERibbonSubCategory, ComponentContainer> sortedSubCategories = _sortSubcategoriesByOrdinal(ribbonBandSubCategories);

      //Hier werden die RibbonBands erzeugt, mit Buttons versehen, der RibbonTask erzeugt und zur returnList hinzugefügt
      for (ERibbonSubCategory currSubCategory : sortedSubCategories.keySet())
      {
        //JRibbonBand initialisieren und ResizePolicy hinzufügen
        JRibbonBand ribbonBand = new JRibbonBand(currSubCategory.name(), null);
        List<RibbonBandResizePolicy> result = new ArrayList<>();
        result.add(new CoreRibbonResizePolicies.None(ribbonBand.getControlPanel()));
        ribbonBand.setResizePolicies(result);

        Collection<ComponentContainer> commandButtonsToAdd = ribbonBandSubCategories.asMap().get(currSubCategory);
        List<ComponentContainer> sortedCommandButtons = _sortListByComponentID(commandButtonsToAdd);
        for (ComponentContainer currButton : sortedCommandButtons)
        {
          ERibbonComponentType componentType = currButton.componentType;
          ribbonBand.addRibbonComponent(_getComponentFromEnum(componentType, currButton), currButton.rowspan);
        }

        ribbonBands.add(ribbonBand);
      }

      JRibbonBand[] ribbonTasks = ribbonBands.toArray(new JRibbonBand[ribbonBands.size()]);
      RibbonTask task = new RibbonTask(currCategory.name(), ribbonTasks);
      returnList.add(task);
    }

    return returnList;
  }

  private List<ComponentContainer> _sortListByComponentID(Collection<ComponentContainer> pComponentContainers)
  {
    List<ComponentContainer> componentContainers = new ArrayList<>(pComponentContainers);
    Collections.sort(componentContainers, new Comparator<ComponentContainer>()
    {
      @Override
      public int compare(ComponentContainer o1, ComponentContainer o2)
      {
        return (o1.ID - o2.ID);
      }
    });
    return componentContainers;
  }

  private TreeMultimap<ERibbonSubCategory, ComponentContainer> _sortSubcategoriesByOrdinal(ArrayListMultimap<ERibbonSubCategory, ComponentContainer> pContainers)
  {
    TreeMultimap<ERibbonSubCategory, ComponentContainer> returnMap = TreeMultimap.create();
    returnMap.putAll(pContainers);
    return returnMap;
  }

  /**
   * Liefert ein JRibbonComonent, das durch
   * den RibbonComponentType und den ComponentContainer
   * bestimmt ist.
   *
   * @param pType      RibbonComponentType
   * @param pComponent Komponente
   * @return JRibbonComponent
   */
  private JRibbonComponent _getComponentFromEnum(ERibbonComponentType pType, ComponentContainer pComponent)
  {
    JRibbonComponent component = null;

    switch (pType != null ? pType : ERibbonComponentType.JCommandButton)
    {
      default:
        component = new JRibbonComponent(pComponent.component);
        break;
    }

    return component;
  }

  /**
   * Gibt alle Components zurück, die mit "RibbonComponent"
   * annotiert wurden. Sie werden auch gleich nach Kategorie
   * sortiert in einer ArrayListMultimap abgelegt
   *
   * @return ArrayListMultimap mit JCommandButtons, sortiert nach
   * Kategorie
   */
  private ArrayListMultimap<ERibbonCategory, ComponentContainer> _getRibbonComponents()
  {
    ArrayListMultimap<ERibbonCategory, ComponentContainer> allCommandButtons = ArrayListMultimap.create();
    RibbonDataModel dataModelInstance = new RibbonDataModel();
    Field[] fields = dataModelInstance.getClass().getDeclaredFields();
    for (Field currField : fields)
    {
      try
      {
        RibbonComponent annotation = currField.getAnnotation(RibbonComponent.class);
        if (annotation != null)
        {
          ERibbonCategory category = annotation.category();
          ERibbonSubCategory subCategory = annotation.subcategory();
          ERibbonComponentType type = ERibbonComponentType.valueOf(currField.getType().getSimpleName());
          String commandButtonName = currField.getName();
          int posID = annotation.posID();
          int rowspan = annotation.rowspan();
          Object object = currField.get(dataModelInstance);
          if (object != null && object instanceof JComponent)
          {
            JComponent jCommandButton = (JComponent) (object);
            ComponentContainer container = new ComponentContainer(jCommandButton, type, subCategory, commandButtonName, posID, rowspan);
            allCommandButtons.put(category, container);
          }
        }
      }
      catch (IllegalAccessException e)
      {
        throw new DataModelException("Error while loading dataModel (Ribbon)", e);
      }
      catch (IllegalArgumentException e)
      {
        throw new DataModelException("Not supported componentType found in RibbonDataModel (" + currField.getType().getSimpleName() + ")", e);
      }
    }

    //noinspection UnusedAssignment
    dataModelInstance = null;

    return allCommandButtons;
  }
}
