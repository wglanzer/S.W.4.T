package de.swat.clientserverintercom;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * Paket, das zwischen Client und Server verschickt werden kann
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class SendablePackage
{

  private static final String PROPERTY_OPEN = "<";
  private static final String PROPERTY_CLOSE = ">";
  private static final String PROPERTY_EQUALS = "=";

  private String message;
  private Map<String, String> properties = new HashMap<>();

  public SendablePackage()
  {
  }

  public SendablePackage(String pMessage)
  {
    wrap(pMessage);
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
  public void wrap(String pWrapping)
  {
    properties = transformToMap(pWrapping);
    message = pWrapping.substring(pWrapping.lastIndexOf(PROPERTY_CLOSE) + 1);
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

  @Nullable
  public String getStringAttribute(@NotNull String pKey)
  {
    return properties.get(pKey);
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
    String propString = "";

    for(Map.Entry<String, String> currEntry : properties.entrySet())
    {
      String tempString = "";
      tempString += currEntry.getKey() + PROPERTY_EQUALS + currEntry.getValue();
      propString += PROPERTY_OPEN + tempString + PROPERTY_CLOSE;
    }

    return propString + message;
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
      String propString = stringCopy.substring(propOpenIndex, propCloseIndex + 1);
      propString = propString.substring(1, propString.length() - 1);
      String[] keyValue = propString.split(PROPERTY_EQUALS);
      if(keyValue.length == 2)
      {
        String key = keyValue[0];
        String value = keyValue[1];
        returnMap.put(key, value);
      }

      //Für den nächsten Durchlauf vorbereiten
      stringCopy = stringCopy.substring(propCloseIndex + 1);
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
}
