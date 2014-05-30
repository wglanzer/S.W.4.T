package de.swat.android;

import android.os.Bundle;
import android.view.WindowManager;
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
    AAssets.initalize(getExternalFilesDir(null));

    // Bringt den Screen dazu, nicht dunkler zu werden und zum sleepmode zu gehen
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    // Blendet die ActionBar aus
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useGL20 = true;
    initialize(new AMainGame(), config);
  }
}

