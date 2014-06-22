package de.swat.common.map;

import de.swat.SwatRuntimeException;
import de.swat.map.xml.EXMLSubLayerType;
import de.swat.map.xml.MapFileObject;
import de.swat.map.xml.XMLLayer;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Diese Klasse repräsentiert eine Map des Spiels.
 * Diese enthält alle nötigen Informaitonen, um eine Map bereitzustellen.
 * Sie wird durch ein MapFileObject aufgebaut
 * <p/>
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
    layers.add(new MapLayer());
  }

  /**
   * Fügt eine Komponente auf der Map ein.
   *
   * @param pComponent    Komponente, die hinzugefügt werden soll
   * @param pSubLayerType Typ des SubLayers, auf dem sie hinzugefügt werden soll
   * @param pLayerIndex   Index des Layers, auf dem sie hinzugefügt werden soll
   */
  public void addComponent(IMapComponent pComponent, EXMLSubLayerType pSubLayerType, int pLayerIndex)
  {
    int counter = 0;
    for(MapLayer currLayer : layers)
    {
      if(counter == pLayerIndex)
      {
        MapSubLayer sLayer = currLayer.getSubLayer(pSubLayerType);
        if(sLayer == null)
          throw new SwatRuntimeException("Could not find valid SubLayer (type=" + pSubLayerType.name() + ")", null);

        sLayer.addComponent(pComponent);
        return;
      }
      else
        counter++;
    }

    throw new SwatRuntimeException("Could not add component. Maybe layerindex is too high? (index=" + pLayerIndex + ")", null);
  }

  /**
   * Generiert die Map aus einem FileObject
   *
   * @param pFileObject FileObject, aus dem die Map generiert werden soll
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
