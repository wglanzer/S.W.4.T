package de.swat.core.stages.levelStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.common.gui.animation.Animation;
import de.swat.common.gui.assets.keys.AnimationKey;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.StageHandler;
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
    player.setPosition(getWidth() / 2, getHeight() / 2);
    StageHandler.CONTROLSTAGE.addListener(new ClickListener()
    {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
      {
        player.showAnimation(AnimationKey.PLAYER_RECOIL, Animation.PlayMode.HALT);
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button)
      {
        player.getAnimation().stop();
      }
    });
  }

}
