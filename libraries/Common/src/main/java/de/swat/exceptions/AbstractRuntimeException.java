package de.swat.exceptions;

import java.io.PrintStream;

/**
 * Vorlage für Exceptions
 *
 * @author W. Glanzer, 07.12.13
 */
abstract class AbstractRuntimeException extends RuntimeException
{
  private ShowState howToPrintStackTrace = ShowState.FAKE_MESSAGE;
  private Exception stackTrace = null;

  protected AbstractRuntimeException(String pMessage, Exception pStackTrace)
  {
    this(pMessage, pStackTrace, ShowState.REAL_MESSAGE);
  }

  protected AbstractRuntimeException(String pMessage, Exception pStackTrace, ShowState pShowState)
  {
    super(pMessage);
    stackTrace = pStackTrace;
    howToPrintStackTrace = pShowState;
  }

  @Override
  public void printStackTrace(PrintStream s)
  {
    switch (howToPrintStackTrace)
    {
      case FAKE_MESSAGE:
        super.printStackTrace(s);
        break;

      case REAL_MESSAGE:
        super.printStackTrace(s);
        s.print("caused by: ");
        stackTrace.printStackTrace(s);
        break;

      case NONE:
        //Tut nichts, da NONE
        break;
    }
  }

  public static enum ShowState
  {
    REAL_MESSAGE,
    FAKE_MESSAGE,
    NONE
  }
}
