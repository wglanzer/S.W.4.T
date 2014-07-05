package de.swat.entity.entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import de.swat.entity.AbstractEntity;
import de.swat.entity.bodyParts.BodyPartArms;
import de.swat.entity.bodyParts.BodyPartHead;
import de.swat.entity.bodyParts.BodyPartLegs;
import de.swat.entity.bodyParts.BodyPartTorso;
import org.jetbrains.annotations.NotNull;

/**
 * Dieses Entity enthält Grundfunktionen, die jeder Gegner / anderes
 * Menschenartiges Wesen haben sollte.
 *
 * @author W.Glanzer, 05.06.2014.
 */
public class BaseEntity extends AbstractEntity
{
  protected final BodyPartArms arms;
  protected final BodyPartHead head;
  protected final BodyPartLegs legs;
  protected final BodyPartTorso torso;

  public BaseEntity(@NotNull FileHandle pHead, @NotNull FileHandle pArms, @NotNull FileHandle pTorso, @NotNull FileHandle pLegs, @NotNull FileHandle pWeapon)
  {
    super(null);
    arms = new BodyPartArms(pArms);
    head = new BodyPartHead(pHead);
    legs = new BodyPartLegs(pLegs);
    torso = new BodyPartTorso(pTorso);
  }

  @Override
  public void draw(Batch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    legs.draw(batch, parentAlpha);
    arms.draw(batch, parentAlpha);
    torso.draw(batch, parentAlpha);
    head.draw(batch, parentAlpha);
  }

  @Override
  public void act(float pDelta)
  {
    super.act(pDelta);
    legs.act(pDelta);
    arms.act(pDelta);
    torso.act(pDelta);
    head.act(pDelta);
  }

  @Override
  public void setRotation(float degrees)
  {
    super.setRotation(degrees);
    arms.setRotation(degrees);
    legs.setRotation(degrees);
    head.setRotation(degrees);
    torso.setRotation(degrees);
  }

  @Override
  public void setPosition(float x, float y)
  {
    super.setPosition(x, y);
    legs.setPosition(x, y);
    arms.setPosition(x, y);
    torso.setPosition(x, y);
    head.setPosition(x, y);
  }

  @Override
  public void setSize(float width, float height)
  {
    super.setSize(width, height);
    legs.setSize(width, height);
    arms.setSize(width, height);
    torso.setSize(width, height);
    head.setSize(width, height);
  }

  @Override
  public void setOrigin(float originX, float originY)
  {
    super.setOrigin(originX, originY);
    legs.setOrigin(originX, originY);
    arms.setOrigin(originX, originY);
    head.setOrigin(originX, originY);
    torso.setOrigin(originX, originY);
  }

  @Override
  public void setScale(float pScale)
  {
    super.setScale(pScale);
    legs.setScale(pScale);
    arms.setScale(pScale);
    torso.setScale(pScale);
    head.setScale(pScale);
  }

  public BodyPartArms getArms()
  {
    return arms;
  }

  public BodyPartHead getHead()
  {
    return head;
  }

  public BodyPartLegs getLegs()
  {
    return legs;
  }

  public BodyPartTorso getTorso()
  {
    return torso;
  }
}
