package de.swat.mapCreator.brushes;

import de.swat.ObservableList2;

import java.awt.*;

/**
 * Brush-Interface das dazu verwendet wird,
 * einen Brush zu definieren, der sich mit drawBrush
 * in eine ArrayList aus Points einschreibt.
 *
 * @author W. Glanzer, 21.12.13
 */
public interface IBrush
{

  public void drawBrush(ObservableList2<Point> pActualPoints, Point pActualMousePosition);

}
