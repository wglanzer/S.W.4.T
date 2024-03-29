package de.swat.clientserverintercom;

/**
 * Alle Konstanten, die für die Kommunikation für Client und Server gebraucht werden
 *
 * @author W.Glanzer, 27.04.2014.
 */
public interface ICSInterConstants
{

  public static final int SERVER_PORT = 8080; //todo Port ändern
  public static final int FILETRANSFERPORT = 8080;

  //Daten, die zwischen Client und Server verschickt werden
  public static final String CLIENT_DC = "n0";
  public static final String FILETRANSFER_CLASS = "n1";
  public static final String FILETRANSFER_FILENAME = "n2";
  public static final String CLIENT_SEARCH_COMMAND = "n3";
  public static final String SERVER_HEARTBEAT = "n4";
  public static final String PACKAGE_START = "$PKG_S$";
  public static final String PACKAGE_END = "$PKG_E$";
  public static final String ENCODING = "encoding";
  public static final String ENC_BASE64 = "base64";
}
