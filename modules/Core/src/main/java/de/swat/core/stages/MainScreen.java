package de.swat.core.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.StageHandler;

/**
 * Screen, der für Desktop und Android gilt.
 * Dieser wertet die Stages aus dem StageHandler aus,
 * und aktualisiert sie ggf.
 *
 * @author W. Glanzer, 17.04.2014
 */
public class MainScreen extends ScreenAdapter
{

  public MainScreen()
  {
  }

  @Override
  public void show()
  {
  }

  @Override
  public void resize(int width, int height)
  {
    AbstractStage background = StageHandler.BACKGROUND;
    if(background != null)
      background.resize(width, height);

    AbstractStage midground = StageHandler.MIDGROUND;
    if(midground != null)
      midground.resize(width, height);

    AbstractStage foreground = StageHandler.FOREGROUND;
    if(foreground != null)
      foreground.resize(width, height);

    AbstractStage controlstage = StageHandler.CONTROLSTAGE;
    if(controlstage != null)
      controlstage.resize(width, height);
  }

  @Override
  public void render(float delta)
  {
    Gdx.gl.glClearColor(255f, 255f, 0f, 1f);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    AbstractStage background = StageHandler.BACKGROUND;
    if(background != null)
      background.render();

    AbstractStage midground = StageHandler.MIDGROUND;
    if(midground != null)
      midground.render();

    AbstractStage foreground = StageHandler.FOREGROUND;
    if(foreground != null)
      foreground.render();

    AbstractStage controlstage = StageHandler.CONTROLSTAGE;
    if(controlstage != null)
      controlstage.render();
  }

  @Override
  public void hide()
  {
    AbstractStage background = StageHandler.BACKGROUND;
    if(background != null)
      background.hide();

    AbstractStage midground = StageHandler.MIDGROUND;
    if(midground != null)
      midground.hide();

    AbstractStage foreground = StageHandler.FOREGROUND;
    if(foreground != null)
      foreground.hide();

    AbstractStage controlstage = StageHandler.CONTROLSTAGE;
    if(controlstage != null)
      controlstage.hide();
  }

  @Override
  public void dispose()
  {
    AbstractStage background = StageHandler.BACKGROUND;
    if(background != null)
      background.dispose();

    AbstractStage midground = StageHandler.MIDGROUND;
    if(midground != null)
      midground.dispose();

    AbstractStage foreground = StageHandler.FOREGROUND;
    if(foreground != null)
      foreground.dispose();

    AbstractStage controlstage = StageHandler.CONTROLSTAGE;
    if(controlstage != null)
      controlstage.dispose();
  }
}
