package de.swat.map.xml;

import de.swat.XMLable;
import de.swat.exceptions.SwatRuntimeException;
import de.swat.map.xml.components.IXMLComponent;
import de.swat.map.xml.components.XMLComponentFactory;
import org.jdom2.Attribute;
import org.jdom2.Element;

import java.util.HashSet;
import java.util.Set;

/**
 * Unterkategorie des Layers.
 * Entweder Background, Midground oder Foreground
 *
 * @author W.Glanzer, 17.06.2014.
 */
public class XMLSubLayer implements XMLable
{

  public static final String ATR_TYPE = "type";
  public EXMLSubLayerType type = EXMLSubLayerType.Background;
  public Set<IXMLComponent> children = new HashSet<>();

  public XMLSubLayer(EXMLSubLayerType pType)
  {
    type = pType;
  }

  public void addComponent(IXMLComponent pComponent)
  {
    children.add(pComponent);
  }

  @Override
  public Element toXML()
  {
    Element element = new Element("sublayer");
    element.setAttribute(ATR_TYPE, type.name());
    for(IXMLComponent currChildren : children)
    {
      Element ele = currChildren.toXML();
      if(ele != null)
        element.addContent(ele);
    }
    return element;
  }

  @Override
  public void fromXML(Element pXML)
  {
    //SubLayerType bestimmten
    Attribute atrType = pXML.getAttribute(ATR_TYPE);
    if(atrType == null)
      throw new SwatRuntimeException("XMLSubLayer-Type could not be null", null);
    String value = atrType.getValue();
    try
    {
      type = EXMLSubLayerType.valueOf(value);
    }
    catch(IllegalArgumentException e)
    {
      throw new SwatRuntimeException("XMLSubLayer-Type invalid (type=" + value + ")", null);
    }

    //Komponenten
    for(Element currChild : pXML.getChildren())
      children.add(XMLComponentFactory.reviveComponent(currChild));
  }

}
