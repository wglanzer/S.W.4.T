package de.swat.android.desktop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.ControlEvent;
import de.swat.GlobalControlManager;
import de.swat.IControlManager;
import de.swat.IEvent;
import de.swat.common.gui.assets.keys.ShaderKey;
import de.swat.common.gui.components.GDXTextButton;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.StageHandler;
import de.swat.constants.IStaticConstants;
import de.swat.core.stages.pauseStage.PauseStage;

/**
 * Stage für die Desktop-Gui
 *
 * @author W.Glanzer, 31.05.2014.
 */
public class GuiStage extends AbstractStage
{

  private AbstractStage pauseStage = new PauseStage();

  public GuiStage()
  {
    addActor(_createClickButton());
    addListener(new InputListener()
    {
      private final IControlManager manager = GlobalControlManager.getDefault();

      @Override
      public boolean keyDown(InputEvent event, int keycode)
      {
        ControlEvent fireable = null;

        switch(keycode)
        {
          case IControlSheet.MOVE_FORWARD:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, true, 0, 1);
            break;

          case IControlSheet.MOVE_BACKWARD:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, true, 0, -1);
            break;

          case IControlSheet.MOVE_LEFT:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, true, -1, 0);
            break;

          case IControlSheet.MOVE_RIGHT:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, true, 1, 0);
            break;
        }

        if(fireable != null)
          manager.fireControlEvent(fireable);

        return fireable != null;
      }

      @Override
      public boolean keyUp(InputEvent event, int keycode)
      {
        ControlEvent fireable = null;

        switch(keycode)
        {
          case IControlSheet.MOVE_FORWARD:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, false, 0, 1);
            break;

          case IControlSheet.MOVE_BACKWARD:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, false, 0, -1);
            break;

          case IControlSheet.MOVE_LEFT:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, false, -1, 0);
            break;

          case IControlSheet.MOVE_RIGHT:
            fireable = new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, false, 1, 0);
            break;
        }

        if(fireable != null)
          manager.fireControlEvent(fireable);

        return fireable != null;
      }
    });
  }

  private Button _createClickButton()
  {
    final GDXTextButton clickButton = new GDXTextButton("  " + IStaticConstants.PAUSE + "  ", assets.getSkinDefault(), assets.getFont(), true, assets.getFontScale());
    //    float height = (float) (getHeight() * 0.05);
    //    float width = height * 32 / 9;
    float x = getWidth() - clickButton.getPrefWidth() - 20;
    float y = getHeight() - clickButton.getPrefHeight() - 8;

    clickButton.setTextShader(assets.getShader(ShaderKey.FONT));
    clickButton.setPosition(x, y);
    clickButton.addListener(new ClickListener()
    {
      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        if(pauseStage.isAddedToHandler())
        {
          pauseStage.removeFromHandler();
          addToHandler(StageHandler.StageType.CONTROLSTAGE);
        }
        else
        {
          addToHandler(StageHandler.StageType.BACKGROUND);
          pauseStage.addToHandler(StageHandler.StageType.CONTROLSTAGE);
        }
      }
    });

    return clickButton;
  }
}
