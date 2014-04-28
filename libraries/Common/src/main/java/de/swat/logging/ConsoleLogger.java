package de.swat.logging;

/**
 * Logger, der alles auf die Console logt
 *
 * @author W.Glanzer, 28.04.2014.
 */
public class ConsoleLogger implements ILogger
{
  @Override
  public void err(String pError)
  {
    System.err.println(pError);
  }

  @Override
  public void info(String pOut)
  {
    System.out.println(pOut);
  }

  @Override
  public void debug(String pDebug)
  {
    System.out.println(pDebug);
  }

  @Override
  public void throwable(Throwable pThrowable)
  {
    pThrowable.printStackTrace(System.err);
  }
}
