package de.swat;

/**
 * Kapselung für die Java-Internen Insets, da es diese auf Android nicht gibt
 *
 * @author W.Glanzer, 30.05.2014.
 */
public class Insets
{

  public int top = 0;
  public int left = 0;
  public int bottom = 0;
  public int right = 0;

  public Insets(int pTop, int pLeft, int pBottom, int pRight)
  {
    top = pTop;
    left = pLeft;
    bottom = pBottom;
    right = pRight;
  }
}
