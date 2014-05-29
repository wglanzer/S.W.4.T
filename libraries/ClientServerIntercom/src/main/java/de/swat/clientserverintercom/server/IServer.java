package de.swat.clientserverintercom.server;

import de.swat.clientserverintercom.SendablePackage;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.net.Socket;
import java.util.Map;

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
   * @param pMessage Nachricht, die erhalten wurde
   * @param pClient  Client, von dem die Nachricht stammt
   */
  void onClientMessage(SendablePackage pMessage, Socket pClient);

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
   * @return Liefert eine Map zurück, die bestimmte Aktionen beinhaltet,
   * die bei bestimmten Nachrichten ausgeführt werden
   *
   * @param pClient Client, für den die specialActions zur Verfügung gestellt werden
   */
  Map<String, Runnable> getSpecialActions(Socket pClient);

  /**
   * Sendet eine Nachricht an einen spezifischen Client
   *
   * @param pClient Client, an den die Nachricht geschickt werden soll
   * @param pMessage  Nachricht, die geschickt werden soll
   */
  void sendClientMessage(Socket pClient, SendablePackage pMessage);

  /**
   * Schickt eine Nachricht an alle verbundenen Clients
   *
   * @param pMessage  Nachricht, die geschickt werden soll
   */
  void sendClientMessage(SendablePackage pMessage);

  /**
   * Stoppt den Server
   */
  void stop() throws IOException;

  /**
   * Startet den Server auf einem bestimmten Port
   */
  void start(int pPort);
}
