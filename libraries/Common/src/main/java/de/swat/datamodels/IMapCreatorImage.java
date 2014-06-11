package de.swat.datamodels;

import de.swat.datamodels.map.StructureCollisionObjectDataModel;
import de.swat.observableList2.ObservableList2;

import java.awt.*;

/**
 * @author W. Glanzer, 28.03.2014
 */
public interface IMapCreatorImage
{

  public Map getMap();

  public void setMap(Map pMap);

  public void clearClickedPoints();

  public ObservableList2<Point> getClickedPoints();

  public void addStructureObject(StructureCollisionObjectDataModel pNewObject);
}
