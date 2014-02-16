package de.swat.MapCreator;

import de.swat.Map;
import de.swat.MapCreator.gui.Window;

/**
 * @author W. Glanzer, 02.02.14
 */
public class MapCreator
{

  public MapCreator()
  {
    Map map = new Map();
    Window window = new Window(map);
  }

}
