package de.swat.android;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    addActor(_createClickButton());
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    // -- Größe des Linken Sticks
    int lSSize = (int) (pHeight * 0.35);
    leftStick.setBounds(10, 10, lSSize, lSSize);
    // ----
  }

  private Button _createClickButton()
  {
    final GDXTextButton clickButton = new GDXTextButton(IStaticConstants.ENABLE_FTP_SERVER, assets.getSkinDefault(), assets.getFont());
    float height = (float) (getHeight() * 0.05);
    float width = height * 32 / 9;
    float x = 0;
    float y = 0;

    clickButton.setBorder(0, 10, 0, 10);
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
