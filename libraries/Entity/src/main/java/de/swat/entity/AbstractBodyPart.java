package de.swat.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Abstraktion für einen Teil eines Parts
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class AbstractBodyPart extends AbstractEntity
{
  private Texture texture;

  public AbstractBodyPart(FileHandle pFileHandle)
  {
    texture = new Texture(pFileHandle);
    texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    setSize(texture.getWidth(), texture.getHeight());
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    float x = getX();
    float y = getY();
    float width = getWidth() * getScaleX();
    float height = getHeight() * getScaleY();
    batch.draw(texture, x - width /2, y - height /2, width, height);
  }
}
