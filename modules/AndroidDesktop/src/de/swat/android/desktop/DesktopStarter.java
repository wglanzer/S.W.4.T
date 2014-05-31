package de.swat.android.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.swat.common.stages.CorePreferences;
import de.swat.constants.IVersion;

/**
 * Startet das Android-Game auf dem Desktop
 *
 * @author W.Glanzer, 24.04.2014.
 */
public class DesktopStarter
{
  private static final int WIDTH = 1600;
  private static final int HEIGHT = 900;

  public static void main(String[] args)
  {
    CorePreferences.setAssets(new DesktopAssets());
    System.setProperty("log4j.configurationFile", "log4j2.xml");

    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = IVersion.MAJOR_NAME + " - DesktopStarter - Version " + IVersion.ANDROID_VERSION;
    cfg.useGL20 = true;
    cfg.height = HEIGHT;
    cfg.width = WIDTH;

    new LwjglApplication(new MainGame(), cfg);
  }

}
