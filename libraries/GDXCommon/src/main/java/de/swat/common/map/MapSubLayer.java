package de.swat.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.common.IActAndDrawable;
import de.swat.common.map.structure.MapComponentBoundingBox;
import de.swat.common.map.structure.MapRaster;
import de.swat.constants.IStaticConstants;
import de.swat.map.xml.EXMLSubLayerType;
import de.swat.map.xml.XMLSubLayer;
import de.swat.map.xml.components.IXMLComponent;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Unterlayer, eines MapLayers.
 * Diese können Background, Midground oder Foreground sein.
 * Er enhält alle Komponenten, die auf dem Layer verfügbar sein sollen
 * und bietet einen schnellen Zugriff durch die Speicherstruktur mit
 * Raster u.Ä.
 *
 * @author W.Glanzer, 21.06.2014.
 */
public class MapSubLayer implements IActAndDrawable
{

  public EXMLSubLayerType type;
  public MapRaster[][] rasters;
  private Set<IMapComponent> allComponents;

  public MapSubLayer()
  {
    this(null);
  }

  public MapSubLayer(EXMLSubLayerType pType)
  {
    type = pType;

    allComponents = new TreeSet<>(new Comparator<IMapComponent>()
    {
      @Override
      public int compare(IMapComponent pComp1, IMapComponent pComp2)
      {
        return pComp1.getZIndex() - pComp2.getZIndex();
      }
    });

    //Raster-Array erstellen
    int countWidth = (int) Math.ceil((double) IStaticConstants.MAP_WIDTH / (double) IStaticConstants.RASTER_SIZE);
    int countHeight = (int) Math.ceil((double) IStaticConstants.MAP_HEIGHT / (double) IStaticConstants.RASTER_SIZE);
    rasters = new MapRaster[countWidth][countHeight];
    for(int x = 0; x < countWidth; x++)
      for(int y = 0; y < countHeight; y++)
        rasters[x][y] = new MapRaster();
  }

  /**
   * Fügt eine MapComponent zum SubLayer hinzu.
   * Diese wird dann auch zum Raster hinzugefügt, um schnellen
   * ZUgriff zu gewähren
   *
   * @param pComponent  Komponente die hinzugefügt werden soll
   */
  public void addComponent(IMapComponent pComponent)
  {
    allComponents.add(pComponent);
    for(MapRaster currRaster : _rasterOfComponent(pComponent))
      currRaster.addMapComponent(pComponent);
  }

  /**
   * Liefert alle Raster, die die MapComponent enthalten können
   *
   * @param pComponent  MapComponent, auf die geprüft wird
   * @return Ein Set aus MapRasters
   */
  private Set<MapRaster> _rasterOfComponent(IMapComponent pComponent)
  {
    Set<MapRaster> returnSet = new HashSet<>();
    MapComponentBoundingBox tempBox = new MapComponentBoundingBox(pComponent);

    int startX = (int) (tempBox.x / IStaticConstants.RASTER_SIZE);
    int startY = (int) (tempBox.y / IStaticConstants.RASTER_SIZE);
    int endX = (int) ((tempBox.width + tempBox.x) / IStaticConstants.RASTER_SIZE);
    int endY = (int) ((tempBox.height + tempBox.y) / IStaticConstants.RASTER_SIZE);

    for(int x = startX; x <= endX; x++)
      for(int y = startY; y <= endY; y++)
        returnSet.add(rasters[x][y]);

    return returnSet;
  }

  /**
   * Umwandlung XML -> Map
   *
   * @param pSubLayer SubLayer, aus dem umgewandelt werden soll
   */
  public void fromXML(XMLSubLayer pSubLayer)
  {
    type = pSubLayer.type;
    for(IXMLComponent currComp : pSubLayer.children)
      addComponent(MapComponentFactory.convertToMapComponent(currComp));
  }

  /**
   * Umwandlung Map -> XML
   *
   * @return XMLSubLayer
   */
  public XMLSubLayer toXML()
  {
    XMLSubLayer subLayer = new XMLSubLayer(type);
    for(IMapComponent currComp : allComponents)
      subLayer.addComponent(currComp.toXML());
    return subLayer;
  }

  @Override
  public void act(float pDelta)
  {
    for(IMapComponent currComp : allComponents)
      currComp.act(pDelta);
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    for(IMapComponent currComp : allComponents)
      currComp.draw(pBatch, pParentAlpha, pX, pY, pWidth, pHeight);
  }
}
