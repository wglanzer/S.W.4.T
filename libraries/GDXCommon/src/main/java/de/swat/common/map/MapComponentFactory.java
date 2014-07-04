package de.swat.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.map.xml.components.IXMLComponent;
import org.jdom2.Element;
import org.jetbrains.annotations.Nullable;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;
import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.Set;

/**
 * @author W.Glanzer, 21.06.2014.
 */
public class MapComponentFactory
{

  public static IMapComponent convertToMapComponent(IXMLComponent pComponent)
  {
    return new IMapComponent()
    {
      @Override
      public int getZIndex()
      {
        return 0;
      }

      @Override
      public Set<Point2D> getPoints()
      {
        return new HashSet<>();
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
            return new Element("hallo");
          }

          @Override
          public void fromXML(Element pXML)
          {
          }
        };
      }

      @Override
      public void fromXML(IXMLComponent pComponent)
      {
      }

      @Override
      public void act(float pDelta)
      {
      }

      @Override
      public void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight)
      {
      }

      @Override
      public void setRotation(float pDegrees)
      {
      }

      @Override
      public float getRotation()
      {
        return 0;
      }

      @Override
      public MutableTreeNode getNode()
      {
        return new DefaultMutableTreeNode("COMPONENTE: " + getZIndex());
      }
    };
  }

}
