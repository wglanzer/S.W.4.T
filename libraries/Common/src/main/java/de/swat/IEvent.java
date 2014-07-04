package de.swat;

/**
 * @author W.Glanzer, 26.06.2014.
 */
public interface IEvent
{

  /**
   * Beim Player_Move-Event werden als Parameter x und y-Position
   * erwartet. Hierbei sind die Werte in Prozent anzugeben, wie schnell sich der
   * Spieler bewegen soll.
   */
  public static final String PLAYER_MOVE = "player_move";

  /**
   * Gibt an, dass der Player jetzt schießen soll.
   */
  public static final String PLAYER_SHOOT = "player_shoot";

  /**
   * Gibt an, dass der Player nachladen soll
   */
  public static final String PLAYER_RELOAD = "player_reload";

  /**
   * Gibt an, dass sich der Spieler um seine Achse drehen soll.
   * Als Parameter werden hier erwartet, wo die "Maus" sich gerade befindet /
   * wo der Spieler hinschauen soll.
   */
  public static final String PLAYER_ROTATE = "player_rotate";
}
