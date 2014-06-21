package de.swat.common.gui.components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.common.IActAndDrawable;
import org.jetbrains.annotations.Nullable;

/**
 * Einzelnes Bild
 *
 * @author W.Glanzer, 20.06.2014.
 */
public class DrawableImage implements IActAndDrawable
{

  private Texture texture;

  private float width = 0;
  private float height = 0;

  public DrawableImage(@Nullable FileHandle pFileHandle)
  {
    if(pFileHandle != null)
    {
      texture = new Texture(pFileHandle);
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      width = texture.getWidth();
      height = texture.getHeight();
    }
  }

  @Override
  public void act(float delta)
  {
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    pBatch.draw(texture, pX, pY, pWidth, pHeight);
  }

  public float getWidth()
  {
    return width;
  }

  public void setWidth(float pWidth)
  {
    width = pWidth;
  }

  public float getHeight()
  {
    return height;
  }

  public void setHeight(float pHeight)
  {
    height = pHeight;
  }
}
