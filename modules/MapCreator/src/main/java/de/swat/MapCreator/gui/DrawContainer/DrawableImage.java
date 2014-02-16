package de.swat.MapCreator.gui.DrawContainer;

import java.awt.image.BufferedImage;

/**
 * Diese Klasse stellt einen Container dar, der ein BufferedImage,
 * und dessen dazugehörige x, y und eine eindeutige ID beinhaltet.
 *
 * @author W. Glanzer, 28.11.2013
 */
public class DrawableImage
{
  public final int ID;
  public final int x;
  public final int y;
  public final BufferedImage image;

  public DrawableImage(BufferedImage pImage, int pID, int pX, int pY)
  {
    ID = pID;
    x = pX;
    y = pY;
    image = pImage;
  }

  @Override
  public boolean equals(Object o)
  {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    DrawableImage that = (DrawableImage) o;

    return ID == that.ID;
  }

  @Override
  public int hashCode()
  {
    return ID;
  }
}
