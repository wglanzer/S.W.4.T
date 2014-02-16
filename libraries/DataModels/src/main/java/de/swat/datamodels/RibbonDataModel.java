package de.swat.datamodels;

import de.swat.annotations.*;
import de.swat.constants.IWindowConstants;
import de.swat.enums.*;
import org.pushingpixels.flamingo.api.common.JCommandButton;

import javax.swing.*;
import java.awt.*;

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

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Polygon,
                   posID = 0)
  public JCommandButton finishPoly = new JCommandButton("Finish Polygon")
  {
    {
      setPreferredSize(new Dimension(buttonWidth, buttonWidth));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Polygon,
                   posID = 1)
  public JCommandButton setBackground = new JCommandButton("Set Background")
  {
    {
      setPreferredSize(new Dimension(buttonWidth, buttonWidth));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Polygon,
                   posID = 2)
  public JCommandButton saveMap = new JCommandButton("Save Map")
  {
    {
      setPreferredSize(new Dimension(buttonWidth, buttonWidth));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Polygon,
                   posID = 3)
  public JCommandButton loadMap = new JCommandButton("Load Map")
  {
    {
      setPreferredSize(new Dimension(buttonWidth, buttonWidth));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Polygon,
                   posID = 4)
  public JCommandButton clearPoints = new JCommandButton("Clear All")
  {
    {
      setPreferredSize(new Dimension(buttonWidth, buttonWidth));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Map,
                   posID = 0, rowspan = 1)
  public JLabel xOffPanel = new JLabel()
  {
    {
      setText("X-Offset:");
      setAlignmentY(CENTER_ALIGNMENT);
      setBorder(BorderFactory.createEmptyBorder(0, 5, 4, 5));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Map,
                   posID = 1, rowspan = 1)
  public JLabel yOffPanel = new JLabel()
  {
    {
      setText("Y-Offset:");
      setAlignmentY(CENTER_ALIGNMENT);
      setBorder(BorderFactory.createEmptyBorder(0, 5, 4, 5));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Map,
                   posID = 2, rowspan = 1)
  public JPanel placeholder2 = new JPanel();

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Map,
                   posID = 3, rowspan = 1)
  public JSpinner xOff = new JSpinner()
  {
    {
      setModel(new SpinnerNumberModel(0, 0, (IWindowConstants.MAX_RASTERWIDTH * IWindowConstants.MAX_MAPRASTERS) - IWindowConstants.WINDOW_WIDTH, 1));
      setPreferredSize(new Dimension(buttonWidth, getPreferredSize().height));
    }
  };

  @RibbonComponent(category = ERibbonCategory.Components,
                   subcategory = ERibbonSubCategory.Map,
                   posID = 4, rowspan = 1)
  public JSpinner yOff = new JSpinner()
  {
    {
      setModel(new SpinnerNumberModel(0, 0, (IWindowConstants.MAX_RASTERHEIGHT * IWindowConstants.MAX_MAPRASTERS) - IWindowConstants.WINDOW_HEIGHT, 1));
      setPreferredSize(new Dimension(buttonWidth, getPreferredSize().height));
    }
  };

  public int getButtonWidth()
  {
    return buttonWidth;
  }

  public void setButtonWidth(int pButtonWidth)
  {
    buttonWidth = pButtonWidth;
  }

  public JCommandButton getFinishPoly()
  {
    return finishPoly;
  }

  public void setFinishPoly(JCommandButton pFinishPoly)
  {
    finishPoly = pFinishPoly;
  }

  public JCommandButton getSetBackground()
  {
    return setBackground;
  }

  public void setSetBackground(JCommandButton pSetBackground)
  {
    setBackground = pSetBackground;
  }

  public JCommandButton getSaveMap()
  {
    return saveMap;
  }

  public void setSaveMap(JCommandButton pSaveMap)
  {
    saveMap = pSaveMap;
  }

  public JCommandButton getLoadMap()
  {
    return loadMap;
  }

  public void setLoadMap(JCommandButton pLoadMap)
  {
    loadMap = pLoadMap;
  }

  public JCommandButton getClearPoints()
  {
    return clearPoints;
  }

  public void setClearPoints(JCommandButton pClearPoints)
  {
    clearPoints = pClearPoints;
  }

  public JLabel getXOffPanel()
  {
    return xOffPanel;
  }

  public void setXOffPanel(JLabel pXOffPanel)
  {
    xOffPanel = pXOffPanel;
  }

  public JLabel getYOffPanel()
  {
    return yOffPanel;
  }

  public void setYOffPanel(JLabel pYOffPanel)
  {
    yOffPanel = pYOffPanel;
  }

  public JPanel getPlaceholder2()
  {
    return placeholder2;
  }

  public void setPlaceholder2(JPanel pPlaceholder2)
  {
    placeholder2 = pPlaceholder2;
  }

  public JSpinner getXOff()
  {
    return xOff;
  }

  public void setXOff(JSpinner pXOff)
  {
    xOff = pXOff;
  }

  public JSpinner getYOff()
  {
    return yOff;
  }

  public void setYOff(JSpinner pYOff)
  {
    yOff = pYOff;
  }
}
