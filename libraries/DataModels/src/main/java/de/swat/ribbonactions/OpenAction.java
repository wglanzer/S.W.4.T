package de.swat.ribbonactions;

import de.swat.*;
import de.swat.accesses.*;
import de.swat.annotations.RibbonAction;
import de.swat.constants.IRibbonConstants;
import de.swat.enums.*;
import de.swat.util.ImageUtil;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

/**
 * Action, um ein MapDataModel zu von der Festplatte zu laden
 *
 * @author W. Glanzer, 18.02.14
 */
@RibbonAction
public class OpenAction implements IRibbonAction
{
  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IModelAccess pModelAccess)
  {
  }

  @Override
  public ResizableIcon getIcon()
  {
    return ImageUtil.loadResizableIcon(OpenAction.class.getResource(""), IRibbonConstants.ICON_SIZE);
  }

  @Override
  public String getTitle()
  {
    return "Open...";
  }

  @Override
  public int getPosition()
  {
    return 0;
  }

  @Override
  public int getSize()
  {
    return LARGE;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return ERibbonCategory.Anderes;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return ERibbonSubCategory.Map;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}
