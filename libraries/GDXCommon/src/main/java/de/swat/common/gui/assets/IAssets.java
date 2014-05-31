package de.swat.common.gui.assets;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.common.gui.assets.keys.ResourceKey;
import de.swat.common.gui.assets.keys.ShaderKey;

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

  /**
   * @return Liefert den Font, der systemübergreifend verwendet werden soll
   */
  BitmapFont getFont();

  /**
   * @return Liefert einen bestimmten Shader zurück
   */
  ShaderProgram getShader(ShaderKey pKey);

  /**
   * @return Liefert eine bestimmte Resource als FileHandle zurück
   */
  FileHandle getResource(ResourceKey pKey);
}
