package de.swat.core;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.File;

/**
 * Assets-Referenz
 *
 * @author W.Glanzer, 03.05.2014.
 */
public interface IAssets
{

  /**
   * @return den Skin, den das Programm haben soll
   */
  Skin getSkinDefault();

  /**
   * @return Liefert ein Directory, das einen Ordner für das Game repräsentiert
   */
  File getFilesDir();

}
