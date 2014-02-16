package de.swat.utils;

import de.swat.math.Vector2D;

import java.awt.*;

/**
 * @author Werner Glanzer, 05.10.13
 */
public class MathUtil
{
  public static Object[] getArrayColumn(Object[][] pObjects, int pColumn)
  {
    return pObjects[pColumn];
  }

  public static Object[] getArrayRow(Object[][] pObjects, int pRow)
  {
    Object[] temp = new Object[pObjects.length];
    for (int i = 0; i < pObjects.length; i++)
    {
      temp[i] = pObjects[i][pRow];
    }
    return temp;
  }

  public static Image[] getImageRow(Image[][] pObjects, int pRow)
  {
    Image[] temp = new Image[pObjects.length];
    for (int i = 0; i < pObjects.length; i++)
    {
      temp[i] = pObjects[i][pRow];
    }
    return temp;
  }

  /**
   * Gibt den Winkel zwischen 2 Vektoren zurück
   *
   * @param pVector1 Vektor 1
   * @param pVector2 Vektor 2
   * @return Rückgabewinkel in Grad
   */
  public static double getAngleBetweenVectors(Vector2D pVector1, Vector2D pVector2)
  {
    //cos = Skalarprodukt / (Betrag * Betrag)
    //
    //

    double deltaX1 = pVector1.point1.x - pVector1.point2.x;
    double deltaY1 = pVector1.point1.y - pVector1.point2.y;
    double deltaX2 = pVector2.point1.x - pVector2.point2.x;
    double deltaY2 = pVector2.point1.y - pVector2.point2.y;
    double degree = Math.toDegrees(Math.acos(((deltaX1 * deltaX2) + (deltaY1 * deltaY2)) /
                                                 (Math.sqrt((deltaX1 * deltaX1) + (deltaY1 * deltaY1)) *
                                                     Math.sqrt((deltaX2 * deltaX2) + (deltaY2 * deltaY2)))));
    if (((deltaX1 >= 0) && (deltaX2 >= 0) && (deltaY1 / deltaX1 < deltaY2 / deltaX2))
        || ((deltaX1 <= 0) && (deltaX2 <= 0) && (deltaY1 / deltaX1 < deltaY2 / deltaX2))
        || ((deltaX1 >= 0) && (deltaX2 <= 0) && (deltaY1 / deltaX1 > deltaY2 / deltaX2))
        || ((deltaX1 <= 0) && (deltaX2 >= 0) && (deltaY1 / deltaX1 > deltaY2 / deltaX2)))
    {
      return degree;
    }
    else
    {
      return 360 - degree;
    }

  }

}
