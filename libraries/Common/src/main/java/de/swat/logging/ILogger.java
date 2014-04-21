package de.swat.logging;

/**
 * Beschreibt einen Logger
 *
 * @author W. Glanzer, 17.04.2014
 */
public interface ILogger
{
  public static final String PREFIX = "[";
  public static final String POSTFIX = "]";
  public static final String DELIMITER = ":";

  public static final String ERROR = PREFIX + "ERROR" + POSTFIX + DELIMITER;
  public static final String INFO = PREFIX + "INFO" + POSTFIX + DELIMITER;
  public static final String DEBUG = PREFIX + "DEBUG" + POSTFIX + DELIMITER;

  public static final String SPACE = " ";

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
