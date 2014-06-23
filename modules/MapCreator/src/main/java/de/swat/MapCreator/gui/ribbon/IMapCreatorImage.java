package de.swat.mapCreator.gui.ribbon;

import de.swat.common.map.Map;

/**
 * Bezeichnet ein Image des MapCreators.
 * Dieses Image ist dazu da, um GUI und Daten sauber trennen
 * zu können
 *
 * @author W. Glanzer, 28.03.2014
 */
public interface IMapCreatorImage
{

  /**
   * @return Die Map, die der MapCreator grade erstellt bzw
   * die er grade geladen hat
   */
  public Map getMap();

  /**
   * Setzt die Map, die der MapCreator gerade erstellt
   *
   * @param pMap  Map, die gesetzt werden soll
   */
  public void setMap(Map pMap);

}
