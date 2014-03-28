package de.swat.MapCreator;

import de.swat.constants.IStartArguments;
import de.swat.exceptions.SwatRuntimeException;
import de.swat.utils.PredefinedParameterUtil;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;

/**
 * Einsprungpunkt von außen in den Mapcreator
 *
 * @author W. Glanzer, 28.11.2013
 */
public class MapCreatorMain
{

  public MapCreatorMain()
  {
    new MapCreator();
  }

  public static void main(String[] args)
  {
    _setLookAndFeel();
    _parseArgs(args);
    new MapCreatorMain();
  }

  private static void _parseArgs(String[] pArgs)
  {
    for (String currArg : pArgs)
    {
      if (currArg.equals(IStartArguments.DEBUG))
        PredefinedParameterUtil.setDebugMode(true);
    }
  }

  /**
   * Setzt das LookAndFeel auf "Nimbus".
   * Dieses ist Standardmäßig bei Java dabei
   */
  private static void _setLookAndFeel()
  {
    try
    {
      SubstanceLookAndFeel.setSkin("org.pushingpixels.substance.api.skin.BusinessBlueSteelSkin");
    }
    catch (Exception e)
    {
      throw new SwatRuntimeException("Look and Feel could not be set!", e);
    }
  }
}
