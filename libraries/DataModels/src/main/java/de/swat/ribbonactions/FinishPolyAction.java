package de.swat.ribbonactions;

import de.swat.*;
import de.swat.enums.*;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author W. Glanzer, 22.02.14
 */
public class FinishPolyAction extends AbstractRibbonAction
{
  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker)
  {
  }

  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return null;
  }

  @Override
  public String getTitle()
  {
    return null;
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
