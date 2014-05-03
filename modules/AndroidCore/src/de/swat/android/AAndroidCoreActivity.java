package de.swat.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import de.swat.core.CorePreferences;

public class AAndroidCoreActivity extends AndroidApplication
{

  @Override
  public void onCreate(Bundle pSavedInstanceState)
  {
    super.onCreate(pSavedInstanceState);

    CorePreferences.setAssets(AAssets.get());

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useGL20 = true;
    initialize(new AMainGame(), config);
  }
}

