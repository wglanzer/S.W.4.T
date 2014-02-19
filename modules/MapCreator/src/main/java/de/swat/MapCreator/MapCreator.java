package de.swat.MapCreator;

import de.swat.Map;
import de.swat.MapCreator.gui.Window;
import de.swat.accesses.MapCreatorModelAccess;

/**
 * Hauptanfangsklasse des MapCreators
 *
 * @author W. Glanzer, 02.02.14
 */
public class MapCreator
{
  //Dieses modelAccess ist statisch, da es nur einmal vorkommen kann / darf.
  //Ebenso ist es dann möglich, von überall darauf zuzugreifen, da statisch
  private static MapCreatorModelAccess modelAccess = new MapCreatorModelAccess();

  public MapCreator()
  {
    Map map = new Map();
    modelAccess.setMap(map);

    Window window = new Window(modelAccess);
  }

  public static MapCreatorModelAccess getMapCreatorModelAccess()
  {
    return modelAccess;
  }
}
