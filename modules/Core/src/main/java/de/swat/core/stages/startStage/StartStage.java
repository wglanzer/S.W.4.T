package de.swat.core.stages.startStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.IFileStructure;
import de.swat.clientserverintercom.server.IServer;
import de.swat.constants.IStaticConstants;
import de.swat.core.AbstractStage;
import de.swat.core.CorePreferences;
import de.swat.fileTransfer.FileTransferServer;

import java.io.IOException;

/**
 * Haupt-Stage
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class StartStage extends AbstractStage
{

  public StartStage()
  {
    addActor(_createEnableWifiButton());
  }

  /**
   * Liefert den "enableWifi"-Button, der den FTP-Server startet!
   *
   * @return enableWifi-Button
   */
  private TextButton _createEnableWifiButton()
  {
    final TextButton enableWifi = new TextButton(IStaticConstants.ENABLE_FTP_SERVER, CorePreferences.getAssets().getSkinDefault());
    float height = (float) (getHeight() * 0.1);
    float width = height * 16 / 9;
    float x = getWidth() - width - 10;
    float y = getHeight() - height - 10;

    enableWifi.getLabel().setFontScale(3);
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
}
