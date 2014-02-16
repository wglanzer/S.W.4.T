package de.swat.core.Utility;

/**
 * @author Werner Glanzer, 17.10.13
 */
public class Log
{

  public static void log(Object... pObjects)
  {
    for (Object currObject : pObjects)
      System.out.println(currObject.toString() + ", ");

  }

}
