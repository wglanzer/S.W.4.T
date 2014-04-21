package de.swat.android.constants;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.android.AAssets;
import de.swat.constants.IStaticConstants;

/**
 * Alle Konstanten, die statisch verwendet werden können.
 * %1, %2, etc. werden als Platzhalter verwendet
 *
 * @author W. Glanzer, 18.04.2014
 */
public interface AIStaticConstants extends IStaticConstants
{

  //Dialoge
  public static final String DIALOG_ERROR_TITLE = "ERROR: %1";
  public static final String DIALOG_ERROR_MESSAGE = "An error occurred.\nPlease see your console and contact the developers!";
  public static final String DIALOG_BTN_OK = "OK";
  public static final String DIALOG_BTN_DETAILS = "Details...";

  //Skins
  public static final Skin SKIN_DEFAULT = new Skin(Gdx.files.internal(AAssets.SKIN_DEFAULT));

}
