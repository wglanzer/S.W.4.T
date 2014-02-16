package de.swat.math;

/**
 * @author W. Glanzer, 30.11.13
 */
public class Point2D
{
  public final double x;
  public final double y;

  public Point2D()
  {
    x = 0.0D;
    y = 0.0D;
  }

  public Point2D(double pPX, double pPY)
  {
    x = pPX;
    y = pPY;
  }

  public double getX()
  {
    return x;
  }

  public double getY()
  {
    return y;
  }
}
