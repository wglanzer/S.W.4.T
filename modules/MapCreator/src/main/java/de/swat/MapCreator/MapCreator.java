package de.swat.MapCreator;

import de.swat.*;
import de.swat.MapCreator.gui.Window;
import de.swat.accesses.MapModelAccess;
import de.swat.constants.IWindowConstants;
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
  private static MapModelAccess modelAccess = new MapModelAccess();

  public MapCreator()
  {
    modelAccess = (MapModelAccess) DataModelHandler.newModelAccess(MapModelAccess.class);
    modelAccess.setRaster(new Raster(10, new Dimension(IWindowConstants.MAX_RASTERWIDTH, IWindowConstants.MAX_RASTERHEIGHT), new Map(modelAccess)));

    new Window(modelAccess);
  }

}
