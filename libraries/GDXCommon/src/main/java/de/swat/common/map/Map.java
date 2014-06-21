package de.swat.common.map;

import de.swat.map.xml.MapFileObject;
import de.swat.map.xml.XMLLayer;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Diese Klasse repräsentiert eine Map des Spiels.
 * Diese enthält alle nötigen Informaitonen, um eine Map bereitzustellen.
 * Sie wird durch ein MapFileObject aufgebaut
 *
 * Eine Map benötigt derzeit nur die verschiedenen Layer.
 * Diese wiederrum beinhalten ihre Kinder usw.
 *
 * @author W.Glanzer, 18.06.2014.
 */
public class Map
{

  /**
   * Enthält die verschiedenen Layer.
   * Geordnet nach zIndex
   */
  private SortedSet<MapLayer> layers;

  public Map()
  {
    layers = new TreeSet<>(new Comparator<MapLayer>()
    {
      @Override
      public int compare(MapLayer pLayer1, MapLayer pLayer2)
      {
        return pLayer1.zIndex - pLayer2.zIndex;
      }
    });
  }

  /**
   * Generiert die Map aus einem FileObject
   *
   * @param pFileObject  FileObject, aus dem die Map generiert werden soll
   */
  public void fromFileObject(MapFileObject pFileObject)
  {
    for(XMLLayer currLayer : pFileObject.layers)
    {
      MapLayer layer = new MapLayer();
      layer.fromXML(currLayer);
      layers.add(layer);
    }
  }

  /**
   * Generiert das FileObject aus der Map
   *
   * @return FileObject
   */
  public MapFileObject generateFileObject()
  {
    MapFileObject fileObject = new MapFileObject();
    for(MapLayer currLayer : layers)
      fileObject.addLayer(currLayer.toXML());
    return fileObject;
  }
}
