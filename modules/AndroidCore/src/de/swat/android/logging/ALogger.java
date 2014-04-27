package de.swat.android.logging;

import de.swat.android.dialog.ADialogDisplayer;
import de.swat.constants.IStaticConstants;
import de.swat.logging.ILogger;

import java.io.PrintStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Logger für Android
 *
 * @author W. Glanzer, 17.04.2014
 */
public class ALogger implements ILogger
{

  private PrintStream normalPrint = System.out;
  private PrintStream errorPrint = System.err;

  @Override
  public void err(String pError)
  {
    errorPrint.println(_buildString(ERROR, pError));
  }

  @Override
  public void info(String pOut)
  {
    normalPrint.println(_buildString(INFO, pOut));
  }

  @Override
  public void debug(String pDebug)
  {
    normalPrint.println(_buildString(DEBUG, pDebug));
  }

  @Override
  public void throwable(Throwable pThrowable)
  {
    ADialogDisplayer.errorDialog(pThrowable);
    pThrowable.printStackTrace(normalPrint);
  }

  /**
   * Fügt einen String dieser Art zusammen: 08:30:59 [INFO]: Hier DummyText
   */
  private String _buildString(String pState, String pMessage)
  {
    return MessageFormat.format(IStaticConstants.OUT_PREFIX, _getTime(), pState, pMessage);
  }

  private String _getTime()
  {
    Calendar cal = Calendar.getInstance();
    cal.getTime();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    return sdf.format(cal.getTime());
  }
}
