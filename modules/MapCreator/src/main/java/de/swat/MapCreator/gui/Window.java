package de.swat.mapCreator.gui;

import de.swat.common.map.Map;
import de.swat.constants.IRibbonConstants;
import de.swat.constants.IVersion;
import de.swat.constants.IWindowConstants;
import de.swat.datamodels.PropertySheet;
import de.swat.mapCreator.gui.drawContainer.DrawContainer;
import de.swat.mapCreator.gui.ribbon.Ribbon;
import de.swat.mapCreator.gui.ribbon.applicationMenu.SendMapAction;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenu;

import javax.swing.*;
import java.awt.*;

/**
 * GUI-Implementierung
 *
 * @author W. Glanzer, 28.11.2013
 */
public class Window extends JFrame
{
  /*GUI-Komponenten*/
  private DrawContainer drawContainer;
  private Ribbon ribbon;

  public Window(Map pMap)
  {
    drawContainer = new DrawContainer(pMap);
    ribbon = new Ribbon();

    setSize(new Dimension(IWindowConstants.WINDOW_WIDTH, IWindowConstants.WINDOW_HEIGHT));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setTitle(IVersion.MAPCREATOR_TITLE);

    _configureRibbonApplicationMenu();
    _addComponents();

    SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        setVisible(true);
      }
    });
  }

  private void _addComponents()
  {
    int dividerSize = 3; //TODO

    /*Ribbon*/
    JPanel ribbonPanel = new JPanel(null);
    ribbon.setLocation(0, -28);
    ribbon.setSize(IWindowConstants.WINDOW_WIDTH, (IRibbonConstants.BUTTON_SIZE + 28 + 17) - ribbon.getY());
    ribbonPanel.add(ribbon);
    ribbonPanel.setPreferredSize(new Dimension(0, ribbon.getSize().height + ribbon.getY()));
    add(ribbonPanel, BorderLayout.NORTH);

    JTabbedPane trees = new JTabbedPane();
    trees.addTab("Dummy-Tree1", _getDummyTree());
    trees.addTab("Dummy-Tree2", _getDummyTree());

    /*Kombination aus JTree und JYPropertySheet*/
    final JSplitPane splitTree_Properties = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitTree_Properties.setDividerSize(dividerSize);
    splitTree_Properties.setTopComponent(trees);
    splitTree_Properties.setBottomComponent(_getDummyPropertySheet().getComponent());
    splitTree_Properties.setResizeWeight(0.5); //mittig

    /*Über-SplitPane, hierauf sind rechts der drawContainer, links die SplitTree_Properties*/
    JSplitPane splitContainer_Properties = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitContainer_Properties.setDividerLocation(300); //MagicNumber: 300 sieht gut aus
    splitContainer_Properties.setDividerSize(dividerSize);
    splitContainer_Properties.setRightComponent(drawContainer);
    splitContainer_Properties.setLeftComponent(splitTree_Properties);

    add(splitContainer_Properties, BorderLayout.CENTER);

  }

  /**
   * Konfiguriert das ApplicationMenu und fügt es dem Ribbon hinzu
   */
  private void _configureRibbonApplicationMenu()
  {
    RibbonApplicationMenu applicationMenu = new RibbonApplicationMenu();

    applicationMenu.addMenuEntry(new SendMapAction());

    ribbon.setApplicationMenu(applicationMenu);
    ribbon.putClientProperty("ribbon.hideSecondary", true);
    ribbon.putClientProperty("ribbon.sizePrimary", 300);
  }

  public void mapChanged(Map pNewMap)
  {
    drawContainer.mapChanged(pNewMap);
  }

  private JTree _getDummyTree()
  {
    return new JTree();
  }

  private PropertySheet _getDummyPropertySheet()
  {
    return new PropertySheet();
  }

  public DrawContainer getDrawContainer()
  {
    return drawContainer;
  }

}
