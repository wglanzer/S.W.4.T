package de.swat.android.desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.core.IAssets;

/**
 * Assets die im Game verwendet werden
 *
 * @author W. Glanzer, 19.04.2014
 */
public class DesktopAssets implements IAssets
{

  public static final String SKIN_DEFAULT = "skins/defaultSkin/uiskin.json";

  @Override
  public Skin getSkinDefault()
  {
    return new Skin(Gdx.files.internal(SKIN_DEFAULT));
  }
}
