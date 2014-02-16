package de.swat.utils;

import de.swat.exceptions.SwatRuntimeException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public final class ImageUtil
{

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

