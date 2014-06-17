package de.swat.common.gui.assets.keys;

/**
 * Keys für Resourcen
 *
 * @author W.Glanzer, 31.05.2014.
 */
public enum ResourceKey
{
  FONT_DEFAULT_PNG("skins/defaultSkin/font.png"),
  FONT_DEFAULT_FNT("skins/defaultSkin/font.fnt"),
  SKIN_DEFAULT("skins/defaultSkin/uiskin.json"),
  TOUCHPAD_BACKGROUND("data/touchBackground.png"),
  TOUCHPAD_KNOB("data/touchKnob.png"),
  PLAYER_ARMS("player/Arms.png"),
  PLAYER_HEAD("player/Head.png"),
  PLAYER_TORSO("player/Torso.png"),
  PLAYER_LEGS("player/Legs.png"),
  WEAPON_P99("player/Weapon_P99.png");

  public final String path;

  ResourceKey(String pPath)
  {
    path = pPath;
  }

  @Override
  public String toString()
  {
    return "ResourceKey{" +
        "path='" + path + '\'' +
        '}';
  }
}
