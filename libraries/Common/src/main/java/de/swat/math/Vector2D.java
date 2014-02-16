package de.swat.math;

/**
 * @author W. Glanzer, 30.11.13
 */
public class Vector2D
{

  public final Point2D point1;
  public final Point2D point2;

  public Vector2D()
  {
    point1 = new Point2D(0.0D, 0.0D);
    point2 = new Point2D(0.0D, 0.0D);
  }

  public Vector2D(Point2D pPoint1, Point2D pPoint2)
  {
    point1 = pPoint1;
    point2 = pPoint2;
  }

  public Point2D getPoint1()
  {
    return point1;
  }

  public Point2D getPoint2()
  {
    return point2;
  }
}
