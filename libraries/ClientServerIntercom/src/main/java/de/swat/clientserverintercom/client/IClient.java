package de.swat.clientserverintercom.client;

import de.swat.clientserverintercom.SendablePackage;

/**
 * Dieses Interface beschreibt einen Client und seine Aktionen
 *
 * @author W.Glanzer, 27.04.2014.
 */
public interface IClient
{

  /**
   * Wird ausgeführt, wenn vom Server eine Nachricht empfangen wird
   *
   * @param pMessage  Nachricht, die der Server geschickt hat
   */
  void onServerMessage(SendablePackage pMessage);

  /**
   * Sendet eine Nachricht zum Server
   *
   * @param pMessage Nachricht, die gesendet werden soll
   */
  void sendServerMessage(SendablePackage pMessage);

  /**
   * Trennt die Verbindung zwischen Client und Server
   */
  void disconnect();

  /**
   * Liefert die Nachricht zurück, die zum Server geschickt werden soll.
   * Wenn nichts gesendet werden soll, dann <tt>null</tt>
   *
   * @return Die Nachricht, die gesendet werden soll
   */
  SendablePackage getMessageToSend();

  /**
   * @return <tt>true</tt>, wenn der Client sich vom Server trennen will
   */
  boolean wantsToDisconnect();

  /**
   * @return Server-IP
   */
  String getServerIP();

  /**
   * @return Server-Port
   */
  int getServerPort();
}
