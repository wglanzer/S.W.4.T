package de.swat.exceptions;

/**
 * Allgemeine Klasse für nicht
 * zugeordnete Exceptions.
 *
 * @author W. Glanzer, 07.12.13
 */
public class SwatRuntimeException extends AbstractRuntimeException
{

  public SwatRuntimeException(String pMessage, Exception pStackTrace)
  {
    super(pMessage, pStackTrace);
  }

}
