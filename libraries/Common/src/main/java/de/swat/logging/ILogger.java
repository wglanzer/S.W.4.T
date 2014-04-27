package de.swat.logging;

/**
 * Beschreibt einen Logger
 *
 * @author W. Glanzer, 17.04.2014
 */
public interface ILogger
{
  public static final String ERROR = "ERROR";
  public static final String INFO = "INFO";
  public static final String DEBUG = "DEBUG";

  /**
   * Gibt eine Error-Meldung aus
   *
   * @param pError Error-Meldung
   */
  void err(String pError);

  /**
   * Gibt eine normale Meldung aus
   *
   * @param pOut normale Meldung
   */
  void info(String pOut);

  /**
   * Gibt eine debug-Meldung aus
   *
   * @param pDebug debug-Meldung
   */
  void debug(String pDebug);

  /**
   * Gibt ein throwable-Objekt aus
   *
   * @param pThrowable Throwable
   */
  void throwable(Throwable pThrowable);

}
