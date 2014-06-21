package de.swat.common.map;

import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.map.xml.components.IXMLComponent;
import org.jdom2.Element;
import org.jetbrains.annotations.Nullable;

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
    };
  }

}
