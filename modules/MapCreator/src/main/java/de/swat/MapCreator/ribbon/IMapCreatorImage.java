package de.swat.mapCreator.ribbon;

import de.swat.common.map.Map;
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

}
