package de.swat.utils;

import de.swat.math.*;
import org.junit.Test;

/**
 * @author W. Glanzer, 14.12.13
 */
public class MathUtilTest
{
  @Test
  public void edgePointTest()
  {
    System.out.println(MathUtil.getAngleBetweenVectors(new Vector2D(new Point2D(0, 0), new Point2D(1, 0)), new Vector2D(new Point2D(0, 0), new Point2D(0, 1))));
  }
}
