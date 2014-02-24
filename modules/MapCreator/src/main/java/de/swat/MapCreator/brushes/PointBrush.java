package de.swat.MapCreator.brushes;

import javafx.collections.ObservableList;

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
  public void drawBrush(ObservableList<Point> pActualPoints, Point pActualMousePosition)
  {
    pActualPoints.add(pActualMousePosition);
  }

}
