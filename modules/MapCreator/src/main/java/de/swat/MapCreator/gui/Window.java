package de.swat.MapCreator.gui;

import de.swat.*;
import de.swat.MapCreator.gui.DrawContainer.DrawContainer;
import de.swat.constants.IWindowConstants;
import de.swat.dataModels.Map.StructureCollisionObjectDataModel;
import de.swat.enums.ERibbonComponentType;
import de.swat.utils.ImageUtil;
import org.pushingpixels.flamingo.api.common.JCommandButton;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * GUI-Implementierung
 *
 * @author W. Glanzer, 28.11.2013
 */
public class Window extends JFrame
{
  private static final String TITLE = "S.W.4.T - MapCreator - Version 1.0.0";
  private final Map map;
  /*GUI-Komponenten*/
  private DrawContainer drawContainer;
  private Ribbon ribbon;
  private JSpinner xOffSpinner;
  private JSpinner yOffSpinner;

  public Window(Map pMap)
  {
    map = pMap;
    drawContainer = new DrawContainer(pMap);
    ribbon = new Ribbon();

    setSize(new Dimension(IWindowConstants.WINDOW_WIDTH, IWindowConstants.WINDOW_HEIGHT));
    setLocationRelativeTo(null);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setResizable(false);
    setTitle(TITLE);

    _addComponents();
    _addActionListenersToRibbonButtons();

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
    add(ribbon, BorderLayout.NORTH);

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

  private JTree _getDummyTree()
  {
    return new JTree();
  }

  private PropertySheet _getDummyPropertySheet()
  {
    return new PropertySheet();
  }

  /**
   * Hier werden alle Buttons, wenn möglich,
   * mit einem ActionListener versehen.
   */
  private void _addActionListenersToRibbonButtons()
  {
    for (ComponentContainer currButton : ribbon.buttons)
    {
      String buttonName = currButton.name;
      JComponent component = currButton.component;

      if ((currButton.componentType == ERibbonComponentType.JCommandButton || currButton.componentType == ERibbonComponentType.JSpinner))
        switch (buttonName == null ? "" : buttonName)
        {
          //FinishPoly-Button
          case "finishPoly":
            JCommandButton finishPoly = (JCommandButton) component;
            finishPoly.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                ArrayList<Point> clickedPoints = drawContainer.getClickedPoints();
                if (clickedPoints.size() > 1)
                {
                  map.addPoints(clickedPoints);
                  StructureCollisionObjectDataModel newObject = map.finishStructure();
                  drawContainer.addStructureObject(newObject);
                  repaint();
                }
              }
            });
            break;

          case "setBackground":
            JCommandButton setBackground = (JCommandButton) component;
            setBackground.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(Window.this);

                if (returnValue == JFileChooser.APPROVE_OPTION)
                {
                  BufferedImage image = ImageUtil.loadFileAsImage(fileChooser.getSelectedFile());
                  drawContainer.setBackgroundimage(image, false);
                }
              }
            });
            break;

          case "xOff":
            xOffSpinner = (JSpinner) component;
            xOffSpinner.addChangeListener(new ChangeListener()
            {
              @Override
              public void stateChanged(ChangeEvent e)
              {
                drawContainer.setXOff((Integer) xOffSpinner.getValue(), false);
              }
            });
            break;

          case "yOff":
            yOffSpinner = (JSpinner) component;
            yOffSpinner.addChangeListener(new ChangeListener()
            {
              @Override
              public void stateChanged(ChangeEvent e)
              {
                drawContainer.setYOff((Integer) yOffSpinner.getValue(), false);
              }
            });
            break;

          case "saveMap":
            JCommandButton saveMap = (JCommandButton) component;
            ActionListener saveAction = new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                final JFileChooser fileChooser = new JFileChooser();
                drawContainer.setBlocked(true);
                fileChooser.addActionListener(new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                    //fileChooser.removeActionListener(this);
                    //if (fileChooser.getSelectedFile() != null)
                    //  SaveUtil.save(dataModel, fileChooser.getSelectedFile().getPath());
                    //drawContainer.setBlocked(false);
                    throw new RuntimeException("Not implemented yet!");
                  }
                });
                fileChooser.showOpenDialog(Window.this);
              }
            };
            saveMap.addActionListener(saveAction);
            break;

          case "loadMap":
            JCommandButton loadMap = (JCommandButton) component;
            ActionListener loadAction = new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                final JFileChooser fileChooser = new JFileChooser();
                drawContainer.setBlocked(true);
                fileChooser.addActionListener(new ActionListener()
                {
                  @Override
                  public void actionPerformed(ActionEvent e)
                  {
                    fileChooser.removeActionListener(this);
                    if (fileChooser.getSelectedFile() != null)
                    {
                      //MapDataModel newDataModel = SaveUtil.load(fileChooser.getSelectedFile().getPath());
                      //map.setNewModel(newDataModel);
                      //drawContainer.reloadFromDataModel();
                      //drawContainer.setBlocked(false);
                      //repaint();
                      throw new RuntimeException("Not implemented yet!");
                    }
                    drawContainer.setBlocked(false);
                  }
                });
                fileChooser.showOpenDialog(Window.this);
              }
            };
            loadMap.addActionListener(loadAction);
            break;

          case "clearPoints":
            JCommandButton clearPoints = (JCommandButton) component;
            clearPoints.addActionListener(new ActionListener()
            {
              @Override
              public void actionPerformed(ActionEvent e)
              {
                drawContainer.clearAll();
                drawContainer.repaint();
              }
            });
            break;

          default:
            System.out.println("No actionListener known to this component '" + buttonName + "'"); //TODO
            break;
        }
    }
  }

  /**
   * Updated die Values der JSpinner für
   * das xOffset und yOffset. Ihnen wird
   * der aktuelle Wert der <code>xOff</code> und
   * <code>yOff</code> gegeben.
   */
  public void updateSpinner()
  {
    xOffSpinner.setValue(drawContainer.xOff);
    yOffSpinner.setValue(drawContainer.yOff);
  }

  public DrawContainer getDrawContainer()
  {
    return drawContainer;
  }
}
