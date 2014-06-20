package de.swat.map.xml.components;

import org.jdom2.Element;

/**
 * Factory, die alle XMLComponents erzeugen kann
 *
 * @author W.Glanzer, 20.06.2014.
 */
public class XMLComponentFactory
{



  /**
   * Kann eine XML-Komponente anhand eines XML-Elementes wiederherstellen
   *
   * @param pXML  XML-Element aus dem die Komponente wiederhergestellt werden soll
   * @return XMLKomponente
   */
  public static IXMLComponent reviveComponent(Element pXML)
  {
    return null; //todo
  }

}
