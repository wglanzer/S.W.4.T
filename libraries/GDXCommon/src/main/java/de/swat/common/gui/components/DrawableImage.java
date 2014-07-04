package de.swat.common.gui.components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import de.swat.common.IActAndDrawable;
import org.jetbrains.annotations.Nullable;

/**
 * Einzelnes Bild
 *
 * @author W.Glanzer, 20.06.2014.
 */
public class DrawableImage implements IActAndDrawable
{

  private TextureRegion texture;

  private float width = 0;
  private float height = 0;
  private float rotation = 0f;

  public DrawableImage(@Nullable FileHandle pFileHandle)
  {
    if(pFileHandle != null)
    {
      Texture tex = new Texture(pFileHandle);
      tex.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      width = tex.getWidth();
      height = tex.getHeight();
      texture = new TextureRegion(tex);
    }
  }

  @Override
  public void act(float delta)
  {
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    pBatch.draw(texture, pX, pY, pWidth/2, pHeight/2, pWidth, pHeight, 1f, 1f, rotation);
  }

  public float getWidth()
  {
    return width;
  }  @Override
  public void setRotation(float pDegrees)
  {
    rotation = pDegrees;
  }

  public void setWidth(float pWidth)
  {
    width = pWidth;
  }  @Override
  public float getRotation()
  {
    return rotation;
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
