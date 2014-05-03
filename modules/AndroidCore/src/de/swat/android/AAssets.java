package de.swat.android;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.core.IAssets;

/**
 * Assets die im Game verwendet werden
 *
 * @author W. Glanzer, 19.04.2014
 */
public class AAssets implements IAssets
{
  private static AAssets INSTANCE = new AAssets();

  public static AAssets get()
  {
    return INSTANCE;
  }

  @Override
  public Skin getSkinDefault()
  {
    return new Skin(Gdx.files.internal("skins/defaultSkin/uiskin.json"));
  }
}
