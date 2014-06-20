package de.swat.common.gui.animation;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.utils.Array;
import de.swat.common.IActAndDrawable;
import org.jetbrains.annotations.Nullable;

/**
 * Animationsklasse
 *
 * @author W.Glanzer, 18.06.2014.
 */
public class Animation implements IActAndDrawable
{

  private Array<Sprite> skeleton = new Array<>();
  private float elapsed;
  private int currentFrame;
  private float speed;
  private PlayMode playMode = PlayMode.HALT;
  private boolean showNextAnimation;
  private IAnimationListener listener;

  /**
   * Erstellt eine neue Animation
   *
   * @param pFile  Datei, das zur .txt-Datei führt
   * @param pName  Name der internen Bilder ("res_0001", "res_0002" -> "res")
   * @param pSpeed Geschwindigkeit, die den Abstand der Bilder zueinander darstellt
   */
  public Animation(@Nullable FileHandle pFile, String pName, float pSpeed)
  {
    this(pFile, pName, pSpeed, null);
  }

  /**
   * Erstellt eine neue Animation
   *
   * @param pFile     Datei, das zur .txt-Datei führt
   * @param pName     Name der internen Bilder ("res_0001", "res_0002" -> "res")
   * @param pSpeed    Geschwindigkeit, die den Abstand der Bilder zueinander darstellt
   * @param pListener Listener für die Animation, <tt>null</tt>, wenn keiner gebraucht wird
   */
  public Animation(@Nullable FileHandle pFile, String pName, float pSpeed, IAnimationListener pListener)
  {
    speed = pSpeed;
    if(pFile != null)
    {
      TextureAtlas spriteSheet = new TextureAtlas(pFile.path());
      skeleton = spriteSheet.createSprites(pName);
    }
    if(pListener != null)
      listener = new AnimationAdapter();
  }

  /**
   * Setzt den PlayMode
   *
   * @param pMode Mode, der gesetzt werden soll
   * @see de.swat.common.gui.animation.Animation.PlayMode
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
    listener.animationStopped();
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
        else
          listener.animationStopped();
      }
    }
  }

  public void draw(SpriteBatch batch, float parentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    if(skeleton != null)
    {
      Sprite sprite = skeleton.get(currentFrame);
      sprite.setBounds(pX, pY, pWidth, pHeight);
      sprite.draw(batch);
    }
  }

  public void setListener(IAnimationListener pListener)
  {
    listener = pListener;
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
