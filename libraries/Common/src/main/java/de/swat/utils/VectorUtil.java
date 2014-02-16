package de.swat.utils;

import de.swat.math.Point2D;

import java.awt.*;

/**
 * Utility-Klasse für Vector-Objekte
 *
 * @author W. Glanzer, 30.11.13
 */
public class VectorUtil
{
  private VectorUtil()
  {
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
  public static boolean isVectorCollision(Point2D a1, Point2D a2, Point2D b1, Point2D b2)
  {
    double denom = (b2.y - b1.y) * (a2.x - a1.x) - (b2.x - b1.x) * (a2.y - a1.y);
    if (Math.abs(denom) < 0.00000001)
      return false;

    double ua = ((b2.x - b1.x) * (a1.y - b1.y) -
        (b2.y - b1.y) * (a1.x - b1.x)) / denom;
    double ub = ((a2.x - a1.x) * (a1.y - b1.y) -
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
  public static Point getVectorCollision(Point2D a1, Point2D a2, Point2D b1, Point2D b2)
  {
    double x;
    double y;

    //  y = m * x + t
    //  m = (x2-x1)/(y2-y1)
    //  t = y1 - (x2-x1)/(y2-y1) * x1
    //  y = (x2-x1)/(y2-y1) * x + (y1 - (x2-x1)/(y2-y1) * x1)
    //  x = (t2-t1)/(m1-m2)

    if (!isVectorCollision(a1, a2, b1, b2))
      return null;

    if (a1.x == a2.x)
    {
      //Falls Vector 1 parallel zu y-Achse
      double m2 = ((b2.y - b1.y) / (b2.x - b1.x));
      double t2 = (b1.y - m2 * b1.x);
      x = (a1.x);
      y = (m2 * x + t2);
      return new Point((int) Math.round(x), (int) Math.round(y));

    }
    else if (b1.x == b2.x)
    {
      //Falls Vector 2 parallel zu y-Achse
      double m1 = ((a2.y - a1.y) / (a2.x - a1.x));
      double t1 = (a1.y - m1 * a1.x);
      x = (b1.x);
      y = (m1 * x + t1);
      return new Point((int) Math.round(x), (int) Math.round(y));

    }
    else
    {

      double m1 = ((a2.y - a1.y) / (a2.x - a1.x));     //Steigung Vektor 1
      double m2 = ((b2.y - b1.y) / (b2.x - b1.x));     //Steigung Vektor 2
      double t1 = (a1.y - m1 * a1.x);                  //Additive Konstante Vektor 1
      double t2 = (b1.y - m2 * b1.x);                  //Additive Konstante Vektor 2

      x = ((t2 - t1) / (m1 - m2));              //Schnittpunktkoordinate x durch gleichsetzen
      y = (m1 * x + t1);                        //Schnittpunktkoordinate y durch einsetzen
      return new Point((int) Math.round(x), (int) Math.round(y));

    }
  }
}
