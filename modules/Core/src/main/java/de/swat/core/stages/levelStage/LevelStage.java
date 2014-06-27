package de.swat.core.stages.levelStage;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.ControlEvent;
import de.swat.GlobalControlManager;
import de.swat.IControlListener;
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

  private _ControlListener listener = new _ControlListener();

  public LevelStage()
  {
    addActor(player);
    GlobalControlManager.getDefault().addControlListener(listener);
    player.setPosition(getWidth() / 2, getHeight() / 2);
    StageHandler.CONTROLSTAGE.addListener(new ClickListener()
    {
      @Override
      public boolean touchDown(InputEvent event, float x, float y, int pointer, int button)
      {
        player.getArms().showAnimation(AnimationKey.PLAYER_RECOIL, Animation.PlayMode.LOOP);
        return true;
      }

      @Override
      public void touchUp(InputEvent event, float x, float y, int pointer, int button)
      {
        Animation anim = player.getArms().getAnimation();
        if(anim != null)
          anim.stop();
      }
    });
  }

  @Override
  public void render()
  {
    getCamera().update();
    super.render();
  }

  @Override
  public void update(float pDelta)
  {
    Camera cam = getCamera();
    float moveSpeed = player.getMoveSpeed();

    cam.translate((listener.moveX * moveSpeed) * pDelta, (listener.moveY * moveSpeed) * pDelta, 0);
    cam.update();
    super.update(pDelta);
  }

  @Override
  public void dispose()
  {
    super.dispose();
    GlobalControlManager.getDefault().removeControlListener(listener);
  }

  private class _ControlListener implements IControlListener
  {
    public int moveX = 0;
    public int moveY = 0;

    @Override
    public boolean dispatchControlEvent(ControlEvent pEvent)
    {
      if(pEvent.type.equals(ControlEvent.Type.PLAYER_CONTROL))
      {
        if(pEvent.isActivated)
        {
          moveX += pEvent.modificator[0];
          moveY += pEvent.modificator[1];
        }
        else
        {
          moveX -= pEvent.modificator[0];
          moveY -= pEvent.modificator[1];
        }
      }

      // Event wird noch gebraucht!
      return false;
    }
  }
}
