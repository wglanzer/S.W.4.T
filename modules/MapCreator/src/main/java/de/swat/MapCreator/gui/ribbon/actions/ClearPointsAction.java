package de.swat.mapCreator.gui.ribbon.actions;

import de.swat.constants.IRibbonConstants;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.mapCreator.gui.ribbon.AbstractRibbonAction;
import de.swat.mapCreator.gui.ribbon.IMapCreatorImage;
import de.swat.mapCreator.gui.ribbon.RibbonAction;
import de.swat.utils.ImageUtil;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * @author W. Glanzer, 22.02.14
 */
@RibbonAction
public class ClearPointsAction extends AbstractRibbonAction
{
  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return ImageUtil.loadResizableIcon(ClearPointsAction.class.getResource("trashcan.png"), IRibbonConstants.ICON_SIZE);
  }

  @Override
  public String getTitle()
  {
    return "Clear Points";
  }

  @Override
  public int getPosition()
  {
    return 300;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return ERibbonCategory.COMMON;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return ERibbonSubCategory.MAP;
  }

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {
  }

}
