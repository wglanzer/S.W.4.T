package de.swat.android.desktop;

import com.badlogic.gdx.Input;

/**
 * Liefert alle KeyCodes der Keys, die verwendet werden
 *
 * @author W.Glanzer, 26.06.2014.
 */
public interface IControlSheet
{

  public static final int MOVE_FORWARD = Input.Keys.W;
  public static final int MOVE_BACKWARD = Input.Keys.S;
  public static final int MOVE_LEFT = Input.Keys.A;
  public static final int MOVE_RIGHT = Input.Keys.D;

}
