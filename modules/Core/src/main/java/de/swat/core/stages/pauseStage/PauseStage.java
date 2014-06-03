package de.swat.core.stages.pauseStage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.IFileStructure;
import de.swat.clientserverintercom.server.IServer;
import de.swat.common.gui.assets.keys.ShaderKey;
import de.swat.common.gui.components.GDXLabel;
import de.swat.common.gui.components.GDXTextButton;
import de.swat.common.stages.AbstractStage;
import de.swat.common.stages.StageHandler;
import de.swat.constants.IStaticConstants;
import de.swat.fileTransfer.FileTransferServer;

import java.io.IOException;

/**
 * Pause-Stage
 *
 * @author W.Glanzer, 31.05.2014.
 */
public class PauseStage extends AbstractStage
{
  private ShapeRenderer renderer = new ShapeRenderer();

  public PauseStage()
  {
    addActor(_createEnableWifiButton());
    addActor(_createBackButton());
    addActor(_createFPSLabel());
  }

  private Label _createFPSLabel()
  {
    GDXLabel fpsLabel = new GDXLabel("FPS: ", assets.getSkinDefault())
    {
      @Override
      public void draw(SpriteBatch batch, float parentAlpha)
      {
        setText("FPS: " + Gdx.graphics.getFramesPerSecond());
        super.draw(batch, parentAlpha);
      }
    };
    fpsLabel.setPosition(getWidth() - 37 - fpsLabel.getWidth(), 0);
    fpsLabel.setFontShader(assets.getShader(ShaderKey.FONT));

    return fpsLabel;
  }

  private Button _createBackButton()
  {
    final GDXTextButton backButton = new GDXTextButton(IStaticConstants.BACK, assets.getSkinDefault(), assets.getFont(), true, assets.getFontScale());
    float height = (float) (getHeight() * 0.05);
    float width = height * 32 / 9;
    float x = 10;
    float y = 10;

    backButton.setTextShader(assets.getShader(ShaderKey.FONT));
    backButton.setBounds(x, y, width, height);
    backButton.addListener(new ClickListener()
    {
      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        removeFromHandler();
        getGuiStage().addToHandler(StageHandler.StageType.CONTROLSTAGE);
      }
    });
    return backButton;
  }

  /**
   * Liefert den "enableWifi"-Button, der den FTP-Server startet!
   *
   * @return enableWifi-Button
   */
  private Button _createEnableWifiButton()
  {
    final GDXTextButton enableWifi = new GDXTextButton(IStaticConstants.ENABLE_FTP_SERVER, assets.getSkinDefault(), assets.getFont(), true, assets.getFontScale());
    float height = (float) (getHeight() * 0.05);
    float width = height * 32 / 9;
    float x = getWidth() / 2 - width / 2;
    float y = getHeight() - 100;

    enableWifi.setTextShader(assets.getShader(ShaderKey.FONT));
    enableWifi.setBounds(x, y, width, height);
    enableWifi.addListener(new ClickListener()
    {
      private boolean isClicked = false;
      private IServer server;

      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        isClicked = !isClicked;

        if(isClicked)
        {
          enableWifi.setText(IStaticConstants.DISABLE_FTP_SERVER);

          logger.info("FileTransferServer starting...");
          server = new FileTransferServer(assets.getFilesDir().getPath() + IFileStructure.MAPS);
          logger.info("FileTransferServer started!");
        }
        else
        {
          enableWifi.setText(IStaticConstants.ENABLE_FTP_SERVER);

          try
          {
            server.stop();
          }
          catch(IOException e)
          {
            logger.catching(e);
          }
        }
      }
    });

    return enableWifi;
  }

  @Override
  public void draw()
  {
    Gdx.gl.glEnable(GL10.GL_BLEND);
    Gdx.gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
    renderer.begin(ShapeRenderer.ShapeType.Filled);
    renderer.setColor(new Color(0, 0, 0, 0.7f));
    renderer.rect(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    renderer.end();
    Gdx.gl.glDisable(GL10.GL_BLEND);

    super.draw();
  }

}
