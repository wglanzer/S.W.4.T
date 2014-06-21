package de.swat.utils;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * @author W.Glanzer, 21.06.2014.
 */
public class StreamUtil
{

  /**
   * Führt das Runnable ohne System.err aus.
   * Diese Meldungen werden dann verschluckt und können nicht ausgegeben werden
   *
   * @param pRunnable  Runnable, das ausgeführt werden soll
   */
  public static void executeWithoutErrPrintStream(Runnable pRunnable)
  {
    PrintStream oldErr = System.err;
    System.setErr(new PrintStream(new OutputStream()
    {
      public void write(int b)
      {
      }
    }));

    pRunnable.run();

    System.setErr(oldErr);
  }

}
