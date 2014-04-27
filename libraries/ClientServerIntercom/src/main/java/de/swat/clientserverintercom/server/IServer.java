package de.swat.clientserverintercom.server;

import org.jetbrains.annotations.Nullable;

import java.net.Socket;

/**
 * Dieses Interface beschreibt einen Server und seine Aktionen
 *
 * @author W.Glanzer, 27.04.2014.
 */
public interface IServer
{

  /**
   * Wird ausgeführt, wenn ein Client connected
   *
   * @param pClient  Client, der gerade connected
   */
  void onClientConnect(@Nullable Socket pClient);

  /**
   * Wird ausgeführt, wenn der Client eine Nachricht an den Server schickt
   *
   * @param pMessage
   */
  void onClientMessage(String pMessage);

  /**
   * Wird ausgeführt, wenn der Client die Verbindung verliert
   *
   * @param pClient Client, der die Verbindung verloren hat
   */
  void onClientConnectionInterrupt(Socket pClient);

  /**
   * @return Liefert den Port, auf den der Server hört
   */
  int getServerPort();

  /**
   * Sendet eine Nachricht an einen spezifischen Client
   *
   * @param pClient Client, an den die Nachricht geschickt werden soll
   * @param pMessage  Nachricht, die geschickt werden soll
   */
  void sendClientMessage(Socket pClient, String pMessage);

  /**
   * Schickt eine Nachricht an alle verbundenen Clients
   *
   * @param pMessage  Nachricht, die geschickt werden soll
   */
  void sendClientMessage(String pMessage);
}
