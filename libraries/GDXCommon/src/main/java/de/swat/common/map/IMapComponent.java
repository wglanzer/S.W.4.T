package de.swat.common.map;

import de.swat.ITreeable;
import de.swat.common.IActAndDrawable;
import de.swat.map.xml.components.IXMLComponent;

import java.awt.geom.Point2D;
import java.util.Set;

/**
 * Beschreibt eine Komponente der Map.
 *
 * @author W.Glanzer, 21.06.2014.
 */
public interface IMapComponent extends IActAndDrawable, ITreeable
{

  /**
   * @return Liefert den Z-Index auf dem dazugehörigen SubLayer
   */
  int getZIndex();

  /**
   * @return Liefert die Eckpunkte des Polygons der MapComponent
   */
  Set<Point2D> getPoints();

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
