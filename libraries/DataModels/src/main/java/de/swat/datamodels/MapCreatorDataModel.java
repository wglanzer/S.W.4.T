package de.swat.datamodels;

import de.swat.*;
import de.swat.annotations.DataModel;

/**
 * Datenmodell des MapCreators
 *
 * @author W. Glanzer, 19.02.14
 */
@DataModel
public class MapCreatorDataModel
{

  private MapCreatorMap mapCreatorMap;

  public MapCreatorMap getMapCreatorMap()
  {
    return mapCreatorMap;
  }

  public void setMapCreatorMap(MapCreatorMap pMapCreatorMap)
  {
    mapCreatorMap = pMapCreatorMap;
  }
}
