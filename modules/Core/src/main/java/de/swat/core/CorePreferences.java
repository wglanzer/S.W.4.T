package de.swat.core;

import de.swat.exceptions.SwatRuntimeException;

/**
 * Setzt die Preferences, die der Core benötigt
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class CorePreferences
{

  private static IAssets assets;

  public static IAssets getAssets()
  {
    if(assets == null)
      throw new SwatRuntimeException("Assets equals 'null'. Initialize CorePreferences!", null);

    return assets;
  }

  public static void setAssets(IAssets pAssets)
  {
    assets = pAssets;
  }
}
