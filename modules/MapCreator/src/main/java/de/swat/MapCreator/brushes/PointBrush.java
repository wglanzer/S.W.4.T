package de.swat.MapCreator.brushes;

import java.awt.*;
import java.util.ArrayList;

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
  public void drawBrush(ArrayList<Point> pActualPoints, Point pActualMousePosition)
  {
    pActualPoints.add(pActualMousePosition);
  }

}
