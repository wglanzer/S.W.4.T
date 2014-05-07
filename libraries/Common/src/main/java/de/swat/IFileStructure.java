package de.swat;

import java.io.File;

/**
 * Datei-Struktur
 *
 * @author W.Glanzer, 03.05.2014.
 */
public interface IFileStructure
{

  public static final String SEPERATOR = File.separator;

  /**
   * Speicherort der Maps innerhalb des IAssets.getFilesDir()
   */
  public static final String MAPS = SEPERATOR + "maps";

}
