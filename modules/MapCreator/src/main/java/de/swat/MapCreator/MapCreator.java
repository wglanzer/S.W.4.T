package de.swat.MapCreator;

import de.swat.*;
import de.swat.MapCreator.gui.Window;
import de.swat.accesses.MapModelAccess;
import de.swat.constants.IWindowConstants;
import de.swat.dataModels.Map.StructureCollisionObjectDataModel;
import de.swat.observableList2.ObservableList2;
import de.swat.util.DataModelHandler;

import java.awt.*;

/**
 * Hauptanfangsklasse des MapCreators
 *
 * @author W. Glanzer, 02.02.14
 */
public class MapCreator
{
  //Dieses modelAccess ist statisch, da es nur einmal vorkommen kann / darf.
  //Ebenso ist es dann möglich, von überall darauf zuzugreifen, da statisch
  private MapModelAccess modelAccess;
  private static MapCreatorImage image = new MapCreatorImage();
  private Map currentLoadedMap;
  private Window window;

  public MapCreator()
  {
    image.setMapCreator(this);
    modelAccess = (MapModelAccess) DataModelHandler.newModelAccess(MapModelAccess.class);
    Map map = new Map(modelAccess);
    modelAccess.setRaster(new Raster(10, new Dimension(IWindowConstants.MAX_RASTERWIDTH, IWindowConstants.MAX_RASTERHEIGHT), map));
    currentLoadedMap = map;

    window = new Window(currentLoadedMap);
  }

  public static MapCreatorImage getMapCreatorImage()
  {
    return image;
  }

  public void setMap(Map pMap)
  {
    currentLoadedMap = pMap;
    window.mapChanged(pMap);
  }

  public Map getMap()
  {
    return currentLoadedMap;
  }

  public void clearClickedPoints()
  {
    window.getDrawContainer().clearClickedPoints();
  }

  public ObservableList2<Point> getClickedPoints()
  {
    return window.getDrawContainer().getClickedPoints();
  }

  public void addStructureObject(StructureCollisionObjectDataModel pNewObject)
  {
    window.getDrawContainer().addStructureObject(pNewObject);
  }
}
