package de.swat.datamodels.util;

import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ImageWrapperResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import java.awt.*;
import java.net.URL;

/**
 * @author W. Glanzer, 18.02.14
 */
public class ImageUtil
{

  @Nullable
  public static ResizableIcon loadResizableIcon(URL pURL, int pSize)
  {
    return ImageWrapperResizableIcon.getIcon(pURL, new Dimension(pSize, pSize));
  }

}
