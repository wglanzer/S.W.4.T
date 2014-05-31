package de.swat.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.android.dialog.ADialogDisplayer;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.IStageChangeListener;
import de.swat.common.stages.StageHandler;
import de.swat.core.stages.MainScreen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Dieser Screen leitet an einen anderen weiter.
 * Hier werden nur die Exceptions des anderen abgefangen, sodass kein
 * critical-error mehr entstehen kann.
 *
 * @author W. Glanzer, 06.04.2014
 */
public class AScreen implements Screen
{
  private Screen redirectTo;
  private Logger logger = LogManager.getLogger();

  public AScreen()
  {
    try
    {
      StageHandler.addStageChangeListener(new IStageChangeListener()
      {
        @Override
        public void stageChanged(StageHandler.StageType pStageType, Stage pStage)
        {
          if(pStageType.equals(StageHandler.StageType.CONTROLSTAGE))
          {
            ADialogDisplayer.setStage(pStage);
            Gdx.input.setInputProcessor(pStage);
          }
        }
      });

      AGuiStage guiStage = new AGuiStage();
      guiStage.addToHandler(StageHandler.StageType.CONTROLSTAGE);
      AbstractStage.setGuiStage(guiStage);

      redirectTo = new MainScreen();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    try
    {
      redirectTo.resize(pWidth, pHeight);
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void pause()
  {
    try
    {
      redirectTo.pause();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void resume()
  {
    try
    {
      redirectTo.resume();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void dispose()
  {
    try
    {
      redirectTo.dispose();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void render(float pDelta)
  {
    try
    {
      redirectTo.render(pDelta);
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void show()
  {
    try
    {
      redirectTo.show();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void hide()
  {
    try
    {
      redirectTo.hide();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

}
