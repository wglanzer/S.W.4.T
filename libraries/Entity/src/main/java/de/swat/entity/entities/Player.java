package de.swat.entity.entities;

import de.swat.*;
import de.swat.common.gui.animation.Animation;
import de.swat.common.gui.assets.keys.AnimationKey;
import de.swat.common.gui.assets.keys.ResourceKey;
import de.swat.entity.IControllable;
import de.swat.entity.IMovableEntity;
import de.swat.entity.IWeaponable;
import de.swat.entity.weapons.Weapon_P2000;

/**
 * Spieler
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class Player extends BaseEntity implements IMovableEntity, IControllable, IWeaponable
{
  private final _ControlListener listener = new _ControlListener();
  private IWeapon weapon = new Weapon_P2000();

  public Player()
  {
    super(assets.getResource(ResourceKey.PLAYER_HEAD), assets.getResource(ResourceKey.PLAYER_ARMS),
        assets.getResource(ResourceKey.PLAYER_TORSO), assets.getResource(ResourceKey.PLAYER_LEGS), assets.getResource(ResourceKey.WEAPON_P99));
    GlobalControlManager.getDefault().addControlListener(listener);
    setScale(0.5f);
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

  private class _ControlListener implements IControlListener
  {
    public int moveX = 0;
    public int moveY = 0;

    @Override
    public boolean dispatchControlEvent(ControlEvent pEvent)
    {
      if(pEvent.type.equals(ControlEvent.Type.PLAYER_CONTROL))
      {
        if(pEvent.isActivated)
        {
          moveX += pEvent.modificator[0];
          moveY += pEvent.modificator[1];
        }
        else
        {
          moveX -= pEvent.modificator[0];
          moveY -= pEvent.modificator[1];
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
