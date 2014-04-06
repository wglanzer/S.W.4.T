package de.swat.android;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author W. Glanzer, 06.04.2014
 */
public class AndroidCore implements ApplicationListener
{

  Texture texture;
  SpriteBatch batch;
  float elapsed;

  @Override
  public void create()
  {
    texture = new Texture(Gdx.files.internal("libgdx-logo.png"));
    batch = new SpriteBatch();
  }

  @Override
  public void resize(int width, int height)
  {
  }

  @Override
  public void render()
  {
    elapsed += Gdx.graphics.getDeltaTime();
    Gdx.gl.glClearColor(0, 0, 0, 0);
    Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
    batch.begin();
    batch.draw(texture, 100 + 100 * (float) Math.cos(elapsed), 100 + 25 * (float) Math.sin(elapsed));
    batch.end();
  }

  @Override
  public void pause()
  {
  }

  @Override
  public void resume()
  {
  }

  @Override
  public void dispose()
  {
  }

}
