package de.swat.map.xml.dummy;

import de.swat.map.xml.EXMLSubLayerType;
import de.swat.map.xml.XMLLayer;
import de.swat.map.xml.XMLSubLayer;
import de.swat.map.xml.components.XMLPolygon;

/**
 * @author W.Glanzer, 20.06.2014.
 */
public class DummyXMLLayerGenerator
{

  /**
   * Erstellt einen Dummy-XML-Layer mit drei Ebenen.
   *
   * @return Dummy-XMLLayer
   */
  public static XMLLayer generateDummyXMLLayer(int pZIndex)
  {
    XMLLayer layer = new XMLLayer(pZIndex);
    layer.addSubLayer(_generateLayer(EXMLSubLayerType.Background));
    layer.addSubLayer(_generateLayer(EXMLSubLayerType.Midground));
    layer.addSubLayer(_generateLayer(EXMLSubLayerType.Foreground));

    return layer;
  }

  private static XMLSubLayer _generateLayer(EXMLSubLayerType pType)
  {
    XMLSubLayer subLayer = new XMLSubLayer(pType);
    subLayer.addComponent(new XMLPolygon());
    subLayer.addComponent(new XMLPolygon());
    subLayer.addComponent(new XMLPolygon());
    return subLayer;
  }
}
