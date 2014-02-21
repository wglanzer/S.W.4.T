package de.swat;

import de.swat.enums.*;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Bezeichnet die Actions im Ribbon.
 * Diese werden dann per Reflection aufgerufen
 *
 * @author W. Glanzer, 18.02.2014
 */
public interface IRibbonAction extends IAction, Comparable
{
  public static final int SMALL = 1;
  public static final int MEDIUM = 2;
  public static final int LARGE = 3;

  /**
   * Führt sich aus, wenn auf den Button gedrückt wurde
   *
   * @param pSourceEvent ActionEvent vom ActionListener
   * @param pInvoker     Repräsentiert den JCommandButton, auf den gedrückt wurde.
   */
  void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IModelAccess pModelAccess);

  /**
   * @return Liefert das Icon zurück, das die Action haben soll
   */
  @Nullable
  ResizableIcon getIcon();

  /**
   * @return Titel, bzw. Caption, des Buttons
   */
  String getTitle();

  /**
   * @return Position des Commandbuttons im vergleich dann zu anderen
   */
  int getPosition();

  /**
   * @return Gibt die Größe des CommandButtons zurück, SMALL, MEDIUM oder LARGE
   */
  int getSize();

  /**
   * @return Haupt-Kategorie, in die der Button einzuordnen ist
   */
  ERibbonCategory getCategory();

  /**
   * @return Unter-Kategorie, in die der Button einzuordnen ist
   */
  ERibbonSubCategory getSubCategory();

  @Override
  int compareTo(Object o);
}