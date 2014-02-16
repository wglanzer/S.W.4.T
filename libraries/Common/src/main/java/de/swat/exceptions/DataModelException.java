package de.swat.exceptions;

/**
 * Exception, die beim Laden des
 * DatenModells auftreten kann.
 *
 * @author W. Glanzer, 07.12.13
 */
public class DataModelException extends AbstractRuntimeException
{

  public DataModelException(String pMessage, Exception pStackTrace)
  {
    super(pMessage, pStackTrace);
  }

}
