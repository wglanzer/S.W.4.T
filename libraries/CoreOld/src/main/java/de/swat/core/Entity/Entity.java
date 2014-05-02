package de.swat.core.Entity;

/**
 * Entity beschreibt alles, was auf der Karte nicht
 * statisch, sondern dynamisch ist.
 * (alles statische ist auf der Karte selbst gespeichert)
 *
 * @author Werner Glanzer, 02.10.13
 */
public class Entity
{
  public float posX;
  public float posY;

  public int runspeed = 1;
  public float renderRunspeed = runspeed / 1000.0F;

  //public SpriteSheet spriteSheet;

  //public Entity(SpriteSheet pSpriteSheet)
  //{
  //  spriteSheet = pSpriteSheet;
  //}

  //public Entity(String pPathToSpritesheet, int pRows, int pColumns)
  //{
  //  this(new SpriteSheet(ImageUtil.loadFileAsBigImage(new File(pPathToSpritesheet)), 150, 150));
  //}

  public float getRenderRunspeed()
  {
    return renderRunspeed;
  }

  public int getRunspeed()
  {
    return runspeed;
  }

  public void setRunspeed(int pRunspeed)
  {
    runspeed = pRunspeed;
    renderRunspeed = runspeed / 1000.0F;
  }

  public float getPosY()
  {
    return posY;
  }

  public void setPosY(float pPosY)
  {
    posY = pPosY;
  }

  public float getPosX()
  {
    return posX;
  }

  public void setPosX(float pPosX)
  {
    posX = pPosX;
  }

}
