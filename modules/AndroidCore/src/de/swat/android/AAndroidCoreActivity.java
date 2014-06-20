package de.swat.android;

import android.os.Bundle;
import android.view.WindowManager;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import de.swat.common.stages.CorePreferences;

import java.io.File;

public class AAndroidCoreActivity extends AndroidApplication
{

  private static final int SAMPLES_4_MSAA = 8;

  @Override
  public void onCreate(Bundle pSavedInstanceState)
  {
    super.onCreate(pSavedInstanceState);

    CorePreferences.setAssets(AAssets.get());
    File extFileDir = getExternalFilesDir(null);
    AAssets.initalize(extFileDir != null ? extFileDir : getFilesDir());

    // Bringt den Screen dazu, nicht dunkler zu werden und zum sleepmode zu gehen
    getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    // Blendet die ActionBar aus
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);

    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useGL20 = true;
    config.numSamples = SAMPLES_4_MSAA;

    initialize(new AMainGame(), config);
  }
}

