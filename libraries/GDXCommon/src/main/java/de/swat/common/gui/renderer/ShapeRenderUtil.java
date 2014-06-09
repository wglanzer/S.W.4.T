package de.swat.common.gui.renderer;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author W.Glanzer, 05.06.2014.
 */
public class ShapeRenderUtil
{

  private static ShapeRenderer shapeRenderer = new ShapeRenderer();


  /**
   * Zeichnet ein Rectangle. Dreht es um einen bestimmten Winkel
   *
   * @param pX       X-Koordinate
   * @param pY       Y-Koordinate
   * @param pWidth   Breite
   * @param pHeight  Höhe
   * @param pAngle   Winkel, um den gedreht werden soll
   * @param pColor   Farbe, die das Rectangle haben soll
   */
  public static void drawRect(float pX, float pY, float pWidth, float pHeight, float pAngle, Color pColor)
  {
    shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    shapeRenderer.identity();
    shapeRenderer.setColor(pColor);
    shapeRenderer.translate(pX, pY, 0.f);
    shapeRenderer.rotate(0.f, 0.f, 1.f, pAngle);
    shapeRenderer.rect(-pWidth/2, -pHeight/2, pWidth, pHeight);
    shapeRenderer.end();
  }

}
