package de.swat.entity.entities;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
  protected BodyPartArms arms;
  protected BodyPartHead head;
  protected BodyPartLegs legs;
  protected BodyPartTorso torso;

  public BaseEntity(@NotNull FileHandle pHead, @NotNull FileHandle pArms, @NotNull FileHandle pTorso, @NotNull FileHandle pLegs, @NotNull FileHandle pWeapon)
  {
    super(null);
    arms = new BodyPartArms(pArms);
    head = new BodyPartHead(pHead);
    legs = new BodyPartLegs(pLegs);
    torso = new BodyPartTorso(pTorso);
  }

  @Override
  public void act(float delta)
  {
    super.act(delta);
    legs.act(delta);
    arms.act(delta);
    torso.act(delta);
    head.act(delta);
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    legs.draw(batch, parentAlpha);
    arms.draw(batch, parentAlpha);
    torso.draw(batch, parentAlpha);
    head.draw(batch, parentAlpha);
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
  public void setPosition(float x, float y)
  {
    super.setPosition(x, y);
    legs.setPosition(x, y);
    arms.setPosition(x, y);
    torso.setPosition(x, y);
    head.setPosition(x, y);
  }

  @Override
  public void setScale(float scale)
  {
    super.setScale(scale);
    legs.setScale(scale);
    arms.setScale(scale);
    torso.setScale(scale);
    head.setScale(scale);
  }
}
