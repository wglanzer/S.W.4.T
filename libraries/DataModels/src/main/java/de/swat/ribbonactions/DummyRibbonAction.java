package de.swat.ribbonactions;

import de.swat.IRibbonAction;
import de.swat.enums.*;

import javax.swing.*;

/**
 * @author W. Glanzer, 18.02.2014
 */
public class DummyRibbonAction implements IRibbonAction
{
  @Override
  public void performAction()
  {
  }

  @Override
  public Icon getIcon()
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
