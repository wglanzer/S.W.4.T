package de.swat.entity.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.swat.entity.AbstractEntity;
import de.swat.entity.bodyParts.BodyPartArms;
import de.swat.entity.bodyParts.BodyPartHead;
import de.swat.entity.bodyParts.BodyPartLegs;
import de.swat.entity.bodyParts.BodyPartTorso;

/**
 * Dieses Entity enthält Grundfunktionen, die jeder Gegner / anderes
 * Menschenartiges Wesen haben sollte.
 *
 * @author W.Glanzer, 05.06.2014.
 */
public class BaseEntity extends AbstractEntity
{
  protected BodyPartArms arms;
  protected BodyPartHead head;
  protected BodyPartLegs legs;
  protected BodyPartTorso torso;


  public BaseEntity()
  {
    arms = new BodyPartArms();
    head = new BodyPartHead();
    legs = new BodyPartLegs();
    torso = new BodyPartTorso();
  }

  @Override
  public void act(float delta)
  {
    super.act(delta);
    arms.act(delta);
    head.act(delta);
    legs.act(delta);
    torso.act(delta);
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    arms.draw(batch, parentAlpha);
    head.draw(batch, parentAlpha);
    legs.draw(batch, parentAlpha);
    torso.draw(batch, parentAlpha);
  }

  @Override
  public void setSize(float width, float height)
  {
    super.setSize(width, height);
    arms.setSize(width, height);
    head.setSize(width, height);
    legs.setSize(width, height);
    torso.setSize(width, height);
  }

  @Override
  public void setPosition(float x, float y)
  {
    super.setPosition(x, y);
    arms.setPosition(x, y);
    head.setPosition(x, y);
    legs.setPosition(x, y);
    torso.setPosition(x, y);
  }
}
