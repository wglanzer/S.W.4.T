package de.swat.dataModels.Map;

import de.swat.math.*;
import de.swat.utils.VectorUtil;
import javafx.collections.ObservableList;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Werner Glanzer, 28.11.13
 */
public class EntityCollisionObjectDataModel extends AbstractCollisionObjectDataModel
{

  private int posX, posY, radius;

  public EntityCollisionObjectDataModel(int pPosX, int pPosY, int pRadius)//TODO Wenn ein entity-Thread geschrieben ist, wird dieser hier benötigt
  {
    posX = pPosX;
    posY = pPosY;
    radius = pRadius;
  }

  @Override
  public void setBoundingBox()
  {
    setBoundingBox(new Rectangle(posX - radius, posY - radius, 2 * radius, 2 * radius));
  }


  /*
  Den richtigen Vektor finden, der rechtwinklig auf dem Kollisionsvektor steht:
  deltaX^2 + deltaY^2 = radius^2
  deltaX / deltaY = kDeltaY/kDeltaX

  deltaX = (kDeltaY/kDeltaX) * deltaY

  deltaY^2 = radius^2 - ((kDeltaY/kDeltaX) * deltaY)^2

  deltaY^2 = radius^2 - (deltaY * kDeltaY)^2 / kDeltaX^2

  deltaY^2 = radius^2 - (deltaY^2 * kDeltaY^2) / kDeltaX^2

   */

  @Override
  public Point checkFirstCollision(Vector2D pVector2D)
  {
    int vDeltaX = (int) Math.abs(pVector2D.getPoint1().getX() - pVector2D.getPoint2().getX());
    int vDeltaY = (int) Math.abs(pVector2D.getPoint1().getY() - pVector2D.getPoint2().getY());
    int deltaY = (int) Math.round(Math.sqrt((radius * radius * vDeltaX * vDeltaX) / ((vDeltaX * vDeltaX) + (vDeltaY * vDeltaY))));
    int deltaX = (int) Math.round(Math.sqrt((radius * radius) - (deltaY * deltaY)));
    return VectorUtil.getVectorCollision(pVector2D.getPoint1(), pVector2D.getPoint2(), new Point2D(posX + deltaX, posY + deltaY), new Point2D(posX - deltaX, posY - deltaY));
  }

  @Override
  public ArrayList<Point> checkAllCollisions(Vector2D pVector2D)
  {
    ArrayList<Point> returnList = new ArrayList<>(0);
    returnList.add(checkFirstCollision(pVector2D));
    return returnList;
  }

  @Override
  public ObservableList<Point> findPath(Vector2D pVector, int pRadius)
  {
    return null;
  }

  public int getPosX()
  {
    return posX;
  }

  public int getPosY()
  {
    return posY;
  }

  public int getRadius()
  {
    return radius;
  }
}
