package de.swat.entity.weapons;

import de.swat.entity.AbstractWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * @author W.Glanzer, 03.06.2014.
 */
public class Weapon_P2000 extends AbstractWeapon
{

  public Weapon_P2000()
  {
    super(null);
  }

  @Override
  public int getFirerate()
  {
    return 1;
  }

  @NotNull
  @Override
  public List<EFireMode> getFireMode()
  {
    return new ArrayList<>();
  }
}
