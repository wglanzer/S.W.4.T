package de.swat.clientserverintercom.client;

import de.swat.NoConnectionException;
import de.swat.clientserverintercom.SendablePackage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

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
  void sendServerMessage(SendablePackage pMessage) throws NoConnectionException;

  /**
   * Stellt die Verbindung zur angegebenen InetAdress und dessen Port her.
   * Speichert den Socket, damit er in getSocket() zurückgegeben werden kann
   *
   * @throws IOException
   */
  void connect(InetAddress pConnectTo, int pPort) throws IOException;

  /**
   * Löst die Verbindung des Sockets auf
   *
   * @throws IOException
   */
  void disconnect() throws IOException;

  /**
   * @return Socket, <tt>null</tt>, wenn der Client noch nirgends verbunden ist
   */
  Socket getSocket();

  /**
   * @return Server-IP
   */
  String getServerIP() throws NoConnectionException;

  /**
   * @return Server-Port
   */
  int getServerPort() throws NoConnectionException;
}
