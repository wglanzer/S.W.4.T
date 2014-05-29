package de.swat;

/**
 * Wird geworfen, wenn keine Verbindung verfügbar ist
 *
 * @author W.Glanzer, 28.05.2014.
 */
public class NoConnectionException extends RuntimeException
{

  public NoConnectionException()
  {
    super("No Connection available!");
  }
}
