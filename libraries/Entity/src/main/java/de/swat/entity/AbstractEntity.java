package de.swat.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.swat.common.gui.animation.Animation;
import de.swat.common.gui.assets.IAssets;
import de.swat.common.gui.assets.keys.AnimationKey;
import de.swat.common.stages.CorePreferences;

/**
 * Abstraktes Entity.
 * Jedes Entity bezeichnet ein Objekt.
 * Hier werden die Grundfunktionen definiert
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class AbstractEntity extends Actor implements IEntity
{

  /**
   * Systemübergreifende Assets
   */
  protected static final IAssets assets = CorePreferences.getAssets();

  /**
   * Textur, wenn keine Animation vorhanden ist
   */
  private Texture texture;

  /**
   * Animation, die abgespielt werden kann
   */
  private Animation anim;

  public AbstractEntity(FileHandle pFileHandle)
  {
    if(pFileHandle != null)
    {
      texture = new Texture(pFileHandle);
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      setSize(texture.getWidth(), texture.getHeight());
    }
  }

  /**
   * Zeichnet eine Animation
   *
   * @param pKey   Schlüssel, welche Animation verwendet werden soll
   * @param pMode  Mode, in dem die Animation abgespielt wird
   */
  public void showAnimation(AnimationKey pKey, Animation.PlayMode pMode)
  {
    if(pKey == null)
      anim = null;
    else
    {
      anim = CorePreferences.getAssets().getAnimation(pKey);
      anim.resetCounter();
      anim.setPlayMode(pMode);
    }
  }

  /**
   * @return Die Animation, <tt>null</tt> wenn keine Animation verwendet wird
   */
  public Animation getAnimation()
  {
    return anim;
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);

    if(anim != null)
      anim.draw(batch, parentAlpha);
    else if(texture != null)
    {
      float x = getX();
      float y = getY();
      float width = getWidth() * getScaleX();
      float height = getHeight() * getScaleY();
      batch.draw(texture, x - width / 2, y - height / 2, width, height);
    }
  }

  @Override
  public void act(float pDelta)
  {
    super.act(pDelta);
    if(anim != null)
      anim.act(pDelta);
  }

  @Override
  public void setPosition(float x, float y)
  {
    super.setPosition(x, y);
    if(anim != null)
      anim.setPosition(x, y);
  }

  @Override
  public void setScale(float pScale)
  {
    super.setScale(pScale);
    if(anim != null)
      anim.setScale(pScale);
  }

  @Override
  public void setSize(float width, float height)
  {
    super.setSize(width, height);
    if(anim != null)
      anim.setSize(width, height);
  }
}
