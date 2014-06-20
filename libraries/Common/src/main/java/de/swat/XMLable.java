package de.swat;

import org.jdom2.Element;
import org.jetbrains.annotations.Nullable;

/**
 * Gibt an, dass ein Objekt in ein XML-Objekt zerlegt werden kann
 *
 * @author W.Glanzer, 17.06.2014.
 */
public interface XMLable
{

  /**
   * Wandelt das Objekt in XML um.
   *
   * @return XML-Element, oder <tt>null</tt>
   */
  @Nullable
  Element toXML();

  /**
   * Wandelt XML zu diesem Objekt um und befüllt ggf. die Werte
   *
   * @param pXML XML-Element
   */
  void fromXML(Element pXML);

}
