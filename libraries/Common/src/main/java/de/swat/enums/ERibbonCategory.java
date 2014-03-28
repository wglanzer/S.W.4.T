package de.swat.enums;

/**
 * Kategorien, die der Ribbon haben kann.
 * Diese werden derzeit als Reiter im Ribbon dargestellt
 *
 * @author W. Glanzer, 05.12.13
 */
public enum ERibbonCategory
{
  COMMON("Common", 100),
  MAP("Map", 200),
  STRUCTURE("Structure", 300),
  ENTITIES("Entities", 400),
  PATHFINDING("Pathfinding", 500);

  /**
   * Deklaration der Enums
   */
  private final String displayName;
  private final int position;

  private ERibbonCategory(final String pDisplayName, final int pPosition)
  {
    displayName = pDisplayName;
    position = pPosition;
  }

  public int getPosition()
  {
    return position;
  }

  public String getDisplayName()
  {
    return displayName;
  }
}
