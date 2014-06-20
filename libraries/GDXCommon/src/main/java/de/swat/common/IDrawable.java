package de.swat.common;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
  void draw(SpriteBatch pBatch, float pParentAlpha, float pX, float pY, float pWidth, float pHeight);

}
