package de.swat.MapCreator;

import de.swat.exceptions.SwatRuntimeException;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

import javax.swing.*;

/**
 * Einsprungpunkt von außen in den Mapcreator
 *
 * @author W. Glanzer, 28.11.2013
 */
public class MapCreatorMain
{

  public MapCreatorMain()
  {
    GlobalKeyListenerManager.init();
    new MapCreator();
  }

  public static void main(String[] args)
  {
    _setLookAndFeel();
    new MapCreatorMain();
  }

  /**
   * Setzt das LookAndFeel
   */
  private static void _setLookAndFeel()
  {
    try
    {
      SwingUtilities.invokeLater(new Runnable()
      {
        @Override
        public void run()
        {
          SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin");
        }
      });
    }
    catch (Exception e)
    {
      throw new SwatRuntimeException("Look and Feel could not be set!", e);
    }
  }
}
