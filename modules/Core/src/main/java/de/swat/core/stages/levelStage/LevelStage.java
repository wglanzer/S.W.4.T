package de.swat.core.stages.levelStage;

import de.swat.common.stages.AbstractStage;
import de.swat.entity.entities.Player;

/**
 * Stage, auf dem die Levels angezeigt werden.
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class LevelStage extends AbstractStage
{

  private Player player = new Player();

  public LevelStage()
  {
    addActor(player);
  }

}
