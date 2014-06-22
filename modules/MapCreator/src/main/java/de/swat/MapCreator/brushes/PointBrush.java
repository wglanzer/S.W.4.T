package de.swat.mapCreator.brushes;

import de.swat.ObservableList2;

import java.awt.*;

/**
 * Standard-Point-Brush
 * Er fügt nur den gerade geklickten MausPoint
 * in die ArrayList ein.
 * --> Standard
 *
 * @author W. Glanzer, 22.12.13
 */
public class PointBrush extends AbstractBrush
{

  @Override
  public void drawBrush(ObservableList2<Point> pActualPoints, Point pActualMousePosition)
  {
    pActualPoints.add(pActualMousePosition);
  }

}
