package de.swat.common;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 *  Gibt an, dass etwas gezeichnet werden kann
 *
 * @author W.Glanzer, 20.06.2014.
 */
public interface IDrawable
{

  /**
   * Wird aufgerufen, wenn sich das Objekt
   * auf das SpriteBatch zeichnen soll.
   *
   * @param pBatch        Batch, auf das gezeichnet werden soll
   * @param pParentAlpha  Alpha-Wert des Parents des Objektes
   */
  void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pOriginX, float pOriginY, float pWidth, float pHeight);

  /**
   * @return Liefert die Rotation des Drawables
   */
  float getRotation();

  /**
   * Setzt die Rotation des Drawables.
   *
   * @param pDegrees  Degrees, um die gedreht werden soll
   */
  void setRotation(float pDegrees);
}
