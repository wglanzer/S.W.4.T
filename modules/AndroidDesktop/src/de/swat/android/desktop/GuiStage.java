package de.swat.android.desktop;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
