package de.swat.android;

import com.badlogic.gdx.backends.lwjgl.*;

/**
 * @author W. Glanzer, 06.04.2014
 */
public class CoreDesktop
{

  public static void main(String[] args)
  {
    LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
    config.useGL20 = true;
    new LwjglApplication(new AndroidCore(), config);
  }

}
