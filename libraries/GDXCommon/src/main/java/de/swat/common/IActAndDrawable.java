package de.swat.common;

import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * Gibt an, dass sich ein Objekt Zeichnen und Acten kann
 *
 * @author W.Glanzer, 20.06.2014.
 */
public interface IActAndDrawable extends IDrawable, IActable
{

  @Override
  void act(float pDelta);

  @Override
  void draw(Batch pBatch, float pParentAlpha, float pX, float pY, float pOriginX, float pOriginY, float pWidth, float pHeight);
}
