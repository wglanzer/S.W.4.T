package de.swat.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import de.swat.android.dialog.ADialogDisplayer;
import de.swat.android.gui.controls.TouchpadImpl;
import de.swat.core.stages.startStage.StartStage;

/**
 * Ein gekapselter ApplicationListener der durch den AExceptionRedirectScreen
 * benutzt und aufgerufen wird. Durch diesen werden dann etwaige Exceptions abgefangen
 * und durch einen Dialog ersetzt.
 *
 * @author W. Glanzer, 17.04.2014
 */
public class AScreen extends ScreenAdapter
{
  private Stage stage = new StartStage();
  private Touchpad leftStick = new TouchpadImpl().getTouchpad();

  @Override
  public void show()
  {
    ADialogDisplayer.setStage(stage);
    Gdx.input.setInputProcessor(stage);

    stage.addActor(leftStick);
  }

  @Override
  public void resize(int width, int height)
  {
    int border = 10;

    stage.setViewport(width, height);

    // -- Größe des Linken Sticks
    int lSSize = (int) (height * 0.35);
    leftStick.setBounds(border, border, lSSize, lSSize);
    // ----
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
