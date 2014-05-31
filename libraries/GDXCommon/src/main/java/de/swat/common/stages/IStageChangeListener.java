package de.swat.common.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * Listener der darauf horcht, dass sich die Stages im StageHandler ändern
 *
 * @author W.Glanzer, 31.05.2014.
 */
public interface IStageChangeListener
{

  void stageChanged(StageHandler.StageType pStageType, Stage pStage);

}
