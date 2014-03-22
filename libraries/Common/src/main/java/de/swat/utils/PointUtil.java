package de.swat.utils;

import de.swat.math.*;
import de.swat.observableList2.ObservableList2;

import java.awt.*;

/**
 * @author W. Glanzer, 01.12.13
 */
public class PointUtil
{

  /**
   * Prüft, ob zwei Punkte im Abstand des proxRadiuses
   * aneinander liegen
   *
   * @param pPoint1     Punkt1
   * @param pPoint2     Punkt2
   * @param pProxRadius ProxRadius
   * @return Abstand Punkt1 -> Punkt2 < proxRadius
   */
  public static boolean checkProximity(Point pPoint1, Point pPoint2, int pProxRadius)
  {
    double diffX = pPoint1.x - pPoint2.x;
    double diffY = pPoint1.y - pPoint2.y;
    return (diffX * diffX + diffY * diffY) < pProxRadius;
  }

  /**
   * Gibt den Punkt zurück, der auf der Winkelhalbierenden von den Vektoren pPoint1 -> pPoint2 und pPoint3 -> pPoint2
   * liegt und von pPoint2 den Abstand pRadius hat
   *
   * @param pPoint1 Punkt 1
   * @param pPoint2 Punkt 2 (Mittelpunkt)
   * @param pPoint3 Punkt 3
   * @param pRadius Entfernungsradius
   * @return Rückgabepunkt
   */
  public static Point getEdgePoint(Point pPoint1, Point pPoint2, Point pPoint3, int pRadius)
  {
    double deltaAngle = MathUtil.getAngleBetweenVectors(new Vector2D(new Point2D(pPoint1.x, pPoint1.y), new Point2D(pPoint2.x, pPoint2.y)), new Vector2D(new Point2D(pPoint3.x, pPoint3.y), new Point2D(pPoint2.x, pPoint2.y)));

    //System.out.println(deltaAngle);
    double vectorAngle = MathUtil.getAngleBetweenVectors(new Vector2D(new Point2D(pPoint1.x, pPoint1.y), new Point2D(pPoint2.x, pPoint2.y)), new Vector2D(new Point2D(0, 0), new Point2D(-1, 0)));

    double angle = vectorAngle - (deltaAngle / 2) - 180;


    if (angle >= 360)
    {
      angle = angle - 360;
    }
    if (angle < 0)
    {
      angle = angle + 360;
    }

    double radAngle = Math.tan(Math.toRadians(angle));

    //System.out.println(angle);
    //System.out.println(angle + "   " + radAngle);
    //System.out.println(Math.tan(Math.toRadians(90)));


    double pointX;
    double pointY;

    if (angle <= 90 || angle > 270)
    {
      pointX = Math.sqrt((double) (pRadius * pRadius) / ((radAngle * radAngle) + 1));
      pointY = -(radAngle * pointX);
    }
    else
    {
      pointX = -Math.sqrt((double) (pRadius * pRadius) / ((radAngle * radAngle) + 1));
      pointY = -(radAngle * pointX);
    }

    return new Point((int) pointX + pPoint2.x, (int) pointY + pPoint2.y);
  }

  /**
   * Gibt die Länge einer Struktur zurück
   *
   * @param pPointList Die zu überprüfende Struktur
   * @return Die Länge der Struktur
   */
  public static double getLengthFromStructure(ObservableList2<Point> pPointList)
  {
    double returnValue = 0;
    for (int i = 1; i < pPointList.size(); i++)
    {
      returnValue = returnValue + pPointList.get(i).distance(pPointList.get(i - 1));
    }
    return returnValue;
  }
}
