package de.swat.clientserverintercom;

import org.apache.commons.codec.binary.Base64;
import org.jetbrains.annotations.NotNull;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Paket, das zwischen Client und Server verschickt werden kann
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class SendablePackage
{

  private static final String PROPERTY_OPEN = "[[<--";
  private static final String PROPERTY_CLOSE = "-->]]";
  private static final String PROPERTY_EQUALS = "=";

  private static final Charset CHARSET = Charset.forName("utf-8");
  private IEncoder encoder = new _Base64Encoder();

  private String message = "";
  private Map<String, String> properties = new HashMap<>();

  public SendablePackage()
  {
    this(""); //Zur vereinheitlichung
  }

  public SendablePackage(String pMessage)
  {
    if(pMessage.contains(ICSInterConstants.PACKAGE_START) || pMessage.contains(ICSInterConstants.PACKAGE_END))
      //unwrap from packagestart-endings
      pMessage = pMessage.substring(pMessage.indexOf(ICSInterConstants.PACKAGE_START) + ICSInterConstants.PACKAGE_START.length(),
          pMessage.indexOf(ICSInterConstants.PACKAGE_END));

    if(pMessage.contains(PROPERTY_OPEN))
      //unwrap from propertyopens-closes
      pMessage = unwrap(pMessage);

    // Dann war es vorher gesetzt und muss decodiert werden
    if(getStringAttribute(ICSInterConstants.ENCODING).equals(ICSInterConstants.ENC_BASE64))
      pMessage = encoder.decode(pMessage);
    else
      putProperty(ICSInterConstants.ENCODING, ICSInterConstants.ENC_BASE64);

    setMessage(pMessage);
  }

  /**
   * Legt ein Property in die properties-Map
   *
   * @param pKey   Key der Property
   * @param pValue Value der Property
   */
  public void putProperty(String pKey, String pValue)
  {
    properties.put(pKey, pValue);
  }

  /**
   * Verpackt den übergebenen String in dieses Objekt.
   * Hier werden die Properties und die Message dieses Objekts angepasst.
   *
   * @param pWrapping String, der hier verpackt werden soll
   */
  public String unwrap(String pWrapping)
  {
    properties = transformToMap(pWrapping);
    return pWrapping.substring(pWrapping.lastIndexOf(PROPERTY_CLOSE) + PROPERTY_CLOSE.length());
  }

  /**
   * @return Gibt die Nachricht zurück, die mit diesem Package verschickt worden ist
   */
  public String getMessage()
  {
    return message;
  }

  /**
   * Setzt die Message
   *
   * @param pMessage Setzt die Message
   */
  public void setMessage(String pMessage)
  {
    message = pMessage;
  }

  @NotNull
  public String getStringAttribute(@NotNull String pKey)
  {
    String prop = properties.get(pKey);
    return prop != null && !prop.isEmpty() ? prop : "";
  }

  @NotNull
  public String getStringAttribute(@NotNull String pKey, String pDefault)
  {
    String prop = getStringAttribute(pKey);
    return !prop.isEmpty() ? prop : pDefault;
  }

  /**
   * @return Liefert einen String zurück, der zwischen Client und Server verschickt werden kann.
   */
  public String getSendableString()
  {
    return transformToString();
  }

  /**
   * @return String, der alle Informationen über das Package enthält.
   * Properties und Message werden wie folgt zusammengefügt:
   * <key1=val1><key2=val2>Hier kommt dann die Nachricht
   */
  private String transformToString()
  {
    String propString = ICSInterConstants.PACKAGE_START;

    for(Map.Entry<String, String> currEntry : properties.entrySet())
    {
      String tempString = "";
      tempString += currEntry.getKey() + PROPERTY_EQUALS + currEntry.getValue();
      propString += PROPERTY_OPEN + tempString + PROPERTY_CLOSE;
    }

    String messageBase64 = encoder.encode(message);
    return (propString + messageBase64 + ICSInterConstants.PACKAGE_END);
  }

  /**
   * @return Map, die die Keys und Values auseinanderfriemelt
   * und getrennt als "String, String"-Map zurückgibt
   */
  private Map<String, String> transformToMap(String pStringToTransform)
  {
    String stringCopy = pStringToTransform;
    Map<String, String> returnMap = new HashMap<>();

    int propOpenIndex = stringCopy.indexOf(PROPERTY_OPEN);
    int propCloseIndex = stringCopy.indexOf(PROPERTY_CLOSE);

    while(propOpenIndex > -1 && propCloseIndex > -1 && propOpenIndex < propCloseIndex)
    {
      String propString = stringCopy.substring(propOpenIndex, propCloseIndex + PROPERTY_CLOSE.length());
      propString = propString.substring(PROPERTY_OPEN.length(), propString.length() - PROPERTY_CLOSE.length());
      String[] keyValue = propString.split(PROPERTY_EQUALS);
      if(keyValue.length == 2)
      {
        String key = keyValue[0];
        String value = keyValue[1];
        returnMap.put(key, value);
      }

      //Für den nächsten Durchlauf vorbereiten
      stringCopy = stringCopy.substring(propCloseIndex + PROPERTY_CLOSE.length());
      propOpenIndex = stringCopy.indexOf(PROPERTY_OPEN);
      propCloseIndex = stringCopy.indexOf(PROPERTY_CLOSE);
    }

    return returnMap;
  }

  @Override
  public String toString()
  {
    return "SendablePackage{" +
        "message='" + message + '\'' +
        ", properties=" + properties +
        '}';
  }

  /**
   * IEncoder-Impl
   */
  private static class _Base64Encoder implements IEncoder
  {

    @Override
    public String encode(String pToEncode)
    {
      return new String(Base64.encodeBase64(pToEncode.getBytes()), CHARSET);
    }

    @Override
    public String decode(String pToDecode)
    {
      return new String(Base64.decodeBase64(pToDecode.getBytes()), CHARSET);
    }
  }
}
