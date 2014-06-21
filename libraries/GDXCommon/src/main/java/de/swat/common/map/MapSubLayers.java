package de.swat.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.common.IActAndDrawable;
import de.swat.map.xml.EXMLSubLayerType;
import de.swat.map.xml.XMLSubLayer;
import de.swat.map.xml.components.IXMLComponent;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Unterlayer, eines MapLayers.
 * Diese können Background, Midground oder Foreground sein
 *
 * @author W.Glanzer, 21.06.2014.
 */
public class MapSubLayers implements IActAndDrawable
{

  public EXMLSubLayerType type;
  public Set<IMapComponent> components;

  public MapSubLayers()
  {
    components = new TreeSet<>(new Comparator<IMapComponent>()
    {
      @Override
      public int compare(IMapComponent pComp1, IMapComponent pComp2)
      {
        return pComp1.getZIndex() - pComp2.getZIndex();
      }
    });
  }

  /**
   * Umwandlung XML -> Map
   *
   * @param pSubLayer  SubLayer, aus dem umgewandelt werden soll
   */
  public void fromXML(XMLSubLayer pSubLayer)
  {
    type = pSubLayer.type;
    for(IXMLComponent currComp : pSubLayer.children)
      components.add(MapComponentFactory.convertToMapComponent(currComp));
  }

  /**
   * Umwandlung Map -> XML
   *
   * @return XMLSubLayer
   */
  public XMLSubLayer toXML()
  {
    XMLSubLayer subLayer = new XMLSubLayer(type);
    for(IMapComponent currComp : components)
      subLayer.addComponent(currComp.toXML());
    return subLayer;
  }

  @Override
  public void act(float pDelta)
  {
    for(IMapComponent currComp : components)
      currComp.act(pDelta);
  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
  {
    for(IMapComponent currComp : components)
      currComp.draw(pBatch, pParentAlpha, pX, pY, pWidth, pHeight);
  }
}
