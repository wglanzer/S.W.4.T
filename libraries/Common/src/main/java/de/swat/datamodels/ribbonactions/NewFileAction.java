package de.swat.datamodels.ribbonactions;

import de.swat.annotationProcessors.annotations.RibbonAction;
import de.swat.constants.IRibbonConstants;
import de.swat.constants.IWindowConstants;
import de.swat.datamodels.AbstractRibbonAction;
import de.swat.datamodels.IMapCreatorImage;
import de.swat.datamodels.Map;
import de.swat.datamodels.Raster;
import de.swat.datamodels.accesses.MapModelAccess;
import de.swat.datamodels.util.DataModelHandler;
import de.swat.datamodels.util.ImageUtil;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author W. Glanzer, 22.02.14
 */
@RibbonAction
public class NewFileAction extends AbstractRibbonAction
{
  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {
    MapModelAccess modelAccess = (MapModelAccess) DataModelHandler.newModelAccess(MapModelAccess.class);
    Map map = new Map(modelAccess);
    modelAccess.setRaster(new Raster(10, new Dimension(IWindowConstants.MAX_RASTERWIDTH, IWindowConstants.MAX_RASTERHEIGHT), map));
    pMapCreatorImage.setMap(map);
  }

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

}
