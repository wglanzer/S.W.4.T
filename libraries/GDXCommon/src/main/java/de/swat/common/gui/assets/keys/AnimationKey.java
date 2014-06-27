package de.swat.common.gui.assets.keys;

/**
 * @author W.Glanzer, 18.06.2014.
 */
public enum AnimationKey
{
  PLAYER_RECOIL("player/anim/recoil/recoil.txt", "recoil", 0.035f),
  PLAYER_RELOAD("player/anim/reload/reload.txt", "reload", 0.045f);

  public final String path;
  public final String name;
  public final float speed;

  AnimationKey(String pPath, String pName, float pSpeed)
  {
    path = pPath;
    name = pName;
    speed = pSpeed;
  }

  @Override
  public String toString()
  {
    return "AnimationKey{" +
        "path='" + path + '\'' +
        "name='" + name + "\'" +
        "speed='" + speed + "\'" +
        '}';
  }
}
