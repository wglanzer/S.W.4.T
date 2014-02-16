package de.swat.dataModels.Map;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alex Biederer, 28.11.13
 */
public class Structure implements Serializable
{
  private ArrayList<Point> pointList = new ArrayList<>();

  public ArrayList<Point> getPointList()
  {
    return pointList;
  }

  public Polygon getPolygon()
  {
    int[] polyX = new int[pointList.size()];
    int[] polyY = new int[pointList.size()];
    for (int i = 0; i < polyX.length; i++)
    {
      polyX[i] = (int) pointList.get(i).getX();
      polyY[i] = (int) pointList.get(i).getY();
    }
    return new Polygon(polyX, polyY, polyX.length);
  }

  /**
   * Überprüft die Richtung, in der eine Struktur gespeichert ist und dreht diese ggf um
   */
  public void checkDirection()
  {
    Point maxPoint = pointList.get(0);
    for (int i = 1; i < pointList.size(); i++)
    {
      Point currPoint = pointList.get(i);
      if (currPoint.y < maxPoint.y)
      {
        maxPoint = currPoint;
      }
    }
    int maxPointIndex = pointList.indexOf(maxPoint);
    int prePointIndex = maxPointIndex - 1;
    int postPointIndex = maxPointIndex + 1;

    if (maxPointIndex == 0)
    {
      prePointIndex = pointList.size() - 1;
    }
    if (maxPointIndex == pointList.size() - 1)
    {
      postPointIndex = 0;
    }

    if (pointList.get(prePointIndex).x < pointList.get(postPointIndex).x)
    {
      ArrayList<Point> tempList = new ArrayList<>(0);
      for (int i = 0; i < pointList.size(); i++)
      {
        tempList.add(pointList.get(i));
      }
      pointList = new ArrayList<>(0);
      for (int i = tempList.size() - 1; i >= 0; i--)
      {
        pointList.add(tempList.get(i));
      }
    }
  }
}
