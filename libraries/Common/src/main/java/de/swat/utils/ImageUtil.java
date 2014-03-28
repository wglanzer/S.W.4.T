package de.swat.utils;

import de.swat.exceptions.SwatRuntimeException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Utility-Klasse für Bilder
 *
 * @author W. Glanzer, 05.12.13
 */
public final class ImageUtil
{

  /**
   * Lädt ein BufferedImage anhand eines Files
   *
   * @param pFile Bild, das geladen werden soll
   * @return BufferedImage
   */
  public static BufferedImage loadFileAsImage(File pFile)
  {
    try
    {
      return ImageIO.read(pFile);
    }
    catch (IOException e)
    {
      throw new SwatRuntimeException("Could not find specific file: " + pFile.getPath(), e);
    }
  }

}

