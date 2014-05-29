package de.swat.core.stages.startStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.IFileStructure;
import de.swat.core.AbstractStage;
import de.swat.core.CorePreferences;
import de.swat.fileTransfer.FileTransferServer;

/**
 * Haupt-Stage
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class StartStage extends AbstractStage
{

  private TextButton enableWifi = new TextButton("enable Wifi", CorePreferences.getAssets().getSkinDefault());

  public StartStage()
  {
    enableWifi.setBounds(getWidth() - 100 - 5, getHeight() - 40 - 5, 100, 40);
    enableWifi.addListener(new ClickListener()
    {

      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        enableWifi.setText("you can't disable!");

        logger.info("FileTransferServer starting...");
        new FileTransferServer(assets.getFilesDir().getPath() + IFileStructure.MAPS);
        logger.info("FileTransferServer started!");
      }
    });

    addActor(enableWifi);
  }
}
