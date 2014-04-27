package de.swat.clientserverintercom.server;

import de.swat.clientserverintercom.client.IClient;

import java.net.Socket;

/**
 * Dieses Interface beschreibt einen Server und seine Aktionen
 *
 * @author W.Glanzer, 27.04.2014.
 */
public interface IServer
{

  void onClientConnect(Socket pClient);

  void onClientMessage(String pMessage);

  void onClientConnectionInterrupt(Socket pClient);

  int getServerPort();

  void sendClientMessage(IClient pSendTo);
}
