package de.swat;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Beschreibt eine Waffe
 *
 * @author W.Glanzer, 29.06.2014.
 */
public interface IWeapon
{

  /**
   * Liefert die Feuerrate in RPS (Rounds per second)
   *
   * @return Feuerrate
   */
  int getFirerate();

  /**
   * Feuermodus, mit der die Waffe abgefeuert werden kann
   *
   * @return Liste aus Feuermodi, die die Waffe haben kann
   */
  @NotNull
  List<EFireMode> getFireMode();


  public enum EFireMode
  {
    SEMI,
    AUTO
  }

}
