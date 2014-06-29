package de.swat.entity;

import de.swat.IWeapon;

/**
 * Gibt an, dass ein Entity eine Waffe haben kann
 *
 * @author W.Glanzer, 29.06.2014.
 */
public interface IWeaponable
{

  /**
   * Liefert die aktuell ausgerüstete Waffe
   *
   * @return Die gerade ausgerüstete Waffe
   */
  IWeapon getCurrentWeapon();

}
