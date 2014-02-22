package de.swat.enums;

/**
 * @author W. Glanzer, 05.12.13
 */
public enum ERibbonCategory
{
  COMMON("Common", 100),
  Components("Components", 200);

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
