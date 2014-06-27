package de.swat;

import java.util.EventListener;

/**
 * Listener für einen ControlManager
 *
 * @author W.Glanzer, 26.06.2014.
 */
public interface IControlListener extends EventListener
{

  /**
   * Dispatcht ein ControlEvent. Wird hier <tt>true</tt> zurückgegeben wird
   * das Event nicht weiter behandelt.
   *
   * @param pEvent  Event, das gedispatcht wird
   * @return <tt>true</tt>, wenn das Event behandelt wurde. Andernfalls <tt>false</tt>
   */
  boolean dispatchControlEvent(ControlEvent pEvent);

}
