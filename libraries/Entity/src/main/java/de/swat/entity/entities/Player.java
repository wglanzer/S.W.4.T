package de.swat.entity.entities;

import de.swat.*;
import de.swat.common.gui.animation.Animation;
import de.swat.common.gui.assets.keys.AnimationKey;
import de.swat.common.gui.assets.keys.ResourceKey;
import de.swat.entity.IControllable;
import de.swat.entity.IMovableEntity;
import de.swat.entity.IWeaponable;
import de.swat.entity.weapons.Weapon_P2000;

import java.awt.*;
import java.util.Arrays;

/**
 * Spieler
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class Player extends BaseEntity implements IMovableEntity, IControllable, IWeaponable
{
  private static final Point ORIGIN = new Point(114, 137);
  private final _ControlListener listener = new _ControlListener();
  private IWeapon weapon = new Weapon_P2000();

  public Player()
  {
    super(assets.getResource(ResourceKey.PLAYER_HEAD), assets.getResource(ResourceKey.PLAYER_ARMS),
        assets.getResource(ResourceKey.PLAYER_TORSO), assets.getResource(ResourceKey.PLAYER_LEGS), assets.getResource(ResourceKey.WEAPON_P99));
    GlobalControlManager.getDefault().addControlListener(listener);
    setScale(0.5f);
    setOrigin(ORIGIN.x, ORIGIN.y);
  }

  @Override
  public float getMoveSpeed()
  {
    return 100f;
  }

  @Override
  public IWeapon getCurrentWeapon()
  {
    return weapon;
  }

  @Override
  public void act(float pDelta)
  {
    super.act(pDelta);

    float moveSpeed = getMoveSpeed();
    translatePosition((listener.moveX * moveSpeed) * pDelta, (listener.moveY * moveSpeed) * pDelta);
  }

  @Override
  public void dispose()
  {
    super.dispose();
    GlobalControlManager.getDefault().removeControlListener(listener);
  }

  public void reload()  //todo gehört in eine Weapon-Klasse ausgelagert
  {
    getArms().showAnimation(AnimationKey.PLAYER_RELOAD, Animation.PlayMode.HALT);
  }

  public void lookToPoint(float pX, float pY)
  {
    float stageWidth = getStage().getWidth();
    float stageHeight = getStage().getHeight();

    float newRot = getRotation();

    if(stageWidth / 2 - pX < 0)
      newRot = (float) Math.toDegrees(Math.atan((double) (stageHeight / 2 - pY) / (double) (pX - stageWidth / 2)));

    else if(stageWidth / 2 - pX > 0)
      newRot = 180 + (float) Math.toDegrees(Math.atan((double) (pY - stageHeight / 2) / (double) (stageWidth / 2 - pX)));

    else
    {
      if(stageHeight / 2 - pY < 0)
        newRot = -90F;

      else if(stageHeight / 2 - pY > 0)
        newRot = 90F;

    }

    if(newRot < 0)
      newRot += 360;

    setRotation(-newRot);
  }

  private class _ControlListener implements IControlListener
  {
    public int moveX = 0;
    public int moveY = 0;

    @Override
    public boolean dispatchControlEvent(ControlEvent pEvent)
    {
      float[] modificators = pEvent.modificator;

      if(pEvent.type.equals(ControlEvent.Type.PLAYER_CONTROL))
      {
        switch(pEvent.subType)
        {
          case IEvent.PLAYER_MOVE:
            if(pEvent.isActivated)
            {
              moveX += modificators[0];
              moveY += modificators[1];
            }
            else
            {
              moveX -= modificators[0];
              moveY -= modificators[1];
            }
            break;

          case IEvent.PLAYER_ROTATE:
            if(modificators.length == 2)
              lookToPoint(modificators[0], modificators[1]);
            else
              throw new IllegalArgumentException("Modificators not valid! modificators=" + Arrays.toString(modificators));
            break;
        }
      }
      else if(pEvent.type.equals(ControlEvent.Type.PLAYER_ACTION))
      {
        switch(pEvent.subType)
        {
          case IEvent.PLAYER_SHOOT:
            if(pEvent.isActivated)
              getArms().showAnimation(AnimationKey.PLAYER_RECOIL, Animation.PlayMode.LOOP, 1000f / getCurrentWeapon().getFirerate());
            else
            {
              Animation anim = getArms().getAnimation();
              if(anim != null)
                anim.stop();
            }
            break;

          case IEvent.PLAYER_RELOAD:
            reload();
            break;
        }
      }

      // Event wird noch gebraucht
      return false;
    }
  }
}
