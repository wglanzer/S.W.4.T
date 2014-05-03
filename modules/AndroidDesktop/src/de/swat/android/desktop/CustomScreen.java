package de.swat.android.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.core.stages.StartStage;

/**
 * Ein gekapselter ApplicationListener der durch den AExceptionRedirectScreen
 * benutzt und aufgerufen wird. Durch diesen werden dann etwaige Exceptions abgefangen
 * und durch einen Dialog ersetzt.
 *
 * @author W. Glanzer, 17.04.2014
 */
public class CustomScreen extends ScreenAdapter
{
  private Stage stage = new StartStage();

  @Override
  public void show()
  {
    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void resize(int width, int height)
  {
    stage.setViewport(width, height);
  }

  @Override
  public void render(float delta)
  {
    Gdx.gl.glClearColor(255f, 255f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    stage.act(delta);
    stage.draw();
  }

  @Override
  public void hide()
  {
    dispose();
  }

  @Override
  public void dispose()
  {
    stage.dispose();
  }
}
