package de.swat.datamodels;

import de.swat.Map;
import de.swat.annotations.DataModel;

/**
 * Datenmodell des MapCreators
 *
 * @author W. Glanzer, 19.02.14
 */
@DataModel
public class MapCreatorDataModel
{

  private Map map;

  public Map getMap()
  {
    return map;
  }

  public void setMap(Map pMap)
  {
    map = pMap;
  }
}
