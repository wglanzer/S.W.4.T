package de.swat.entity;

import com.badlogic.gdx.scenes.scene2d.Actor;
import de.swat.common.gui.assets.IAssets;
import de.swat.common.stages.CorePreferences;

/**
 * @author W.Glanzer, 03.06.2014.
 */
public class AbstractEntity extends Actor implements IEntity
{

  protected static final IAssets assets = CorePreferences.getAssets();

}
