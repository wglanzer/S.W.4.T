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

  public static void main(String[] args)
  {
    LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
    cfg.title = IVersion.MAJOR_NAME + " - DesktopStarter - Version " + IVersion.ANDROID_VERSION;
    cfg.useGL20 = false;
    cfg.width = 960;
    cfg.height = 640;

    new LwjglApplication(new AMainGame(), cfg);
  }

}
