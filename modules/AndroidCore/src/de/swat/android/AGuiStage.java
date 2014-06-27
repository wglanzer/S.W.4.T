package de.swat.android;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.ControlEvent;
import de.swat.GlobalControlManager;
import de.swat.IControlManager;
import de.swat.IEvent;
import de.swat.android.gui.controls.TouchpadImpl;
import de.swat.common.gui.assets.keys.ShaderKey;
import de.swat.common.gui.components.GDXTextButton;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.StageHandler;
import de.swat.constants.IStaticConstants;
import de.swat.core.stages.pauseStage.PauseStage;

/**
 * Gui/HUD für Android
 *
 * @author W.Glanzer, 31.05.2014.
 */
public class AGuiStage extends AbstractStage
{

  private Touchpad leftStick = new TouchpadImpl().getTouchpad();
  private AbstractStage pauseStage = new PauseStage();

  public AGuiStage()
  {
    addActor(leftStick);
    addActor(_createPauseButton());
    addActor(_createReloadButton());

    leftStick.addListener(new ChangeListener()
    {
      private final IControlManager manager = GlobalControlManager.getDefault();

      @Override
      public void changed(ChangeEvent event, Actor actor)
      {
        manager.fireControlEvent(new ControlEvent(ControlEvent.Type.PLAYER_CONTROL, IEvent.PLAYER_MOVE, true, leftStick.getKnobPercentX(), leftStick.getKnobPercentY()));
      }
    });
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    super.resize(pWidth, pHeight);

    // -- Größe des Linken Sticks
    int lSSize = (int) (pHeight * 0.35);
    leftStick.setBounds(10, 10, lSSize, lSSize);
    // ----
  }

  private Button _createReloadButton()
  {
    final GDXTextButton clickButton = new GDXTextButton(IStaticConstants.RELOAD, assets.getSkinDefault(), assets.getFont(), true, assets.getFontScale());
    float height = (float) (getHeight() * 0.05);
    float width = height * 32 / 9;
    float x = getWidth() - width - 10;
    float y = getHeight() - 210 - 10;

    clickButton.setTextShader(assets.getShader(ShaderKey.FONT));
    clickButton.setBounds(x, y, width, height);
    clickButton.addListener(new ClickListener()
    {
      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        GlobalControlManager.getDefault().fireControlEvent(new ControlEvent(ControlEvent.Type.PLAYER_ACTION, IEvent.PLAYER_RELOAD, true, null));
      }
    });

    return clickButton;
  }

  private Button _createPauseButton()
  {
    final GDXTextButton clickButton = new GDXTextButton(IStaticConstants.PAUSE, assets.getSkinDefault(), assets.getFont(), true, assets.getFontScale());
    float height = (float) (getHeight() * 0.05);
    float width = height * 32 / 9;
    float x = getWidth() - width - 10;
    float y = getHeight() - height - 10;

    clickButton.setTextShader(assets.getShader(ShaderKey.FONT));
    clickButton.setBounds(x, y, width, height);
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
