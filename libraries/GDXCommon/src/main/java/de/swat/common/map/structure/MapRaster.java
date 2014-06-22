package de.swat.common.map.structure;

import de.swat.common.map.IMapComponent;

import java.util.HashSet;
import java.util.Set;

/**
 * Ein Teil einer Map, der ein kleineres Raster darstellt.
 * Siehe Struktur.png. Dieser enthält alle BoundingBoxes,
 * die auf ihm liegen. Komplett oder Teilweise...
 *
 * @author W.Glanzer, 22.06.2014.
 */
public class MapRaster
{

  private Set<MapComponentBoundingBox> boundingBoxes = new HashSet<>();

  public MapRaster()
  {
  }

  /**
   * Fügt eine MapComponent zum Raster hinzu.
   * Die Boundingbox darauf wird in eine Liste gespeichert und
   * für den schnellen Zugriff bereitgehalten
   *
   * @param pComponent  Komponente, die hinzugefügt werden soll
   */
  public void addMapComponent(IMapComponent pComponent)
  {
    boundingBoxes.add(new MapComponentBoundingBox(pComponent));
  }

  /**
   * @return Liefert die Boundingboxes zurück, die das Raster komplett
   * oder nur teilweise enthält
   */
  public Set<MapComponentBoundingBox> getBoundingBoxes()
  {
    return boundingBoxes;
  }
}
