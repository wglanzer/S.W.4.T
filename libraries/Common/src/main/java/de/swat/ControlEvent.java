package de.swat;

/**
 * @author W.Glanzer, 26.06.2014.
 */
public class ControlEvent
{

  public final Type type;
  public final String subType;
  public final boolean isActivated;
  public final float[] modificator;

  public ControlEvent(Type pType, String pSubType, boolean pIsActivated, float... pModificator)
  {
    type = pType;
    subType = pSubType;
    isActivated = pIsActivated;
    modificator = pModificator;
  }

  public static enum Type
  {
    PLAYER_CONTROL,
    PLAYER_ACTION
  }
}
