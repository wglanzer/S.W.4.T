package de.swat.datamodels.models;

import de.swat.annotationProcessors.annotations.DataModel;
import de.swat.annotationProcessors.annotations.RibbonAction;
import de.swat.datamodels.IRibbonAction;
import de.swat.utils.LookupUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Datenmodell zum Ribbon des MapCreators
 *
 * @author W. Glanzer, 06.12.13
 */
@SuppressWarnings({"UnusedDeclaration", "SuspiciousNameCombination"})
@DataModel
public class RibbonDataModel extends SimpleDataModel
{
  public int buttonWidth = 75;

  private Set<IRibbonAction> children;

  public Set<IRibbonAction> getChildren()
  {
    if (children == null)
    {
      children = new HashSet<>();
      Set<Class<?>> classesByAnnotation = LookupUtil.getClassByAnnotation(RibbonAction.class, "de.swat.datamodels.ribbonactions");
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

    }

    return children;
  }

  public void setChildren(Set<IRibbonAction> pChildren)
  {
    children = pChildren;
  }

  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Polygon,
  //              posID = 0)
  //public JCommandButton finishPoly = new JCommandButton("Finish Polygon")
  //{
  //  {
  //    setPreferredSize(new Dimension(buttonWidth, buttonWidth));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Polygon,
  //              posID = 1)
  //public JCommandButton setBackground = new JCommandButton("Set Background")
  //{
  //  {
  //    setPreferredSize(new Dimension(buttonWidth, buttonWidth));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Polygon,
  //              posID = 2)
  //public JCommandButton saveMap = new JCommandButton("Save Map")
  //{
  //  {
  //    setPreferredSize(new Dimension(buttonWidth, buttonWidth));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Polygon,
  //              posID = 3)
  //public JCommandButton loadMap = new JCommandButton("Load Map")
  //{
  //  {
  //    setPreferredSize(new Dimension(buttonWidth, buttonWidth));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Polygon,
  //              posID = 4)
  //public JCommandButton clearPoints = new JCommandButton("Clear All")
  //{
  //  {
  //    setPreferredSize(new Dimension(buttonWidth, buttonWidth));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Map,
  //              posID = 0, rowspan = 1)
  //public JLabel xOffPanel = new JLabel()
  //{
  //  {
  //    setText("X-Offset:");
  //    setAlignmentY(CENTER_ALIGNMENT);
  //    setBorder(BorderFactory.createEmptyBorder(0, 5, 4, 5));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Map,
  //              posID = 1, rowspan = 1)
  //public JLabel yOffPanel = new JLabel()
  //{
  //  {
  //    setText("Y-Offset:");
  //    setAlignmentY(CENTER_ALIGNMENT);
  //    setBorder(BorderFactory.createEmptyBorder(0, 5, 4, 5));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Map,
  //              posID = 2, rowspan = 1)
  //public JPanel placeholder2 = new JPanel();
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Map,
  //              posID = 3, rowspan = 1)
  //public JSpinner xOff = new JSpinner()
  //{
  //  {
  //    setModel(new SpinnerNumberModel(0, 0, (IWindowConstants.MAX_RASTERWIDTH * IWindowConstants.MAX_MAPRASTERS) - IWindowConstants.WINDOW_WIDTH, 1));
  //    setPreferredSize(new Dimension(buttonWidth, getPreferredSize().height));
  //  }
  //};
  //
  //@RibbonAction(category = ERibbonCategory.Components,
  //              subcategory = ERibbonSubCategory.Map,
  //              posID = 4, rowspan = 1)
  //public JSpinner yOff = new JSpinner()
  //{
  //  {
  //    setModel(new SpinnerNumberModel(0, 0, (IWindowConstants.MAX_RASTERHEIGHT * IWindowConstants.MAX_MAPRASTERS) - IWindowConstants.WINDOW_HEIGHT, 1));
  //    setPreferredSize(new Dimension(buttonWidth, getPreferredSize().height));
  //  }
  //};

  public int getButtonWidth()
  {
    return buttonWidth;
  }

  public void setButtonWidth(int pButtonWidth)
  {
    buttonWidth = pButtonWidth;
  }
}
