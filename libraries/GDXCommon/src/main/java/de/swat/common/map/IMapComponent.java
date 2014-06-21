package de.swat.common.map;

import de.swat.common.IActAndDrawable;
import de.swat.map.xml.components.IXMLComponent;

/**
 * Beschreibt eine Komponente der Map.
 *
 * @author W.Glanzer, 21.06.2014.
 */
public interface IMapComponent extends IActAndDrawable
{

  /**
   * @return Liefert den Z-Index auf dem dazugehörigen SubLayer
   */
  int getZIndex();

  /**
   * @return Umwandlung in eine XMLKomponente.
   */
  IXMLComponent toXML();

  /**
   * Umwandlung aus einer XMLKomponente
   *
   * @param pComponent  XMLKomponente
   */
  void fromXML(IXMLComponent pComponent);

}
