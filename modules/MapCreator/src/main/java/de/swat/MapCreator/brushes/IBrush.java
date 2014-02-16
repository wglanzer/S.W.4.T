package de.swat.MapCreator.brushes;

import java.awt.*;
import java.util.ArrayList;

/**
 * Brush-Interface das dazu verwendet wird,
 * einen Brush zu definieren, der sich mit drawBrush
 * in eine ArrayList aus Points einschreibt.
 *
 * @author W. Glanzer, 21.12.13
 */
public interface IBrush
{

  public void drawBrush(ArrayList<Point> pActualPoints, Point pActualMousePosition);

}
