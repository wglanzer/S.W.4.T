package de.swat.core;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.common.gui.assets.IAssets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstrakte Stage-Klasse, die die allgemeinen Funktionen der Stage erweitert
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class AbstractStage extends Stage
{

  /**
   * Logger, der in allen Stages gleich verwendet werden kann.
   */
  protected final Logger logger = LogManager.getLogger();

  /**
   * Einfache Referenz auf die Assets, dass nicht immer über die CorePreferences gegangen werden muss
   */
  protected final IAssets assets = CorePreferences.getAssets();
}
