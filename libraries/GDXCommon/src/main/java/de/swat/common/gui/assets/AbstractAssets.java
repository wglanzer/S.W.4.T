package de.swat.common.gui.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import de.swat.common.gui.assets.keys.ResourceKey;
import de.swat.common.gui.assets.keys.ShaderKey;
import de.swat.exceptions.SwatRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.ExecutionException;

/**
 * Implementiert Assets, die für Android und Desktop gleich sind
 *
 * @author W.Glanzer, 30.05.2014.
 */
public abstract class AbstractAssets implements IAssets
{
  private static LoadingCache<ShaderKey, ShaderProgram> shaders;
  private static LoadingCache<ResourceKey, FileHandle> resources;
  private BitmapFont font;
  private Skin skin;

  public AbstractAssets()
  {
    shaders = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build(new CacheLoader<ShaderKey, ShaderProgram>()
        {
          @Override
          public ShaderProgram load(@NotNull ShaderKey pKey) throws Exception
          {
            ShaderProgram shader = new ShaderProgram(Gdx.files.internal(pKey.vertShader), Gdx.files.internal(pKey.fragShader));
            if(!shader.isCompiled())
              throw new SwatRuntimeException("Failed to create Shader (shaderKey=" + pKey, null);

            return shader;
          }
        });

    resources = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build(new CacheLoader<ResourceKey, FileHandle>()
        {
          @Override
          public FileHandle load(@NotNull ResourceKey pKey) throws Exception
          {
            return Gdx.files.internal(pKey.path);
          }
        });
  }

  @Override
  public Skin getSkinDefault()
  {
    if(skin == null)
      skin = new Skin(getResource(ResourceKey.SKIN_DEFAULT))
      {
        @Override
        public BitmapFont getFont(String name)
        {
          return AbstractAssets.this.getFont();
        }
      };

    return skin;
  }

  @Override
  public BitmapFont getFont()
  {
    if(font == null)
    {
      Texture texture = new Texture(getResource(ResourceKey.FONT_DEFAULT_PNG), true);
      texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
      font = new BitmapFont(getResource(ResourceKey.FONT_DEFAULT_FNT), new TextureRegion(texture), false);
    }

    return font;
  }

  @Override
  public ShaderProgram getShader(ShaderKey pKey)
  {
    try
    {
      return shaders.get(pKey);
    }
    catch(ExecutionException e)
    {
      throw new SwatRuntimeException("Cannot load shader: " + pKey, e);
    }
  }

  @Override
  public FileHandle getResource(ResourceKey pKey)
  {
    try
    {
      return resources.get(pKey);
    }
    catch(ExecutionException e)
    {
      throw new SwatRuntimeException("Cannot load resource: " + pKey, e);
    }
  }
}
