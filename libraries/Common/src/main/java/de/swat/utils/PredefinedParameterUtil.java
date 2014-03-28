package de.swat.utils;

/**
 * Enthält alle Parameter, die am Anfang der VM übergeben wurden
 *
 * @author W. Glanzer, 29.03.2014
 */
public class PredefinedParameterUtil
{

  private static boolean debugMode = false;

  public static boolean isDebugMode()
  {
    return debugMode;
  }

  public static void setDebugMode(boolean pDebugMode)
  {
    debugMode = pDebugMode;
  }
}
