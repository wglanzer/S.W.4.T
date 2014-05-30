package de.swat.android.desktop;

import de.swat.FileUtils;
import de.swat.common.gui.assets.AbstractAssets;

import java.io.File;

/**
 * Assets die für den Desktop verwendet werden
 *
 * @author W. Glanzer, 19.04.2014
 */
public class DesktopAssets extends AbstractAssets
{

  @Override
  public File getFilesDir()
  {
    return FileUtils.getFilesDir();
  }

}
