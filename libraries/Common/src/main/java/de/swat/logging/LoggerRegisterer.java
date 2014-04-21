package de.swat.logging;

import de.swat.constants.IStaticConstants;
import de.swat.utils.StringUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * Hier können sich Logger zentral registrieren,
 * um einen zentralen Zugriffspunkt zu bekommen.
 *
 * @author W. Glanzer, 17.04.2014
 */
public class LoggerRegisterer
{

  private static final Set<ILogger> loggerSet = new HashSet<>();

  /**
   * Registriert einen Logger
   *
   * @param pLogger Logger, der registriert werden soll
   */
  public static void register(ILogger pLogger)
  {
    synchronized(loggerSet)
    {
      loggerSet.add(pLogger);
      System.out.println(StringUtil.replacePlaceholder(IStaticConstants.LOGGERREGISTERER_LOGGER_REGISTERED, loggerSet.size()));
    }
  }

  /**
   * Entfernt einen Logger
   *
   * @param pLogger Logger, der entfernt werden soll
   */
  public static void unregister(ILogger pLogger)
  {
    synchronized(loggerSet)
    {
      loggerSet.remove(pLogger);
      System.out.println(StringUtil.replacePlaceholder(IStaticConstants.LOGGERREGISTERER_LOGGER_UNREGISTERED, loggerSet.size()));
    }
  }

  /**
   * Normale Ausgabe. Selbes wie info()
   *
   * @see de.swat.logging.LoggerRegisterer#info(String)
   */
  public static void out(String pString)
  {
    info(pString);
  }

  /**
   * Gibt einen String mit dem Prefix [INFO] aus
   */
  public static void info(String pString)
  {
    synchronized(loggerSet)
    {
      for(ILogger currLogger : loggerSet)
        currLogger.info(pString);
    }
  }

  /**
   * Gibt einen String mit dem Prefix [DEBUG] aus
   */
  public static void debug(String pString)
  {
    synchronized(loggerSet)
    {
      for(ILogger currLogger : loggerSet)
        currLogger.debug(pString);
    }
  }

  /**
   * Gibt einen String mit dem Prefix [ERROR] aus
   */
  public static void err(String pString)
  {
    synchronized(loggerSet)
    {
      for(ILogger currLogger : loggerSet)
        currLogger.err(pString);
    }
  }

  /**
   * Gibt ein Throwable aus
   */
  public static void throwable(Throwable pThrowable)
  {
    synchronized(loggerSet)
    {
      for(ILogger currLogger : loggerSet)
        currLogger.throwable(pThrowable);
    }
  }
}
