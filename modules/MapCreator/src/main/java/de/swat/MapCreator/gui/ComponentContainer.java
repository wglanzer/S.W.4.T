package de.swat.MapCreator.gui;

import de.swat.enums.*;

import javax.swing.*;

/**
 * Container für JComponents mit einem bestimmten
 * EComponentType, deren
 * Kategorie und Unterkategorie. Dient dazu,
 * die SubKategorie durch die ArrayListMultiMaps
 * durchzuschleifen und diese zugreifbar zu
 * machen.
 */
public class ComponentContainer implements Comparable
{
  public final JComponent component;
  public final ERibbonComponentType componentType;
  public final ERibbonSubCategory subCategory;
  public final String name;
  public final int ID;
  public final int rowspan;

  public ComponentContainer(JComponent pComponent, ERibbonComponentType pComponentType, ERibbonSubCategory pSubCategory, String pName, int pID, int pRowspan)
  {
    component = pComponent;
    componentType = pComponentType;
    subCategory = pSubCategory;
    name = pName;
    ID = pID;
    rowspan = pRowspan;
  }

  @Override
  public int compareTo(Object o)
  {
    if(!(o instanceof ComponentContainer))
    {
      return 0;
    }
    else
    {
      ComponentContainer container = (ComponentContainer) o;
      return subCategory.ordinal() - container.subCategory.ordinal();
    }
  }
}
