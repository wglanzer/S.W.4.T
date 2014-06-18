package de.swat.common.gui.animation;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import org.jetbrains.annotations.Nullable;

/**
 * Animationsklasse
 *
 * @author W.Glanzer, 18.06.2014.
 */
public class Animation
{

  private Array<Sprite> skeleton = new Array<>();
  private float elapsed;
  private int currentFrame;
  private float speed;
  private PlayMode playMode = PlayMode.HALT;
  private boolean showNextAnimation;

  /**
   * Erstellt eine neue Animation
   *
   * @param pFile  Datei, das zur .txt-Datei führt
   * @param pName  Name der internen Bilder ("res_0001", "res_0002" -> "res")
   * @param pSpeed Geschwindigkeit, die den Abstand der Bilder zueinander darstellt
   */
  public Animation(@Nullable FileHandle pFile, String pName, float pSpeed)
  {
    speed = pSpeed;
    if(pFile != null)
    {
      TextureAtlas spriteSheet = new TextureAtlas(pFile.path());
      skeleton = spriteSheet.createSprites(pName);
    }
  }

  /**
   * Setzt den PlayMode
   *
   * @see de.swat.common.gui.animation.Animation.PlayMode
   * @param pMode  Mode, der gesetzt werden soll
   */
  public void setPlayMode(PlayMode pMode)
  {
    playMode = pMode;
  }

  /**
   * Startet die Animation.
   * Kann ggf. auch als "Resume" dienen, falls bspw. im PlayMode
   * "HALT"
   */
  public void startAnimation()
  {
    showNextAnimation = true;
  }

  /**
   * Setzt alle Counter wieder zurück auf 0
   */
  public void resetCounter()
  {
    currentFrame = 0;
    elapsed = 0;
  }

  /**
   * Stoppt die Animation
   */
  public void stop()
  {
    currentFrame = skeleton.size - 1;
    playMode = PlayMode.HALT;
  }


  public void act(float pDelta)
  {
    elapsed += pDelta;
    // Es soll zum nächsten Frame umgeschaltet werden
    if(elapsed >= speed)
    {
      // Es gibt noch einen nächsten Frame
      if(currentFrame <= skeleton.size - 2)
      {
        currentFrame++;
        elapsed = 0f;
      }
      // Es gibt keinen nächsten Frame mehr, Loopen wäre jetzt angesagt
      else
      {
        if(playMode.equals(PlayMode.LOOP) || showNextAnimation)
        {
          currentFrame = 0;
          elapsed = 0f;
          if(showNextAnimation)
            showNextAnimation = false;
        }
      }
    }
  }

  public void draw(SpriteBatch batch, float parentAlpha)
  {
    if(skeleton != null)
      skeleton.get(currentFrame).draw(batch);
  }

  public void setPosition(float pX, float pY)
  {
    for(int i = 0; i < skeleton.size; i++)
    {
      Sprite sprite = skeleton.get(i);
      sprite.setPosition(pX - sprite.getWidth() / 2, pY - sprite.getHeight() / 2);
    }
  }

  public void setSize(float pWidth, float pHeight)
  {
    for(int i = 0; i < skeleton.size; i++)
      skeleton.get(i).setSize(pWidth, pHeight);
  }

  public void setScale(float pScale)
  {
    for(int i = 0; i < skeleton.size; i++)
      skeleton.get(i).setScale(pScale);
  }

  /**
   * Enum auf Modi, die eine Animation haben kann
   */
  public enum PlayMode
  {
    /**
     * Behält den letzten Frame der Animation bei.
     */
    HALT,

    /**
     * Loopt die Animation
     */
    LOOP
  }
}
