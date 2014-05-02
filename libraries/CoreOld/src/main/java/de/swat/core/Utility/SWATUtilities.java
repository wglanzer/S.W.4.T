package de.swat.core.Utility;

//import org.newdawn.slick.Image;

import java.awt.*;

/**
 * @author Werner Glanzer, 05.10.13
 */
public class SWATUtilities
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
   * Überprüft, ob zwei Vektoren kollidieren
   *
   * @param a1 Fußpunkt Vektor 1
   * @param a2 Spitze Vektor 1
   * @param b1 Fußpunkt Vektor 2
   * @param b2 Spitze Vektor 2
   * @return Gibt zurück, ob sich die gegebenen Vektoren schneiden
   */
  public static boolean lineSegmentCollision(Point a1, Point a2, Point b1, Point b2)
  {
    float denom = (b2.y - b1.y) * (a2.x - a1.x) - (b2.x - b1.x) * (a2.y - a1.y);
    if (Math.abs(denom) < 0.00000001)
      return false;

    float ua = ((b2.x - b1.x) * (a1.y - b1.y) -
        (b2.y - b1.y) * (a1.x - b1.x)) / denom;
    float ub = ((a2.x - a1.x) * (a1.y - b1.y) -
        (a2.y - a1.y) * (a1.x - b1.x)) / denom;
    return ua >= 0 && ua <= 1 && ub >= 0 && ub <= 1;
  }

  /**
   * Überprüft, an welchem Punkt zwei Vektoren kollidieren (by Alex Biederer)
   *
   * @param a1 Fußpunkt Vektor 1
   * @param a2 Spitze Vektor 1
   * @param b1 Fußpunkt Vektor 2
   * @param b2 Spitze Vektor 2
   * @return Gibt den Schnittpunkt beider Vektoren zurück
   */
  public static Point getVectorCollision(Point a1, Point a2, Point b1, Point b2)
  {
    boolean lineSegmentCollision = lineSegmentCollision(a1, a2, b1, b2);
    int x;
    int y;

    //  y = m * x + t
    //  m = (x2-x1)/(y2-y1)
    //  t = y1 - (x2-x1)/(y2-y1) * x1
    //  y = (x2-x1)/(y2-y1) * x + (y1 - (x2-x1)/(y2-y1) * x1)
    //  x = (t2-t1)/(m1-m2)

    if (!lineSegmentCollision)
      return null;

    if (a1.x == a2.x)
    {                                                 //Falls Vector 1 parallel zu y-Achse

      float m2 = ((b2.y - b1.y) / (b2.x - b1.x));
      float t2 = (b1.y - m2 * b1.x);
      x = a1.x;
      y = (int) (m2 * x + t2);
      return new Point(x, y);

    }
    else if (b1.x == b2.x)
    {                                                 //Falls Vector 2 parallel zu y-Achse

      float m1 = ((a2.y - a1.y) / (a2.x - a1.x));
      float t1 = (a1.y - m1 * a1.x);
      x = b1.x;
      y = (int) (m1 * x + t1);
      return new Point(x, y);

    }
    else
    {

      float m1 = ((a2.y - a1.y) / (a2.x - a1.x));     //Steigung Vektor 1
      float m2 = ((b2.y - b1.y) / (b2.x - b1.x));     //Steigung Vektor 2
      float t1 = (a1.y - m1 * a1.x);                  //Additive Konstante Vektor 1
      float t2 = (b1.y - m2 * b1.x);                  //Additive Konstante Vektor 2

      x = (int) ((t2 - t1) / (m1 - m2));              //Schnittpunktkoordinate x durch gleichsetzen
      y = (int) (m1 * x + t1);                        //Schnittpunktkoordinate y durch einsetzen
      return new Point(x, y);
    }
  }
}
