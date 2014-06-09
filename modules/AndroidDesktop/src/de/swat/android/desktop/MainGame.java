package de.swat.android.desktop;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.IStageChangeListener;
import de.swat.common.stages.StageHandler;
import de.swat.core.stages.MainScreen;
import de.swat.core.stages.levelStage.LevelStage;

/**
 * @author W. Glanzer, 19.04.2014
 */
public class MainGame extends Game
{
  @Override
  public void create()
  {
    StageHandler.addStageChangeListener(new IStageChangeListener()
    {
      @Override
      public void stageChanged(StageHandler.StageType pStageType, Stage pStage)
      {
        if(pStageType.equals(StageHandler.StageType.CONTROLSTAGE))
          Gdx.input.setInputProcessor(pStage);
      }
    });

    GuiStage guiStage = new GuiStage();
    guiStage.addToHandler(StageHandler.StageType.CONTROLSTAGE);
    AbstractStage.setGuiStage(guiStage);
    LevelStage levelStage = new LevelStage();
    levelStage.addToHandler(StageHandler.StageType.MIDGROUND);
    setScreen(new MainScreen());
  }
}
