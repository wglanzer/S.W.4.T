package de.swat.core.stages.startStage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import de.swat.core.AbstractStage;
import de.swat.core.CorePreferences;

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
    enableWifi.setChecked(true);
    enableWifi.addListener(new ClickListener()
    {
      private boolean wasClickedBefore = false;

      @Override
      public void clicked(InputEvent event, float x, float y)
      {
        //Dann Server starten
        if(!wasClickedBefore)
        {
          enableWifi.setText("disable Wifi");
        }
        else
        {
          enableWifi.setText("enable Wifi");
        }

        wasClickedBefore = !wasClickedBefore;
      }
    });

    addActor(enableWifi);
  }
}
