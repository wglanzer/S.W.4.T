package de.swat.MapCreator;

import de.swat.*;
import de.swat.dataModels.Map.StructureCollisionObjectDataModel;
import de.swat.observableList2.ObservableList2;

import java.awt.*;

/**
 * @author W. Glanzer, 28.03.2014
 */
public class MapCreatorImage implements IMapCreatorImage
{
  private MapCreator mapCreator;

  protected void setMapCreator(MapCreator pMapCreator)
  {
    mapCreator = pMapCreator;
  }

  @Override
  public Map getMap()
  {
    return mapCreator.getMap();
  }

  @Override
  public void setMap(Map pMap)
  {
    mapCreator.setMap(pMap);
  }

  @Override
  public void clearClickedPoints()
  {
    mapCreator.clearClickedPoints();
  }

  @Override
  public ObservableList2<Point> getClickedPoints()
  {
    return mapCreator.getClickedPoints();
  }

  @Override
  public void addStructureObject(StructureCollisionObjectDataModel pNewObject)
  {
    mapCreator.addStructureObject(pNewObject);
  }
}
