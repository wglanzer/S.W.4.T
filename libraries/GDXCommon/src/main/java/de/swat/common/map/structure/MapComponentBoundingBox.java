package de.swat.common.map.structure;

import de.swat.common.map.IMapComponent;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Set;

/**
 * Eine MapComponentBoundingBox beschreibt eine Art Boundingbox
 * einer MapComponent. Sie stellt eine Art "Wrapper" im geometrischen
 * Sinn dar.
 *
 * @author W.Glanzer, 22.06.2014.
 */
public class MapComponentBoundingBox extends Rectangle2D.Double
{
  private IMapComponent component;

  public MapComponentBoundingBox(IMapComponent pComponent)
  {
    component = pComponent;
    recalculateBox();
  }

  /**
   * Berechnet die Boundingbox neu.
   * Hierzu werden die Punkte aus der MapComponent verwendet.
   */
  public void recalculateBox()
  {
    Set<Point2D> points = component.getPoints();

    double minX = 0D;
    double minY = 0D;
    double maxX = 0D;
    double maxY = 0D;
    boolean isFirstSet = false;

    for(Point2D currPoint : points)
    {
      if(!isFirstSet)
      {
        minX = currPoint.getX();
        minY = currPoint.getY();
        maxX = minX;
        maxY = minY;
        isFirstSet = true;

        continue;
      }

      double currentX = currPoint.getX();
      double currentY = currPoint.getY();
      if(currentX > maxX)
        maxX = currentX;
      else if(currentX < minX)
        minX = currentX;
      if(currentY > maxY)
        maxY = currentY;
      else if(currentY < minY)
        minY = currentY;
    }

    setRect(minX, minY, maxX - minX, maxY - minY);
  }
}
