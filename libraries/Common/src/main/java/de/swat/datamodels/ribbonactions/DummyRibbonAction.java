package de.swat.datamodels.ribbonactions;

import de.swat.datamodels.AbstractRibbonAction;
import de.swat.datamodels.IMapCreatorImage;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author W. Glanzer, 18.02.2014
 */
public class DummyRibbonAction extends AbstractRibbonAction
{

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {

  }

  @Override
  public ResizableIcon getIcon()
  {
    return null;
  }

  @Override
  public String getTitle()
  {
    return "asdfasdfasdfasdfasdf";
  }

  @Override
  public int getPosition()
  {
    return 0;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return null;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return null;
  }

}
