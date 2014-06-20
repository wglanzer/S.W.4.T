package de.swat.map.xml;

/**
 * Typen, die ein SubLayer annehmen kann
 *
 * @author W.Glanzer, 18.06.2014.
 */
public enum EXMLSubLayerType
{

  /**
   * Hintergrund.
   * Kollisionsobjekte werden hier nicht berücksichtigt.
   * Hier sollten nur Bilder hinzugefügt werden
   */
  Background,

  /**
   * Midground, Spielerebene.
   * Diese Ebene liegt auf der selben Ebene wie der Spieler.
   * Kollisionsebene
   */
  Midground,

  /**
   * Vordergrund.
   * Diese Ebene liegt vor dem Spieler.
   * Eventuell für Lampen, die über dem Spieler angezeigt werden sollen
   */
  Foreground

}
