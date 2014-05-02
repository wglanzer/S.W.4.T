package de.swat.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.android.constants.AIStaticConstants;
import de.swat.android.dialog.ADialogDisplayer;
import de.swat.android.gui.controls.TouchpadImpl;

/**
 * Ein gekapselter ApplicationListener der durch den AExceptionRedirectScreen
 * benutzt und aufgerufen wird. Durch diesen werden dann etwaige Exceptions abgefangen
 * und durch einen Dialog ersetzt.
 *
 * @author W. Glanzer, 17.04.2014
 */
public class AScreen extends ScreenAdapter
{
  private Stage stage = new Stage();
  private TextButton enableWifi = new TextButton("enable Wifi", AIStaticConstants.SKIN_DEFAULT);
  private Color background = new Color(255f, 255f, 0f, 1f);

  @Override
  public void show()
  {
    ADialogDisplayer.setStage(stage);
    Gdx.input.setInputProcessor(stage);

    enableWifi.setBounds(stage.getWidth() - 100 - 5, stage.getHeight() - 40 - 5, 100, 40);
    enableWifi.setChecked(true);
    enableWifi.addListener(new ClickListener()
    {
      private boolean wasClickedBefore = false;

      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        //Dann Server starten
        if(!wasClickedBefore)
        {
          enableWifi.setText("disable Wifi");
        }
        else
        {
          enableWifi.setText("enable Wifi");
        }

        wasClickedBefore = !wasClickedBefore;
      }
    });

    stage.addActor(enableWifi);
    stage.addActor(new TouchpadImpl().getTouchpad());
  }

  @Override
  public void resize(int width, int height)
  {
    stage.setViewport(width, height);
  }

  @Override
  public void render(float delta)
  {
    Gdx.gl.glClearColor(background.r, background.g, background.b, background.a);
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
