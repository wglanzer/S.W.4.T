package de.swat;

import java.util.HashSet;
import java.util.Set;

/**
 * ControlManager
 *
 * @author W.Glanzer, 26.06.2014.
 */
public class GlobalControlManager implements IControlManager
{

  private static final IControlManager controlManagerReference = new GlobalControlManager();

  private final Set<IControlListener> listeners = new HashSet<>();

  /**
   * Liefert den ControlManager
   *
   * @return ControlManager
   */
  public static IControlManager getDefault()
  {
    return controlManagerReference;
  }

  @Override
  public void addControlListener(IControlListener pListener)
  {
    synchronized(listeners)
    {
      listeners.add(pListener);
    }
  }

  @Override
  public void removeControlListener(IControlListener pListener)
  {
    synchronized(listeners)
    {
      listeners.remove(pListener);
    }
  }

  @Override
  public void fireControlEvent(ControlEvent pEvent)
  {
    synchronized(listeners)
    {
      for(IControlListener currListener : listeners)
      {
        boolean bool = currListener.dispatchControlEvent(pEvent);
        if(bool)
          return;
      }
    }
  }

}
