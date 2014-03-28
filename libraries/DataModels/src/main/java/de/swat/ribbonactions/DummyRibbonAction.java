package de.swat.ribbonactions;

import de.swat.*;
import de.swat.enums.*;
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
