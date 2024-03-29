package de.swat.mapCreator.gui;

import de.swat.common.map.Map;
import de.swat.constants.IRibbonConstants;
import de.swat.constants.IVersion;
import de.swat.constants.IWindowConstants;
import de.swat.mapCreator.gui.components.MapTree;
import de.swat.mapCreator.gui.components.drawContainer.DrawContainer;
import de.swat.mapCreator.gui.ribbon.Ribbon;

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
  private Map map;
  private MapTree mapTree;

  public Window(Map pMap)
  {
    map = pMap;
    drawContainer = new DrawContainer(pMap);
    ribbon = new Ribbon();

    setSize(new Dimension(IWindowConstants.WINDOW_WIDTH, IWindowConstants.WINDOW_HEIGHT));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setTitle(IVersion.MAPCREATOR_TITLE);

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

    /*Kombination aus JTree und JYPropertySheet*/
    mapTree = new MapTree(map);
    final JSplitPane splitTree_Properties = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
    splitTree_Properties.setDividerSize(dividerSize);
    splitTree_Properties.setTopComponent(mapTree);
    splitTree_Properties.setBottomComponent(new JPanel());
    splitTree_Properties.setResizeWeight(0.5); //mittig

    /*Über-SplitPane, hierauf sind rechts der drawContainer, links die SplitTree_Properties*/
    JSplitPane splitContainer_Properties = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
    splitContainer_Properties.setDividerLocation(300); //MagicNumber: 300 sieht gut aus
    splitContainer_Properties.setDividerSize(dividerSize);
    splitContainer_Properties.setRightComponent(drawContainer);
    splitContainer_Properties.setLeftComponent(splitTree_Properties);

    add(splitContainer_Properties, BorderLayout.CENTER);
  }

  public void mapChanged(Map pNewMap)
  {
    map = pNewMap;
    drawContainer.mapChanged(pNewMap);
    mapTree.mapChanged(pNewMap);
  }

  public DrawContainer getDrawContainer()
  {
    return drawContainer;
  }

}
