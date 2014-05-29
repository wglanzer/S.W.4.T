package de.swat.utils;

/**
 * @author W. Glanzer, 19.04.2014
 */
public class StringUtil
{

  /**
   * Konvertiert einen Stacktrace (bestehend aus StackTraceElement[]) in einen String.
   * Kann wahlweise auch nur die ersten paar Zeilen auslesen
   *
   * @param pThrowable StackTrace
   * @param pNumbers  Anzahl der Zeilen die ausgelesen werden sollen. <tt>-1</tt> für keine Begrenzung
   * @return StackTrace-String
   */
  public static String convertStacktraceToString(Throwable pThrowable, int pNumbers)
  {
    StackTraceElement[] stackTrace = pThrowable.getStackTrace();
    String returnString = "";
    int count = pNumbers == -1 ? stackTrace.length : pNumbers;

    String message = pThrowable.toString();
    returnString += message + getLineSeperator();

    for(int i = 0; i < count; i++)
    {
      StackTraceElement currElement = stackTrace[i];
      returnString += getTabSymbol() + currElement.toString() + getLineSeperator();
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

  /**
   * Liefert das Symbol, mit dem ein Text eingerückt werden kann
   * (Ähnlich wie \t, dieser funktioniert allerdings bei LibGDX nicht)
   *
   * @return Tab-Symbol
   */
  public static String getTabSymbol()
  {
    return "    ";
  }

  /**
   * Gibt zurück, wie oft pCounter in pComplete vorkommt.
   *
   * @param pComplete  Langer String, in dem pCounter vorkommen soll
   * @param pCounter   String, der in pComplete vorkommen soll
   * @return Wie oft kommt pCounter in pComplete vor?
   */
  public static int countNumber(String pComplete, String pCounter)
  {
    int number = 0;

    for(int offset = 0; offset < pComplete.length(); )
    {
      int currIndex = pComplete.indexOf(pCounter, offset);
      if(currIndex != -1)
      {
        number++;
        offset = currIndex + pCounter.length();
      }
      else
        break;
    }

    return number;
  }
}
