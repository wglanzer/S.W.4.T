package de.swat.exceptions;

import org.jetbrains.annotations.Nullable;

import java.io.PrintStream;

/**
 * Vorlage für Exceptions
 *
 * @author W. Glanzer, 07.12.13
 */
abstract class AbstractRuntimeException extends RuntimeException
{
  private ShowState howToPrintStackTrace = ShowState.REAL_MESSAGE;
  private Exception stackTrace = null;

  protected AbstractRuntimeException(String pMessage, @Nullable Exception pStackTrace)
  {
    this(pMessage, pStackTrace, ShowState.REAL_MESSAGE);
  }

  protected AbstractRuntimeException(String pMessage, @Nullable Exception pStackTrace, ShowState pShowState)
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
        if (stackTrace != null)
        {
          s.print("caused by: ");
          stackTrace.printStackTrace(s);
        }
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
