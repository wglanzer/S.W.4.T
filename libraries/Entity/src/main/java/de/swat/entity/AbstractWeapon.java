package de.swat.entity;

import com.badlogic.gdx.files.FileHandle;
import de.swat.IWeapon;

/**
 * Abstraktion einer Waffe
 *
 * @author W.Glanzer, 03.06.2014.
 */
public abstract class AbstractWeapon extends AbstractEntity implements IWeapon
{
  public AbstractWeapon(FileHandle pFileHandle)
  {
    super(pFileHandle);
  }
}
