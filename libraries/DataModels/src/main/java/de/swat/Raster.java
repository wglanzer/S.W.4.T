package de.swat;

import de.swat.accesses.*;
import de.swat.dataModels.Map.*;
import de.swat.math.*;
import de.swat.observableList2.ObservableList2;
import de.swat.util.DataModelHandler;
import javafx.collections.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author W. Glanzer, 16.02.14
 */
public class Raster
{

  RasterModelAccess modelAccess = (RasterModelAccess) DataModelHandler.newModelAccess(RasterModelAccess.class);

  /**
   * @param pRasterSize       Gewünschte Rastergröße
   * @param pMapSizeDimension Dimension der Map
   */
  public Raster(int pRasterSize, Dimension pMapSizeDimension, Map pMap)
  {
    modelAccess.setMap(pMap);
    modelAccess.setRasterSize(pRasterSize);
    int boundX = (int) pMapSizeDimension.getWidth();
    int boundY = (int) pMapSizeDimension.getHeight();

    ObservableList2[][] currRasterList = new ObservableList2[boundX][boundY];
    for (int i = 0; i < boundX; i++)
    {
      for (int k = 0; k < boundY; k++)
      {
        currRasterList[i][k] = new ObservableList2<>();
      }
    }
    //noinspection unchecked
    modelAccess.setRaster(currRasterList);
  }

  /**
   * Fügt Objekteinträge anhand der Bounding Box in das Raster ein
   *
   * @param pBoundingBox Boundingbox des Objektes
   * @param pIndex       Index des Objektes, das eingetragen werden soll
   */
  public void addToRaster(Rectangle pBoundingBox, int pIndex)
  {
    int rasterSize = modelAccess.getRasterSize();
    int startX = pBoundingBox.x / rasterSize;
    int startY = pBoundingBox.y / rasterSize;
    int endX = ((pBoundingBox.width + pBoundingBox.x) / rasterSize);
    int endY = ((pBoundingBox.height + pBoundingBox.y) / rasterSize);

    for (int i = startX; i <= endX; i++)
    {
      for (int k = startY; k <= endY; k++)
      {
        modelAccess.getRaster()[i][k].add(pIndex);
        ////System.out.println(i + "" + k);
      }
    }
  }

  /**
   * Enternt Objekteinträge anhand der Bounding Box aus dem Raster
   *
   * @param pBoundingBox Boundingbox des Objektes
   * @param pIndex       Index des Objektes, das entfernt werden soll
   */
  public void removeFromRaster(Rectangle pBoundingBox, int pIndex)
  {
    int rasterSize = modelAccess.getRasterSize();
    int startX = pBoundingBox.x / rasterSize;
    int startY = pBoundingBox.y / rasterSize;
    int endX = ((pBoundingBox.width + pBoundingBox.x) / rasterSize);
    int endY = ((pBoundingBox.height + pBoundingBox.y) / rasterSize);

    for (int i = startX; i <= endX; i++)
    {
      for (int k = startY; k <= endY; k++)
      {
        modelAccess.getRaster()[i][k].remove(pIndex);
      }
    }
  }

  /*
  * was die Methode zur bestimmung der Raster tun muss:
  * -steigung des Vektors bestimmen
  * -wenn steigung <-1 oder >1, dann x und y vertauschen
  *
  * -abfangen, wenn vektor in einem raster eingeschlossen ist
  *  dann nur in dem raster suchen.
  * -wenn der vektor von rechts nach links geht, dann for- schleife von hinten durchgehen
  * -ansonsten von vorne durchgehen.
  * -Immer wenn invert war, dann am ende noch das raster umdrehen.
  * -wenn ein punkt rauskommt, zurückgeben.
  * */

  /**
   * Überprüft die erste Kollsion, die ein Vektor hat, und gibt den Punkt zurück.
   *
   * @param pVector Kollisionsvektor
   * @return
   */
  public Point checkFirstCollision(Vector2D pVector)
  {
    int rasterSize = modelAccess.getRasterSize();
    //System.out.println("Methode aufgerufen");
    ArrayList<Integer> checkedObjects = new ArrayList<>(0);
    float aX = (float) pVector.getPoint1().getX();
    float aY = (float) pVector.getPoint1().getY();
    float bX = (float) pVector.getPoint2().getX();
    float bY = (float) pVector.getPoint2().getY();

    float m = 0;

    boolean invert = false;

    if (aX != bX)                                                                                                       //Abfangen von m = Unendlich
    {
      m = (aY - bY) / (aX - bX);
    }
    else                                                                                                                //Bei m = Unendlich Vertauschen von x und y dass m = 0
    {
      float merk = aX;
      aX = aY;
      aY = merk;

      merk = bX;
      bX = bY;
      bY = merk;
    }
    if (m > 1 || m < -1)
    //Vertauschen von x und y
    {
      float merk = aX;
      aX = aY;
      aY = merk;

      merk = bX;
      bX = bY;
      bY = merk;

      m = 1 / m;

      invert = true;
    }


    if (Math.abs(aX - bX) < rasterSize && aX % rasterSize < rasterSize - Math.abs(aX - bX))
    {
      //System.out.println("Kleiner als Raster");
      //Wenn ein Vektor kleiner als ein Raster ist und keine Rastergrenze schneidet
      if (!invert)
      //Wenn umgedreht war, dann wieder zurückdrehen.
      {
        Point returnPoint = checkFirstCollisionFromRaster((int) aX / rasterSize, (int) (aY / rasterSize), pVector, checkedObjects);
        if (returnPoint != null)
          return returnPoint;
        //System.out.println((int) aX / rasterSize + "   " + (int) (aY / rasterSize));
      }
      else
      {
        Point returnPoint = checkFirstCollisionFromRaster((int) aY / rasterSize, (int) (aX / rasterSize), pVector, checkedObjects);
        if (returnPoint != null)
          return returnPoint;
        //System.out.println((int) aY / rasterSize + "   " + (int) (aX / rasterSize));
      }

    }


    else if (aX > bX)
    {
      //System.out.println("Rückwärts rechnen");
      //Wenn Rückwärts gerechnnet wird
      for (float currX = aX; currX > bX; currX = currX - rasterSize)
      //Durchgehen aller Rasterübergänge
      {
        float currY = (currX * m) + (aY - aX * m);

        int ceilX = (int) Math.ceil((double) currX / rasterSize);
        int floorX = (int) Math.floor((double) currX / rasterSize);
        int checkY = (int) (currY / rasterSize);
        //Raster auf beiden Seiten des Wertes werden überprüft.
        if (!invert)
        {
          Point returnPoint = checkFirstCollisionFromRaster(ceilX, checkY, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(ceilX + "   " + checkY);
        }
        else
        {
          Point returnPoint = checkFirstCollisionFromRaster(checkY, ceilX, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(checkY + "   " + ceilX);
        }


        if (!invert)
        {
          Point returnPoint = checkFirstCollisionFromRaster(floorX, checkY, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(floorX + "   " + checkY);
        }
        else
        {
          Point returnPoint = checkFirstCollisionFromRaster(checkY, floorX, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(checkY + "   " + floorX);
        }
      }
    }


    else
    {
      //System.out.println("Vorwärts rechnen");
      //Wenn von vorne nach hinter gerechnet wird.
      for (float currX = aX; currX < bX; currX = currX + rasterSize)
      {
        float currY = (currX * m) + (aY - aX * m);

        int ceilX = (int) Math.ceil((double) currX / rasterSize);
        int floorX = (int) Math.floor((double) currX / rasterSize);
        int checkY = (int) (currY / rasterSize);
        //Raster auf beiden Seiten des Wertes werden überprüft.
        if (!invert)
        {
          Point returnPoint = checkFirstCollisionFromRaster(ceilX, checkY, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(ceilX + "   " + checkY);
        }
        else
        {
          Point returnPoint = checkFirstCollisionFromRaster(checkY, ceilX, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(checkY + "   " + ceilX);
        }


        if (!invert)
        {
          Point returnPoint = checkFirstCollisionFromRaster(floorX, checkY, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(floorX + "   " + checkY);
        }
        else
        {
          Point returnPoint = checkFirstCollisionFromRaster(checkY, floorX, pVector, checkedObjects);
          if (returnPoint != null)
            return returnPoint;
          //System.out.println(checkY + "   " + floorX);
        }
      }
    }
    return null;
  }

  /**
   * Überprüft alle Kollisionen, die ein Vektor auf der Map hat und gibt diese in einem PointArray aus
   *
   * @param pVector Kollisionsvektor
   * @return Array aus Kollisionspunkten
   */
  public ObservableList2<Point> checkAllCollisions(Vector2D pVector)
  {
    int rasterSize = modelAccess.getRasterSize();
    //System.out.println("Methode aufgerufen");
    ObservableList2<Point> returnPoints = new ObservableList2<>();
    ArrayList<Integer> checkedObjects = new ArrayList<>(0);
    float aX = (float) pVector.getPoint1().getX();
    float aY = (float) pVector.getPoint1().getY();
    float bX = (float) pVector.getPoint2().getX();
    float bY = (float) pVector.getPoint2().getY();

    float m = 0;

    boolean invert = false;

    if (aX != bX)                                                                                                       //Abfangen von m = Unendlich
    {
      m = (aY - bY) / (aX - bX);
    }
    else                                                                                                                //Bei m = Unendlich Vertauschen von x und y dass m = 0
    {
      float merk = aX;
      aX = aY;
      aY = merk;

      merk = bX;
      bX = bY;
      bY = merk;
    }
    if (m > 1 || m < -1)
    //Vertauschen von x und y
    {
      float merk = aX;
      aX = aY;
      aY = merk;

      merk = bX;
      bX = bY;
      bY = merk;

      m = 1 / m;

      invert = true;
    }


    if (Math.abs(aX - bX) < rasterSize && aX % rasterSize < rasterSize - Math.abs(aX - bX))
    {
      //System.out.println("Kleiner als Raster");
      //Wenn ein Vektor kleiner als ein Raster ist und keine Rastergrenze schneidet
      if (!invert)
      //Wenn umgedreht war, dann wieder zurückdrehen.
      {
        returnPoints = checkAllCollisionsFromRaster((int) aX / rasterSize, (int) (aY / rasterSize), pVector, checkedObjects, returnPoints);
      }
      else
      {
        returnPoints = checkAllCollisionsFromRaster((int) aY / rasterSize, (int) (aX / rasterSize), pVector, checkedObjects, returnPoints);
      }

    }


    else if (aX > bX)
    {
      //System.out.println("Rückwärts rechnen");
      //Wenn Rückwärts gerechnnet wird
      for (float currX = aX; currX > bX; currX = currX - rasterSize)
      //Durchgehen aller Rasterübergänge
      {
        float currY = (currX * m) + (aY - aX * m);

        int ceilX = (int) Math.ceil((double) currX / rasterSize);
        int floorX = (int) Math.floor((double) currX / rasterSize);
        int checkY = (int) (currY / rasterSize);
        //Raster auf beiden Seiten des Wertes werden überprüft.
        if (!invert)
        {
          returnPoints = checkAllCollisionsFromRaster(ceilX, checkY, pVector, checkedObjects, returnPoints);
        }
        else
        {
          returnPoints = checkAllCollisionsFromRaster(checkY, ceilX, pVector, checkedObjects, returnPoints);
        }


        if (!invert)
        {
          returnPoints = checkAllCollisionsFromRaster(floorX, checkY, pVector, checkedObjects, returnPoints);
        }
        else
        {
          returnPoints = checkAllCollisionsFromRaster(checkY, floorX, pVector, checkedObjects, returnPoints);
        }
      }
    }


    else
    {
      //System.out.println("Vorwärts rechnen");
      //Wenn von vorne nach hinter gerechnet wird.
      for (float currX = aX; currX < bX; currX = currX + rasterSize)
      {
        float currY = (currX * m) + (aY - aX * m);

        int ceilX = (int) Math.ceil((double) currX / rasterSize);
        int floorX = (int) Math.floor((double) currX / rasterSize);
        int checkY = (int) (currY / rasterSize);
        //Raster auf beiden Seiten des Wertes werden überprüft.
        if (!invert)
        {
          returnPoints = checkAllCollisionsFromRaster(ceilX, checkY, pVector, checkedObjects, returnPoints);
        }
        else
        {
          returnPoints = checkAllCollisionsFromRaster(checkY, ceilX, pVector, checkedObjects, returnPoints);
        }


        if (!invert)
        {
          returnPoints = checkAllCollisionsFromRaster(floorX, checkY, pVector, checkedObjects, returnPoints);
        }
        else
        {
          returnPoints = checkAllCollisionsFromRaster(checkY, floorX, pVector, checkedObjects, returnPoints);
        }
      }
    }
    return returnPoints;
  }


  public ObservableList2<Integer> getIndicesFromRaster(int x, int y)
  {
    return modelAccess.getRaster()[x][y];
  }


  /**
   * Überprüft die erste Kollision, die ein Vektor in einem Raster hat.
   *
   * @param x               x Index des Rasterfeldes
   * @param y               y Index des Rasterfeldes
   * @param pVector         Kollsionsvektor
   * @param pCheckedObjects Liste mit den Indices der Objekte, die bereits überprüft wurden.
   * @return
   */
  public Point checkFirstCollisionFromRaster(int x, int y, Vector2D pVector, ArrayList<Integer> pCheckedObjects)
  {
    ObservableList2[][] raster = modelAccess.getRaster();
    ////System.out.println(x + "   " + y);
    MapModelAccess mapModel = modelAccess.getMap().getModelAccess();
    if (mapModel.getCollisionObjects().size() > 0 && raster[x][y].size() > 0)
    {
      Point returnPoint = mapModel.getCollisionObjects().get((int) raster[x][y].get(0)).checkFirstCollision(pVector);
      if (raster[x][y].size() > 1)
      {

        for (int i = 1; i < raster[x][y].size(); i++)
        {
          int currIndex = (int) raster[x][y].get(i);

          if (!pCheckedObjects.contains(currIndex))
          {
            Point currPoint = mapModel.getCollisionObjects().get(currIndex).checkFirstCollision(pVector);
            pCheckedObjects.add(currIndex);

            if (currPoint != null)
            {

              if (returnPoint == null)
                returnPoint = currPoint;

              else if (pVector.getPoint1().x == pVector.getPoint2().x)
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
        }
      }
      return returnPoint;
    }
    else
      return null;
  }

  /**
   * Überprüft alle Kollisionen, die ein Vektor in einem Raster hat.
   *
   * @param x               x Index des Rasterfeldes
   * @param y               y Index des Rasterfeldes
   * @param pVector         Kollsionsvektor
   * @param pCheckedObjects Liste mit den Indices der Objekte, die bereits überprüft wurden.
   * @param pReturnPoints   Punktliste, an die die Kollisionpunkte angehängt werden
   * @return Punktliste, an die die Kollisionpunkte angehängt werden
   */
  private ObservableList2<Point> checkAllCollisionsFromRaster(int x, int y, Vector2D pVector, ArrayList<Integer> pCheckedObjects, ObservableList2<Point> pReturnPoints)
  {
    ObservableList2[][] raster = modelAccess.getRaster();
    MapModelAccess mapModel = modelAccess.getMap().getModelAccess();
    if (mapModel.getCollisionObjects().size() > 0 && raster[x][y].size() > 0)
    {
      for (int i = 0; i < raster[x][y].size(); i++)
      {
        int currIndex = (int) raster[x][y].get(i);

        if (!pCheckedObjects.contains(currIndex))
        {
          ArrayList<Point> currPoints = mapModel.getCollisionObjects().get(currIndex).checkAllCollisions(pVector);
          pCheckedObjects.add(currIndex);

          for (int j = 0; j < currPoints.size(); j++)
          {
            if (currPoints.get(j) != null)
              pReturnPoints.add(currPoints.get(j));
          }
        }
      }
    }
    return pReturnPoints;
  }

  /**
   * Gibt eine Referenz auf ein Objekt zurück, dessen Boundingbox den Zeiger einschließt
   *
   * @param pMouseX x-Position der Maus
   * @param pMouseY y-Position der Maus
   * @return Rückgabeobjekt
   */
  public AbstractCollisionObjectDataModel getInteractableFromMousePosition(int pMouseX, int pMouseY)
  {
    int rasterSize = modelAccess.getRasterSize();
    ObservableList2[][] raster = modelAccess.getRaster();
    MapModelAccess mapModel = modelAccess.getMap().getModelAccess();
    int checkX = pMouseX / rasterSize;
    int checkY = pMouseY / rasterSize;
    for (int i = 0; i < raster[checkX][checkY].size(); i++)
    {
      AbstractCollisionObjectDataModel currModel = mapModel.getCollisionObjects().get((int) raster[checkX][checkY].get(i));
      if (currModel instanceof EntityCollisionObjectDataModel && currModel.getBoundingBox().contains(pMouseX, pMouseY))
        //TODO Door als Collisionobjekt einbauen
        return currModel;
    }
    return null;
  }

  /**
   * Überprüft den kürzesten Weg zwischen 2 Punkten und gibt den Pfad als Arraylist mit Punkten zurück
   *
   * @param pStartPoint Startpunkt
   * @param pEndPoint   Endpunkt
   * @param pRadius     Kurvenradius
   * @return Pfad als Arraylist
   */
  public ObservableList2<Point> findPath(Point pStartPoint, Point pEndPoint, int pRadius, ArrayList<Integer> pCheckedObjects, ObservableList2<Point> pCurrentPoints)
  {
    Vector2D pathVector = new Vector2D(new Point2D(pStartPoint.x, pStartPoint.y), new Point2D(pEndPoint.x, pEndPoint.y));
    Point collPoint = checkFirstCollision(pathVector);
    if (collPoint == null)
      return pCurrentPoints;

    int rasterSize = modelAccess.getRasterSize();
    int checkX = collPoint.x / rasterSize;
    int checkY = collPoint.y / rasterSize;
    System.out.println(collPoint.x + "   " + collPoint.y);
    System.out.println(checkX + "   " + checkY);
    return findPathFromRaster(checkX, checkY, pathVector, pRadius, pCheckedObjects, pCurrentPoints);
  }
  //public ArrayList<Point> findPath(Point pStartPoint, Point pEndPoint, int pRadius, ArrayList<Point> pCurrentPoints)
  //{
  //  //if(checkFirstCollision(new Vector2D(new Point2D(pCurrentPoints.get(pCurrentPoints.size() - 1).x, pCurrentPoints.get(pCurrentPoints.size() - 1).y), new Point2D(pEndPoint.x, pEndPoint.y))) == null){
  //  //  return pCurrentPoints;
  //  //}
  //  System.out.println(pStartPoint.x +  "   " + pStartPoint.y);
  //
  //  Vector2D pVector = new Vector2D(new Point2D(pStartPoint.x, pStartPoint.y), new Point2D(pEndPoint.x, pEndPoint.y));
  //  ArrayList<Integer> checkedObjects = new ArrayList<>(0);
  //  float aX = (float) pVector.getPoint1().getX();
  //  float aY = (float) pVector.getPoint1().getY();
  //  float bX = (float) pVector.getPoint2().getX();
  //  float bY = (float) pVector.getPoint2().getY();
  //
  //  float m = 0;
  //
  //  boolean invert = false;
  //
  //  if (aX != bX)                                                                                                       //Abfangen von m = Unendlich
  //  {
  //    m = (aY - bY) / (aX - bX);
  //  }
  //  else                                                                                                                //Bei m = Unendlich Vertauschen von x und y dass m = 0
  //  {
  //    float merk = aX;
  //    aX = aY;
  //    aY = merk;
  //
  //    merk = bX;
  //    bX = bY;
  //    bY = merk;
  //  }
  //  if (m > 1 || m < -1)
  //  //Vertauschen von x und y
  //  {
  //    float merk = aX;
  //    aX = aY;
  //    aY = merk;
  //
  //    merk = bX;
  //    bX = bY;
  //    bY = merk;
  //
  //    m = 1 / m;
  //
  //    invert = true;
  //  }
  //
  //
  //  if (Math.abs(aX - bX) < rasterSize && aX % rasterSize < rasterSize - Math.abs(aX - bX))
  //  {
  //    //System.out.println("Kleiner als Raster");
  //    //Wenn ein Vektor kleiner als ein Raster ist und keine Rastergrenze schneidet
  //    if (!invert)
  //    //Wenn umgedreht war, dann wieder zurückdrehen.
  //    {
  //      ArrayList<Point> returnedPoints = findPathFromRaster((int) aX / rasterSize, (int) (aY / rasterSize), pVector, pRadius, checkedObjects, pCurrentPoints);
  //      if (returnedPoints.size() != pCurrentPoints.size())
  //      {
  //        for (int v = 0; v < returnedPoints.size(); v++)
  //        {
  //          pCurrentPoints.add(returnedPoints.get(v));
  //        }
  //        return pCurrentPoints;
  //      }
  //      //System.out.println((int) aX / rasterSize + "   " + (int) (aY / rasterSize));
  //    }
  //    else
  //    {
  //      ArrayList<Point> returnedPoints = findPathFromRaster((int) aY / rasterSize, (int) (aX / rasterSize), pVector, pRadius, checkedObjects, pCurrentPoints);
  //      if (returnedPoints.size() != pCurrentPoints.size())
  //      {
  //        for (int v = 0; v < returnedPoints.size(); v++)
  //        {
  //          pCurrentPoints.add(returnedPoints.get(v));
  //        }
  //        return pCurrentPoints;
  //      }
  //      //System.out.println((int) aY / rasterSize + "   " + (int) (aX / rasterSize));
  //    }
  //
  //  }
  //
  //  else if (aX > bX)
  //  {
  //    //System.out.println("Rückwärts rechnen");
  //    //Wenn Rückwärts gerechnnet wird
  //    for (float currX = aX; currX > bX; currX = currX - rasterSize)
  //    //Durchgehen aller Rasterübergänge
  //    {
  //      float currY = (currX * m) + (aY - aX * m);
  //
  //      int ceilX = (int) Math.ceil((double) currX / rasterSize);
  //      int floorX = (int) Math.floor((double) currX / rasterSize);
  //      int checkY = (int) (currY / rasterSize);
  //      //Raster auf beiden Seiten des Wertes werden überprüft.
  //      if (!invert)
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(ceilX, checkY, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          for (int v = 0; v < returnedPoints.size(); v++)
  //          {
  //            pCurrentPoints.add(returnedPoints.get(v));
  //          }
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(ceilX + "   " + checkY);
  //      }
  //      else
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(checkY, ceilX, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          for (int v = 0; v < returnedPoints.size(); v++)
  //          {
  //            pCurrentPoints.add(returnedPoints.get(v));
  //          }
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(checkY + "   " + ceilX);
  //      }
  //
  //
  //      if (!invert)
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(floorX, checkY, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          for (int v = 0; v < returnedPoints.size(); v++)
  //          {
  //            pCurrentPoints.add(returnedPoints.get(v));
  //          }
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(floorX + "   " + checkY);
  //      }
  //      else
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(checkY, floorX, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          for (int v = 0; v < returnedPoints.size(); v++)
  //          {
  //            pCurrentPoints.add(returnedPoints.get(v));
  //          }
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(checkY + "   " + floorX);
  //      }
  //    }
  //  }
  //
  //
  //  else
  //  {
  //    //System.out.println("Vorwärts rechnen");
  //    //Wenn von vorne nach hinter gerechnet wird.
  //    for (float currX = aX; currX < bX; currX = currX + rasterSize)
  //    {
  //      float currY = (currX * m) + (aY - aX * m);
  //
  //      int ceilX = (int) Math.ceil((double) currX / rasterSize);
  //      int floorX = (int) Math.floor((double) currX / rasterSize);
  //      int checkY = (int) (currY / rasterSize);
  //      //Raster auf beiden Seiten des Wertes werden überprüft.
  //      if (!invert)
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(ceilX, checkY, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          pCurrentPoints.addAll(returnedPoints);
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(ceilX + "   " + checkY);
  //      }
  //      else
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(checkY, ceilX, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          pCurrentPoints.addAll(returnedPoints);
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(checkY + "   " + ceilX);
  //      }
  //
  //
  //      if (!invert)
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(floorX, checkY, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          pCurrentPoints.addAll(returnedPoints);
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(floorX + "   " + checkY);
  //      }
  //      else
  //      {
  //        ArrayList<Point> returnedPoints = findPathFromRaster(checkY, floorX, pVector, pRadius, checkedObjects, pCurrentPoints);
  //        if (returnedPoints.size() != pCurrentPoints.size())
  //        {
  //          pCurrentPoints.addAll(returnedPoints);
  //          return pCurrentPoints;
  //        }
  //        //System.out.println(checkY + "   " + floorX);
  //      }
  //    }
  //  }
  //  return pCurrentPoints;
  //}

  public ObservableList2<Point> findPathFromRaster(int x, int y, Vector2D pVector, int pRadius, ArrayList<Integer> pCheckedObjects, ObservableList2<Point> pCurrentPoints)
  {
    ObservableList2[][] raster = modelAccess.getRaster();
    MapModelAccess mapModel = modelAccess.getMap().getModelAccess();
    //System.out.println(x + "   " + y);
    if (mapModel.getCollisionObjects().size() > 0 && raster[x][y].size() > 0)
    {
      Point returnPoint = mapModel.getCollisionObjects().get((int) raster[x][y].get(0)).checkFirstCollision(pVector);
      int checkIndex = (int) raster[x][y].get(0);
      if (raster[x][y].size() > 1)
      {

        for (int i = 1; i < raster[x][y].size(); i++)
        {
          int currIndex = (int) raster[x][y].get(i);

          if (!pCheckedObjects.contains(currIndex))
          {
            Point currPoint = mapModel.getCollisionObjects().get(currIndex).checkFirstCollision(pVector);
            pCheckedObjects.add(currIndex);

            if (currPoint != null)
            {

              if (returnPoint == null)
              {
                returnPoint = currPoint;
                checkIndex = currIndex;
              }

              else if (pVector.getPoint1().x == pVector.getPoint2().x)
              {
                if (pVector.getPoint1().y < pVector.getPoint2().y && currPoint.y < returnPoint.y)
                {
                  returnPoint = currPoint;
                  checkIndex = currIndex;
                }
                else if (pVector.getPoint1().y > pVector.getPoint2().y && currPoint.y > returnPoint.y)
                {
                  returnPoint = currPoint;
                  checkIndex = currIndex;
                }
              }
              else
              {
                if (pVector.getPoint1().x < pVector.getPoint2().x && currPoint.x < returnPoint.x)
                {
                  returnPoint = currPoint;
                  checkIndex = currIndex;
                }
                else if (pVector.getPoint1().x > pVector.getPoint2().x && currPoint.x > returnPoint.x)
                {
                  returnPoint = currPoint;
                  checkIndex = currIndex;
                }
              }
            }
          }
        }
      }

      ObservableList2<Point> currPoints = mapModel.getCollisionObjects().get(checkIndex).findPath(pVector, pRadius);
      pCurrentPoints.addAll(currPoints);
      if (checkFirstCollision(new Vector2D(new Point2D(pCurrentPoints.get(pCurrentPoints.size() - 1).x, pCurrentPoints.get(pCurrentPoints.size() - 1).y), pVector.point2)) == null)
      {
        //System.out.println("erwarteter Abschluss");
        pCurrentPoints.add(new Point((int) pVector.getPoint2().x, (int) pVector.getPoint2().y));
        return pCurrentPoints;
      }
      else
      {
        pCurrentPoints.addAll(findPath(pCurrentPoints.get(pCurrentPoints.size() - 1), new Point((int) pVector.point2.x, (int) pVector.point2.y), pRadius, pCheckedObjects, pCurrentPoints));
        return pCurrentPoints;
      }
    }
    else
      return pCurrentPoints;
  }

}
