package de.swat.common;

/**
 * Gibt an, dass ein Objekt "acten" kann
 *
 * @author W.Glanzer, 20.06.2014.
 */
public interface IActable
{

  /**
   * Wird aufgerufen, wenn sich das Entity
   * "acten" soll.
   *
   * @param pDelta  Zeit seit dem letzen Frame
   */
  void act(float pDelta);

}
