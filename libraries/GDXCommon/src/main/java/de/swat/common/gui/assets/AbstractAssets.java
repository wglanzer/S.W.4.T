package de.swat.common.gui.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import de.swat.exceptions.SwatRuntimeException;

import java.util.concurrent.ExecutionException;

/**
 * Implementiert Assets, die für Android und Desktop gleich sind
 *
 * @author W.Glanzer, 30.05.2014.
 */
public abstract class AbstractAssets implements IAssets
{

  private static final String SKIN_DEFAULT_PREFIX = "skins/defaultSkin/";

  public static final String SKIN_DEFAULT = SKIN_DEFAULT_PREFIX + "uiskin.json";

  public static final String FONT_DEFAULT_PNG = SKIN_DEFAULT_PREFIX + "font.png";
  public static final String FONT_DEFAULT_FNT = SKIN_DEFAULT_PREFIX + "font.fnt";

  private BitmapFont font;
  private Skin skin;
  private LoadingCache<ShaderKey, ShaderProgram> shaders;

  public AbstractAssets()
  {
    shaders = CacheBuilder.newBuilder()
        .maximumSize(1000)
        .build(new CacheLoader<ShaderKey, ShaderProgram>()
        {
          @Override
          public ShaderProgram load(ShaderKey pKey) throws Exception
          {
            ShaderProgram shader = new ShaderProgram(Gdx.files.internal(pKey.vertShader), Gdx.files.internal(pKey.fragShader));
            if(!shader.isCompiled())
              throw new SwatRuntimeException("Failed to create Shader (shaderKey=" + pKey, null);

            return shader;
          }
        });
  }

  @Override
  public Skin getSkinDefault()
  {
    if(skin == null)
      skin = new Skin(Gdx.files.internal(SKIN_DEFAULT))
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
      Texture texture = new Texture(Gdx.files.internal(FONT_DEFAULT_PNG), true);
      texture.setFilter(Texture.TextureFilter.MipMapLinearNearest, Texture.TextureFilter.Linear);
      font = new BitmapFont(Gdx.files.internal(FONT_DEFAULT_FNT), new TextureRegion(texture), false);
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
}
