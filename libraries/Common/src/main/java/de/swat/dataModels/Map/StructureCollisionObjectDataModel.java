package de.swat.dataModels.Map;

import de.swat.math.*;
import de.swat.utils.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Werner Glanzer, 28.11.13
 */
public class StructureCollisionObjectDataModel extends AbstractCollisionObjectDataModel implements Serializable
{

  private Structure structure;

  public StructureCollisionObjectDataModel()
  {
    structure = new Structure();
  }

  /**
   * Setzt die Boundingbox für das Objekt abhängig von der minimalen
   * und maximalen Spannweite der Punkte in der Struktur
   */
  @Override
  public void setBoundingBox()
  {
    ArrayList<Point> pointList = structure.getPointList();
    double maxX = pointList.get(0).getX();
    double maxY = pointList.get(0).getY();
    double minX = pointList.get(0).getX();
    double minY = pointList.get(0).getY();


    for (int i = 1; i < pointList.size(); i++)
    {
      double currentX = pointList.get(i).getX();
      double currentY = pointList.get(i).getY();
      if (currentX > maxX)
        maxX = currentX;
      else if (currentX < minX)
        minX = currentX;
      if (currentY > maxY)
        maxY = currentY;
      else if (currentY < minY)
        minY = currentY;
    }
    setBoundingBox(new Rectangle((int) minX, (int) minY, (int) (maxX - minX), (int) (maxY - minY)));
  }


  /**
   * Überprüft die Kollsion der Struktur mit einem Vektor und gibt den ersten Kollisionspunkt zurück.
   *
   * @param pVector Gerichteter Kollisionsvektor
   * @return Erster Kollsionspunkt
   */
  @Override
  public Point checkFirstCollision(Vector2D pVector)
  {
    Rectangle boundingBox = getBoundingBox();
    Point returnPoint = null;
    Point2D v1 = pVector.getPoint1();
    Point2D v2 = pVector.getPoint2();
    Point2D b1 = new Point2D(boundingBox.x, boundingBox.y);
    Point2D b2 = new Point2D(boundingBox.x + boundingBox.width, boundingBox.y);
    Point2D b3 = new Point2D(boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height);
    Point2D b4 = new Point2D(boundingBox.x, boundingBox.y + boundingBox.height);


    if (boundingBox.contains(v1.x, v1.y) ||
        boundingBox.contains(v2.x, v2.y) ||
        VectorUtil.isVectorCollision(v1, v2, b1, b2) ||
        VectorUtil.isVectorCollision(v1, v2, b2, b3) ||
        VectorUtil.isVectorCollision(v1, v2, b3, b4) ||
        VectorUtil.isVectorCollision(v1, v2, b4, b1)
        )
    {
      //System.out.println("test");
      for (int i = 0; i < getStructure().getPointList().size(); i++)
      {
        Point a1 = structure.getPointList().get(i);
        Point a2;
        if (i == structure.getPointList().size() - 1)
        {
          a2 = structure.getPointList().get(0);
        }
        else
        {
          a2 = structure.getPointList().get(i + 1);
        }
        if (VectorUtil.isVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2()))
        {

          Point currPoint = VectorUtil.getVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2());


          if (returnPoint == null)
            returnPoint = currPoint;

          //Das folgende ist aus Raster übernommen, ist nicht sicher, ob es stimmt.
          if (pVector.getPoint1().x == pVector.getPoint2().x)
          {
            if (pVector.getPoint1().y < pVector.getPoint2().y && currPoint.y < returnPoint.y)
            {
              returnPoint = currPoint;
            }
            else if (pVector.getPoint1().y > pVector.getPoint2().y && currPoint.y > returnPoint.y)
            {
              returnPoint = currPoint;
            }
          }
          else
          {
            if (pVector.getPoint1().x < pVector.getPoint2().x && currPoint.x < returnPoint.x)
            {
              returnPoint = currPoint;
            }
            else if (pVector.getPoint1().x > pVector.getPoint2().x && currPoint.x > returnPoint.x)
            {
              returnPoint = currPoint;
            }
          }
        }
      }
      //if (returnPoint != null)
      //  System.out.println(returnPoint.x + "   " + returnPoint.y);
      //else
      //  System.out.println("test");
    }
    return returnPoint;
  }

  /**
   * Überprüft alle Kollisionspunkte eines Vektors mit einer Struktur und gibt diese als Arraylist zurück
   *
   * @param pVector Kollisionsvektor
   * @return Kollisionspunkte
   */
  @Override
  public ArrayList<Point> checkAllCollisions(Vector2D pVector)
  {
    Rectangle boundingBox = getBoundingBox();
    Point returnPoint = null;
    Point2D v1 = pVector.getPoint1();
    Point2D v2 = pVector.getPoint2();
    Point2D b1 = new Point2D(boundingBox.x, boundingBox.y);
    Point2D b2 = new Point2D(boundingBox.x + boundingBox.width, boundingBox.y);
    Point2D b3 = new Point2D(boundingBox.x + boundingBox.width, boundingBox.y + boundingBox.height);
    Point2D b4 = new Point2D(boundingBox.x, boundingBox.y + boundingBox.height);
    ArrayList<Point> returnPoints = new ArrayList<>(0);


    if (boundingBox.contains(v1.x, v1.y) ||
        boundingBox.contains(v2.x, v2.y) ||
        VectorUtil.isVectorCollision(v1, v2, b1, b2) ||
        VectorUtil.isVectorCollision(v1, v2, b2, b3) ||
        VectorUtil.isVectorCollision(v1, v2, b3, b4) ||
        VectorUtil.isVectorCollision(v1, v2, b4, b1)
        )
    {
      //System.out.println("test");
      for (int i = 0; i < getStructure().getPointList().size(); i++)
      {
        Point a1 = structure.getPointList().get(i);
        Point a2;
        if (i == structure.getPointList().size() - 1)
        {
          a2 = structure.getPointList().get(0);
        }
        else
        {
          a2 = structure.getPointList().get(i + 1);
        }
        if (VectorUtil.isVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2()))
        {

          Point currPoint = VectorUtil.getVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2());


          if (currPoint != null)
            returnPoints.add(currPoint);
        }
      }
    }
    return returnPoints;
  }

  public ArrayList<Point> findPath(Vector2D pVector, int pRadius)
  {
    //System.out.println("Wir sind beim test angekommen");
    Rectangle boundingBox = getBoundingBox();
    Point returnPoint = null;
    Point2D v1 = pVector.getPoint1();
    Point2D v2 = pVector.getPoint2();
    int vectorId = 0;

    for (int i = 0; i < getStructure().getPointList().size(); i++)
    {
      Point a1 = structure.getPointList().get(i);
      Point a2;
      if (i == structure.getPointList().size() - 1)
      {
        a2 = structure.getPointList().get(0);
      }
      else
      {
        a2 = structure.getPointList().get(i + 1);
      }
      if (VectorUtil.isVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2()))
      {

        Point currPoint = VectorUtil.getVectorCollision(new Point2D(a1.getX(), a1.getY()), new Point2D(a2.getX(), a2.getY()), pVector.getPoint1(), pVector.getPoint2());


        if (returnPoint == null)
        {
          returnPoint = currPoint;
          vectorId = i;
        }

        //Das folgende ist aus Raster übernommen, ist nicht sicher, ob es stimmt.
        if (pVector.getPoint1().x == pVector.getPoint2().x)
        {
          if (pVector.getPoint1().y < pVector.getPoint2().y && currPoint.y < returnPoint.y)
          {
            returnPoint = currPoint;
            vectorId = i;
          }
          else if (pVector.getPoint1().y > pVector.getPoint2().y && currPoint.y > returnPoint.y)
          {
            returnPoint = currPoint;
            vectorId = i;
          }
        }
        else
        {
          if (pVector.getPoint1().x < pVector.getPoint2().x && currPoint.x < returnPoint.x)
          {
            returnPoint = currPoint;
            vectorId = i;
          }
          else if (pVector.getPoint1().x > pVector.getPoint2().x && currPoint.x > returnPoint.x)
          {
            returnPoint = currPoint;
            vectorId = i;
          }
        }
      }
    }

    int currID = vectorId;
    System.out.println(vectorId);
    int preID;
    int postID;
    int dist = 0;
    int pointListSize = structure.getPointList().size();
    boolean prePointFound = false;
    boolean postPointFound = false;
    ArrayList<Point> preList = new ArrayList<>(0);
    ArrayList<Point> postList = new ArrayList<>(0);

    while (!(prePointFound && postPointFound) && dist < pointListSize)
    {
      //Die Punkte vor und nach dem berechneten Punkt
      preID = currID - dist;
      if (preID < 0)
      {
        preID = pointListSize + preID;
      }
      postID = currID + dist;
      if (postID >= pointListSize)
      {
        postID = postID - pointListSize;
      }
      dist++;

      //Die Punkte vor und nach dem Punkten davor und danach dem Berechneten Punkt

      if (!prePointFound)
      {
        int prePreID = preID - 1;
        if (prePreID < 0)
        {
          prePreID = pointListSize + prePreID;
        }
        int postPreID = preID + 1;
        if (postPreID >= pointListSize)
        {
          postPreID = postPreID - pointListSize;
        }

        System.out.println("Pre  " + prePreID + "  " + preID + "  " + postPreID);
        Point prePoint = PointUtil.getEdgePoint(structure.getPointList().get(prePreID), structure.getPointList().get(preID), structure.getPointList().get(postPreID), pRadius);
        preList.add(prePoint);
        if (checkFirstCollision(new Vector2D(new Point2D(prePoint.x, prePoint.y), v2)) == null)
        {
          prePointFound = true;
        }
      }

      if (!postPointFound)
      {
        int prePostID = postID - 1;
        if (prePostID < 0)
        {
          prePostID = pointListSize + prePostID;
        }
        int postPostID = postID + 1;
        if (postPostID >= pointListSize)
        {
          postPostID = postPostID - pointListSize;
        }

        System.out.println("Post  " + prePostID + "  " + postID + "  " + postPostID);
        Point postPoint = PointUtil.getEdgePoint(structure.getPointList().get(prePostID), structure.getPointList().get(postID), structure.getPointList().get(postPostID), pRadius);
        postList.add(postPoint);
        if (checkFirstCollision(new Vector2D(new Point2D(postPoint.x, postPoint.y), v2)) == null)
        {
          postPointFound = true;
        }
      }
    }
    if (PointUtil.getLengthFromStructure(postList) > PointUtil.getLengthFromStructure(preList))
    {
      return preList;
    }
    else
    {
      return postList;
    }
  }

  public Structure getStructure()
  {
    return structure;
  }

  public void setStructure(Structure pStructure)
  {
    structure = pStructure;
  }
}
