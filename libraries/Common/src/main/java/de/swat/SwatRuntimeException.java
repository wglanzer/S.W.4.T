package de.swat;

import org.jetbrains.annotations.Nullable;

/**
 * Allgemeine Klasse für nicht
 * zugeordnete Exceptions.
 *
 * @author W. Glanzer, 07.12.13
 */
public class SwatRuntimeException extends AbstractRuntimeException
{

  public SwatRuntimeException(String pMessage, @Nullable Exception pStackTrace)
  {
    super(pMessage, pStackTrace);
  }

}
