package de.swat.android.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.core.IAssets;
import org.apache.logging.log4j.LogManager;

import java.io.File;

/**
 * Assets die im Game verwendet werden
 *
 * @author W. Glanzer, 19.04.2014
 */
public class DesktopAssets implements IAssets
{

  public static final String SKIN_DEFAULT = "skins/defaultSkin/uiskin.json";

  @Override
  public Skin getSkinDefault()
  {
    return new Skin(Gdx.files.internal(SKIN_DEFAULT));
  }

  @Override
  public File getFilesDir()
  {
    File directory = new File(System.getProperty("user.home") + File.pathSeparator + ".sw4t");

    if(!directory.exists())
      if(!directory.mkdir())
        LogManager.getLogger().error("Could not create folder in your userhome!");

    return directory;
  }
}
