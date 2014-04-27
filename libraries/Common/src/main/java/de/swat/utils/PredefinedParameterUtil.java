package de.swat.utils;

import de.swat.constants.IStartArguments;

import java.util.Properties;

/**
 * Enthält alle Parameter, die am Anfang der VM übergeben wurden
 *
 * @author W. Glanzer, 29.03.2014
 */
public class PredefinedParameterUtil
{

  private static boolean debugMode;

  static {
    Properties properties = System.getProperties();

    debugMode = properties.containsKey(IStartArguments.DEBUG);
  }

  public static boolean isDebugMode()
  {
    return debugMode;
  }

}
