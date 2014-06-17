package de.swat.entity;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.swat.common.gui.assets.IAssets;
import de.swat.common.stages.CorePreferences;

/**
 * @author W.Glanzer, 03.06.2014.
 */
public class AbstractEntity extends Actor implements IEntity
{

  protected static final IAssets assets = CorePreferences.getAssets();

  private Texture texture;

  public AbstractEntity(FileHandle pFileHandle)
  {
    if(pFileHandle != null)
    {
      texture = new Texture(pFileHandle);
      texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
      setSize(texture.getWidth(), texture.getHeight());
    }
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);

    if(texture != null)
    {
      float x = getX();
      float y = getY();
      float width = getWidth() * getScaleX();
      float height = getHeight() * getScaleY();
      batch.draw(texture, x - width / 2, y - height / 2, width, height);
    }
  }
}
