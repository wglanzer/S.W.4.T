package de.swat.mapCreator.gui.ribbon.actions;

import de.swat.common.map.Map;
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
public class NewFileAction extends AbstractRibbonAction
{
  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return ImageUtil.loadResizableIcon(NewFileAction.class.getResource("document6.png"), IRibbonConstants.ICON_SIZE);
  }

  @Override
  public String getTitle()
  {
    return "New...";
  }

  @Override
  public int getPosition()
  {
    return 50;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return ERibbonCategory.COMMON;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return ERibbonSubCategory.FILE;
  }

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {
    pMapCreatorImage.setMap(new Map());
  }

}
