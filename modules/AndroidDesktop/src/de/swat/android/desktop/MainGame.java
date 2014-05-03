package de.swat.android.desktop;

import com.badlogic.gdx.Game;

/**
 * @author W. Glanzer, 19.04.2014
 */
public class MainGame extends Game
{
  @Override
  public void create()
  {
    setScreen(new CustomScreen());
  }
}
