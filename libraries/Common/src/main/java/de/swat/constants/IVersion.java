package de.swat.constants;

import java.text.MessageFormat;

/**
 * Gibt die Versionen der einzelnen Module vor und beschreibt
 * allgeme Versionsinformationen von S.W.4.T
 *
 * @author W. Glanzer, 21.02.2014
 */
public interface IVersion
{

  public static final double DATA_MODEL_VERSION = 1.0;
  public static final long SERIAL_UID_VERSION = 1L;

  public static final String MAPCREATOR_VERSION = "1.0.0";
  public static final String ANDROID_VERSION = "1.0.0";

  public static final String MAJOR_NAME = "S.W.4.T";

  public static final String SWAT_TITLE_TEMPLATE = MAJOR_NAME + " - {0} - Version " + MAPCREATOR_VERSION;
  public static final String MAPCREATOR_TITLE = MessageFormat.format(SWAT_TITLE_TEMPLATE, "MapCreator");
}
