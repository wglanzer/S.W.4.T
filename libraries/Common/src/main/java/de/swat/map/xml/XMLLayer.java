package de.swat.map.xml;

import de.swat.XMLable;
import de.swat.exceptions.SwatRuntimeException;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.HashSet;
import java.util.Set;

/**
 * Layer, der Background, Midground und Foreground enthält
 *
 * @author W.Glanzer, 17.06.2014.
 */
public class XMLLayer implements XMLable
{

  public static final String ATR_Z = "z";
  private Set<XMLSubLayer> subLayers = new HashSet<>();
  private int zIndex = 0;

  public XMLLayer(int pZIndex)
  {
    zIndex = pZIndex;
  }

  public void addSubLayer(XMLSubLayer pLayer)
  {
    subLayers.add(pLayer);
  }

  @Override
  public Element toXML()
  {
    Element element = new Element("layer");
    element.setAttribute(ATR_Z, String.valueOf(zIndex));

    for(XMLSubLayer currLayer : subLayers)
      element.addContent(currLayer.toXML());

    return element;
  }

  @Override
  public void fromXML(Element pXML)
  {
    //Z-Index
    Attribute zAttr = pXML.getAttribute(ATR_Z);
    if(zAttr == null)
      throw new SwatRuntimeException("XMLLayer has no z-index", null);
    String zVal = zAttr.getValue();
    zIndex = Integer.parseInt(zVal); //Wenns ihn hier schmeißt, dann merkts der Benutzer sowieso

    //XMLSubLayers
    for(Element currChild : pXML.getChildren())
    {
      XMLSubLayer subLayer = new XMLSubLayer(null);
      subLayer.fromXML(currChild);
      subLayers.add(subLayer);
    }
  }
}
