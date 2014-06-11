package de.swat.datamodels.map;

import de.swat.math.Vector2D;
import de.swat.observableList2.ObservableList2;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Werner Glanzer, 28.11.13
 */
public abstract class AbstractCollisionObjectDataModel implements Serializable
{
  /**
   * Boundingbox des Objects
   */
  private Rectangle boundingBox;

  public abstract void setBoundingBox();

  @Nullable
  public Rectangle getBoundingBox()
  {
    return boundingBox;
  }

  /**
   * Setter für die Boundingbox des Objects
   */
  public void setBoundingBox(Rectangle pBoundingBox)
  {
    boundingBox = pBoundingBox;
  }

  public abstract Point checkFirstCollision(Vector2D pVector2D);

  public abstract ArrayList<Point> checkAllCollisions(Vector2D pVector2D);

  public abstract ObservableList2<Point> findPath(Vector2D pVector, int pRadius);
}
