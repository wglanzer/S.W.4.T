package de.swat.common.map.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.google.common.base.Splitter;
import de.swat.common.map.IMapComponent;
import de.swat.map.xml.components.IXMLComponent;
import org.jdom2.Attribute;
import org.jdom2.Content;
import org.jdom2.Element;
import org.jetbrains.annotations.Nullable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

/**
 * Beschreibt ein Polygon auf der Map
 *
 * @author W.Glanzer, 08.07.2014.
 */
public class PolygonComponent implements IMapComponent
{
  private Set<Point2D> points = new HashSet<>();
  private int zIndex = 0;
  private float rotation = 0;

  public PolygonComponent()
  {
  }

  @Override
  public void act(float pDelta)
  {

  }

  @Override
  public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pOriginX, float pOriginY, float pWidth, float pHeight)
  {

  }

  @Override
  public MutableTreeNode getNode()
  {
    DefaultMutableTreeNode parentNode = new DefaultMutableTreeNode("Polygon");

    for(Point2D currPoint : points)
      parentNode.add(new DefaultMutableTreeNode("Point (x=" + currPoint.getX() + ", y=" + currPoint.getY()));

    return parentNode;
  }

  @Override
  public int getZIndex()
  {
    return zIndex;
  }

  @Override
  public Set<Point2D> getPoints()
  {
    return points;
  }

  @Override
  public IXMLComponent toXML()
  {
    return new IXMLComponent()
    {
      @Nullable
      @Override
      public Element toXML()
      {
        Element ele = new Element("Polygon");
        ele.setAttribute("z", String.valueOf(zIndex));
        ele.setAttribute("rot", String.valueOf(rotation));
        for(Point2D currPoint : points)
          ele.addContent("POINT;" + currPoint.getX() + ";" + currPoint.getY());

        return ele;
      }

      @Override
      public void fromXML(Element pXML)
      {
        Attribute aZIndex = pXML.getAttribute("z");
        if(aZIndex != null && aZIndex.getValue() != null)
          zIndex = Integer.parseInt(aZIndex.getValue());

        Attribute aRotation = pXML.getAttribute("rot");
        if(aRotation != null && aRotation.getValue() != null)
          rotation = Integer.parseInt(aRotation.getValue());

        points.clear();
        for(Content currContent : pXML.getContent())
        {
          String val = currContent.getValue();
          if(val != null)
          {
            Iterable<String> split = Splitter.on(";").omitEmptyStrings().split(val);
            Point2D point = new Point2D.Double();
            int i = 0;
            for(String currString : split)
            {
              if(i == 0)
              {
                if(!currString.equals("POINT"))
                  break;
              }
              else if(i == 1)
                point.setLocation(Integer.valueOf(currString), point.getY());
              else if(i == 2)
                point.setLocation(point.getX(), Integer.valueOf(currString));

              i++;
            }
            points.add(point);
          }
        }
      }
    };
  }

  @Override
  public float getRotation()
  {
    return rotation;
  }

  @Override
  public void setRotation(float pDegrees)
  {
    rotation = pDegrees;
  }
}
