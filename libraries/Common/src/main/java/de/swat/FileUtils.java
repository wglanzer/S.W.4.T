package de.swat;

import org.apache.logging.log4j.LogManager;

import java.io.File;

/**
 * @author W.Glanzer, 04.05.2014.
 */
public class FileUtils
{

  /**
   * @return Liefert das FilesDir zurück (Desktop), in dem Daten gespeichert werden dürfen
   */
  public static File getFilesDir()
  {
    File directory = new File(System.getProperty("user.home") + File.separator + ".sw4t");

    if(!directory.exists())
      if(!directory.mkdir())
        LogManager.getLogger().error("Could not create folder in your userhome!");

    return directory;
  }

}
