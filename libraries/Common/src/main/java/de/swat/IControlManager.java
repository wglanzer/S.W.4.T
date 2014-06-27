package de.swat;

/**
 * Beschreibt eine Schnittstelle, um auf Steuerungsevent zugreifen zu können
 *
 * @author W.Glanzer, 26.06.2014.
 */
public interface IControlManager
{

  /**
   * Fügt einen ControlListener hinzu
   *
   * @param pListener  ControlListener, der hinzugefügt werden soll
   */
  void addControlListener(IControlListener pListener);

  /**
   * Entfernt einen ControlListener
   *
   * @param pListener  ControlListener, der entfernt werden soll
   */
  void removeControlListener(IControlListener pListener);

  /**
   * Feuert ein ControlEvent an die Listener
   *
   * @param pEvent  Event, das gefeuert werden soll
   */
  void fireControlEvent(ControlEvent pEvent);

}
