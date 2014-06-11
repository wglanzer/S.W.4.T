package de.swat.entity.entities;

import de.swat.common.gui.assets.keys.ResourceKey;
import de.swat.entity.IControllable;
import de.swat.entity.IMovableEntity;

/**
 * Spieler
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class Player extends BaseEntity implements IMovableEntity, IControllable
{
  public Player()
  {
    super(assets.getResource(ResourceKey.PLAYER_HEAD), assets.getResource(ResourceKey.PLAYER_ARMS),
        assets.getResource(ResourceKey.PLAYER_TORSO), assets.getResource(ResourceKey.PLAYER_LEGS));
    setScale(0.2f);
  }
}
