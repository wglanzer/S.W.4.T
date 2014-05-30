package de.swat.android;

import de.swat.common.gui.assets.AbstractAssets;
import org.jetbrains.annotations.NotNull;

import java.io.File;

/**
 * Assets die auf Android verwendet werden
 *
 * @author W. Glanzer, 19.04.2014
 */
public class AAssets extends AbstractAssets
{
  private static AAssets INSTANCE = new AAssets();
  private static File filesDir = new File("\\");

  public static void initalize(@NotNull File pFilesDir)
  {
    if(filesDir != null)
      filesDir = pFilesDir;
  }

  public static AAssets get()
  {
    return INSTANCE;
  }

  @Override
  public File getFilesDir()
  {
    return filesDir;
  }
}
