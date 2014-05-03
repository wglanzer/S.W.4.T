package de.swat.android.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.swat.constants.IVersion;
import de.swat.core.CorePreferences;

/**
 * Startet das Android-Game auf dem Desktop
 *
 * @author W.Glanzer, 24.04.2014.
 */
public class DesktopStarter
{
  private static final int WIDTH = 854;
  private static final int HEIGHT = 480;

  public static void main(String[] args)
  {
    CorePreferences.setAssets(new DesktopAssets());

    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = IVersion.MAJOR_NAME + " - DesktopStarter - Version " + IVersion.ANDROID_VERSION;
    cfg.useGL20 = false;
    cfg.height = HEIGHT;
    cfg.width = WIDTH;

    new LwjglApplication(new MainGame(), cfg);
  }

}
