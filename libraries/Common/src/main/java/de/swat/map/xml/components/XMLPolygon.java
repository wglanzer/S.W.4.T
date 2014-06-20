package de.swat.map.xml.components;

import org.jdom2.Element;

/**
 * Erweitert java.awt.Polygon um die XML-Eigenschaft
 *
 * @author W.Glanzer, 17.06.2014.
 */
public class XMLPolygon extends java.awt.Polygon implements IXMLComponent
{

  @Override
  public Element toXML()
  {
    return null;
  }

  @Override
  public void fromXML(Element pXML)
  {

  }
}
