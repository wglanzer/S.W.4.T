package de.swat;

import de.swat.enums.*;

import javax.swing.*;

/**
 * Bezeichnet die Actions im Ribbon.
 * Diese werden dann per Reflection aufgerufen
 *
 * @author W. Glanzer, 18.02.2014
 */
public interface IRibbonAction extends IAction
{
  /**
   * Führt sich aus, wenn auf den Button gedrückt wurde
   */
  void performAction();

  /**
   * @return Liefert das Icon zurück, das die Action haben soll
   */
  Icon getIcon();

  /**
   * @return Titel, bzw. Caption, des Buttons
   */
  String getTitle();

  /**
   * @return Position des Commandbuttons im vergleich dann zu anderen
   */
  int getPosition();

  /**
   * @return Haupt-Kategorie, in die der Button einzuordnen ist
   */
  ERibbonCategory getCategory();

  /**
   * @return Unter-Kategorie, in die der Button einzuordnen ist
   */
  ERibbonSubCategory getSubCategory();
}
