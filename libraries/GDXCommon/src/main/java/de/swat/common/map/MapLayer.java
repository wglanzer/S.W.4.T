package de.swat.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.common.IActAndDrawable;
import de.swat.map.xml.EXMLSubLayerType;
import de.swat.map.xml.XMLLayer;
import de.swat.map.xml.XMLSubLayer;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Layer der Map.
 * Äquivalent zu XMLLayer
 *
 * @author W.Glanzer, 21.06.2014.
 */
public class MapLayer implements IActAndDrawable
{

  public final Set<MapSubLayers> subLayers;
  public int zIndex;

  public MapLayer()
  {
    subLayers = new TreeSet<>(new Comparator<MapSubLayers>()
    {
      @Override
      public int compare(MapSubLayers pSL1, MapSubLayers pSL2)
      {
        int t1 = EXMLSubLayerType.types.indexOf(pSL1.type);
        int t2 = EXMLSubLayerType.types.indexOf(pSL2.type);
        return t1 - t2;
      }
    });
  }

  /**
   * Umwandlung aus XML -> Map
   *
   * @param pLayer  XMLLayer, aus dem generiert werden soll
   */
  public void fromXML(XMLLayer pLayer)
  {
    zIndex = pLayer.zIndex;
    for(XMLSubLayer currLayer : pLayer.subLayers)
    {
      MapSubLayers subLayer = new MapSubLayers();
      subLayer.fromXML(currLayer);
      subLayers.add(subLayer);
    }
  }

  /**
   * @return Wandelt in einen XMLLayer um
   */
  public XMLLayer toXML()
  {
    XMLLayer layer = new XMLLayer(zIndex);
    for(MapSubLayers currSubLayer : subLayers)
      layer.addSubLayer(currSubLayer.toXML());
    return layer;
  }

  @Override
  public void act(float pDelta)
  {
    for(MapSubLayers currSubLayer : subLayers)
      currSubLayer.act(pDelta);
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    for(MapSubLayers currSubLayer : subLayers)
      currSubLayer.draw(pBatch, pParentAlpha, pX, pY, pWidth, pHeight);
  }
}
