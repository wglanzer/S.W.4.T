package de.swat.android.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import de.swat.android.AMainGame;
import de.swat.constants.IVersion;

/**
 * Startet das Android-Game auf dem Desktop
 *
 * @author W.Glanzer, 24.04.2014.
 */
public class DesktopStarter
{

  private static final int FACTOR = 640;

  public static void main(String[] args)
  {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = IVersion.MAJOR_NAME + " - DesktopStarter - Version " + IVersion.ANDROID_VERSION;
    cfg.useGL20 = false;
    cfg.width = (int) (FACTOR * 1.5);
    cfg.height = FACTOR;

    new LwjglApplication(new AMainGame(), cfg);
  }

}
