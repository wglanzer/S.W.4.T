package de.swat.utils;

import de.swat.constants.IStaticConstants;

/**
 * @author W. Glanzer, 19.04.2014
 */
public class StringUtil
{

  /**
   * Ersetzt die Platzhalter in einem String mit den übergebenen Werten
   *
   * @param pToReplace   String, der replaced werden soll (Bspw: Test %1 successfull)
   * @param pReplaceWith Array, das die ersetzten Werte darstellt
   * @return String
   */
  public static String replacePlaceholder(String pToReplace, Object... pReplaceWith)
  {
    String placeholder = IStaticConstants.STRING_PLACEHOLDER;
    String returnString = pToReplace;

    if(pReplaceWith == null || pToReplace == null)
      return pToReplace;

    for(int i = 0; i < pReplaceWith.length; i++)
    {
      String replaceWith = "";
      if(pReplaceWith[i] != null)
        replaceWith = pReplaceWith[i].toString();
      returnString = returnString.replaceAll(placeholder + (i + 1), replaceWith);
    }

    return returnString;
  }

  /**
   * Konvertiert einen Stacktrace (bestehend aus StackTraceElement[]) in einen String.
   * Kann wahlweise auch nur die ersten paar Zeilen auslesen
   *
   * @param pElements StackTrace
   * @param pNumbers  Anzahl der Zeilen die ausgelesen werden sollen. <tt>-1</tt> für keine Begrenzung
   * @return StackTrace-String
   */
  public static String convertStacktraceToString(StackTraceElement[] pElements, int pNumbers)
  {
    String returnString = "";
    int count = pNumbers == -1 ? pElements.length : pNumbers;

    for(int i = 0; i < count; i++)
    {
      StackTraceElement currElement = pElements[i];
      returnString += currElement.toString() + getLineSeperator();
    }

    return returnString;
  }

  /**
   * Liefert den System-Abhängigen Line-Seperator (Windows: \n)
   *
   * @return Line-Seperator
   */
  public static String getLineSeperator()
  {
    String prop = System.getProperty("line.separator");
    return prop == null || prop.isEmpty() ? "\r\n" : prop;
  }
}
