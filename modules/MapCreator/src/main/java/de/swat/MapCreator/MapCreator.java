package de.swat.MapCreator;

import de.swat.MapCreator.gui.Window;
import de.swat.constants.IWindowConstants;
import de.swat.datamodels.Map;
import de.swat.datamodels.Raster;
import de.swat.datamodels.accesses.MapModelAccess;
import de.swat.datamodels.map.StructureCollisionObjectDataModel;
import de.swat.datamodels.util.DataModelHandler;
import de.swat.observableList2.ObservableList2;

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
  private MapModelAccess modelAccess;
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

  public void addStructureObject(StructureCollisionObjectDataModel pNewObject)
  {
    window.getDrawContainer().addStructureObject(pNewObject);
  }
}
