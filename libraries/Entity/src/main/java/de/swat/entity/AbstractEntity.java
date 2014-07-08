package de.swat.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.swat.common.IActAndDrawable;
import de.swat.common.gui.animation.Animation;
import de.swat.common.gui.animation.AnimationAdapter;
import de.swat.common.gui.assets.IAssets;
import de.swat.common.gui.assets.keys.AnimationKey;
import de.swat.common.gui.components.DrawableImage;
import de.swat.common.stages.CorePreferences;
import org.jetbrains.annotations.Nullable;

/**
 * Abstraktes Entity.
 * Jedes Entity bezeichnet ein Objekt.
 * Hier werden die Grundfunktionen definiert
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class AbstractEntity extends Actor implements IEntity, IActAndDrawable
{

  /**
   * Systemübergreifende Assets
   */
  protected static final IAssets assets = CorePreferences.getAssets();

  /**
   * Drawable, das gezeichnet wird.
   * Entweder Animation, oder einfaches Bild
   */
  private IActAndDrawable drawable;

  public AbstractEntity(FileHandle pFileHandle)
  {
    if(pFileHandle != null)
    {
      DrawableImage img = new DrawableImage(pFileHandle);
      drawable = img;
      setSize(img.getWidth(), img.getHeight());
    }
  }

  /**
   * Zeichnet eine Animation
   *
   * @param pKey  Schlüssel, welche Animation verwendet werden soll,
   *              <tt>null</tt>, wenn die Animation abgebrochen werden soll
   * @param pMode Mode, in dem die Animation abgespielt wird
   */
  public void showAnimation(AnimationKey pKey, Animation.PlayMode pMode)
  {
    showAnimation(pKey, pMode, 0);
  }

  /**
   * Zeichnet eine Animation
   *
   * @param pKey  Schlüssel, welche Animation verwendet werden soll,
   *              <tt>null</tt>, wenn die Animation abgebrochen werden soll
   * @param pMode Mode, in dem die Animation abgespielt wird
   */
  public void showAnimation(AnimationKey pKey, Animation.PlayMode pMode, float pTimeBetweenLoop)
  {
    if(pKey == null && drawable instanceof Animation)
    {
      ((Animation) drawable).stop();
    }
    else if(pKey != null)
    {
      Animation anim = CorePreferences.getAssets().getAnimation(pKey);
      anim.setPlayMode(pMode);
      anim.setTimeBetweenLoops(pTimeBetweenLoop);
      anim.setRotation(drawable.getRotation());
      anim.setListener(new AnimationAdapter()
      {
        private final IActAndDrawable lastDrawable = drawable;

        @Override
        public void animationStopped()
        {
          lastDrawable.setRotation(drawable.getRotation());
          drawable = lastDrawable;
        }
      });
      drawable = anim;
    }
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pOriginX, float pOriginY, float pWidth, float pHeight)
  {
    if(drawable != null)
    {
      float scaleX = getScaleX();
      float scaleY = getScaleY();
      float width = pWidth * scaleX;
      float height = pHeight * scaleY;
      drawable.draw(pBatch, pParentAlpha, pX - width / 2, pY - height / 2, pOriginX * scaleX, pOriginY * scaleY, width, height);
    }
  }

  @Override
  public void draw(Batch batch, float parentAlpha)
  {
    draw(batch, parentAlpha, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight());
  }

  @Override
  public void act(float pDelta)
  {
    super.act(pDelta);
    if(drawable != null)
      drawable.act(pDelta);
  }

  @Override
  public float getRotation()
  {
    return super.getRotation();
  }

  @Override
  public void setRotation(float degrees)
  {
    super.setRotation(degrees);
    if(drawable != null)
      drawable.setRotation(degrees);
  }

  /**
   * @return Liefert die Animation, die das Entity besitzt, zurück.
   * Falls das Entity keine Animation besitzt <tt>null</tt>
   */
  @Nullable
  public Animation getAnimation()
  {
    if(drawable instanceof Animation)
      return (Animation) drawable;

    return null;
  }

  /**
   * Verschiebt den Spieler um die angegebenen Werte
   */
  public void translatePosition(float pX, float pY)
  {
    float x = getX();
    float y = getY();
    setPosition(x + pX, y + pY);
  }

  @Override
  public void dispose()
  {
  }
}
