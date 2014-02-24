package de.swat.dataModels.Map;

import de.swat.math.Vector2D;
import javafx.collections.ObservableList;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Werner Glanzer, 28.11.13
 */
public abstract class AbstractCollisionObjectDataModel
{
  /**
   * Boundingbox des Objects
   */
  private Rectangle boundingBox;

  public abstract void setBoundingBox();

  /**
   * Setter für die Boundingbox des Objects
   */
  public void setBoundingBox(Rectangle pBoundingBox)
  {
    boundingBox = pBoundingBox;
  }

  public Rectangle getBoundingBox()
  {
    return boundingBox;
  }

  public abstract Point checkFirstCollision(Vector2D pVector2D);

  public abstract ArrayList<Point> checkAllCollisions(Vector2D pVector2D);

  public abstract ObservableList<Point> findPath(Vector2D pVector, int pRadius);
}
