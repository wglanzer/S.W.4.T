package de.swat.mapCreator;

import de.swat.common.map.Map;
import de.swat.mapCreator.gui.Window;
import de.swat.ObservableList2;

import java.awt.*;

/**
 * Hauptanfangsklasse des MapCreators
 *
 * @author W. Glanzer, 02.02.14
 */
public class MapCreator
{
  private static MapCreatorImage image = new MapCreatorImage();
  //Dieses modelAccess ist statisch, da es nur einmal vorkommen kann / darf.
  //Ebenso ist es dann möglich, von überall darauf zuzugreifen, da statisch
  private Map currentLoadedMap;
  private Window window;

  public MapCreator()
  {
    image.setMapCreator(this);
//    Map map = new Map();
//    map.setRaster(new Raster(10, new Dimension(IWindowConstants.MAX_RASTERWIDTH, IWindowConstants.MAX_RASTERHEIGHT), map));
//    currentLoadedMap = map;

    window = new Window(currentLoadedMap);
  }

  public static MapCreatorImage getMapCreatorImage()
  {
    return image;
  }

  public Map getMap()
  {
    return currentLoadedMap;
  }

  public void setMap(Map pMap)
  {
    currentLoadedMap = pMap;
    window.mapChanged(pMap);
  }

  public void clearClickedPoints()
  {
    window.getDrawContainer().clearClickedPoints();
  }

  public ObservableList2<Point> getClickedPoints()
  {
    return window.getDrawContainer().getClickedPoints();
  }
}
