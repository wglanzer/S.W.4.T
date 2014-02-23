package de.swat.enums;

/**
 * @author W. Glanzer, 06.12.13
 */
public enum ERibbonSubCategory
{
  FILE("File", 100),
  MAP("Map", 200);


  /**
   * Deklaration der Enums
   */
  private final String displayName;
  private final int position;

  private ERibbonSubCategory(final String pDisplayName, final int pPosition)
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
