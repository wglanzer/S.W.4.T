package de.swat.android;

import android.os.Bundle;
import com.badlogic.gdx.backends.android.*;

public class AndroidCoreActivity extends AndroidApplication
{

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    config.useGL20 = true;
    initialize(new AndroidApplicationListener(), config);
  }

}

