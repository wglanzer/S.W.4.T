package de.swat.clientserverintercom.client;

/**
 * Dieses Interface beschreibt einen Client und seine Aktionen
 *
 * @author W.Glanzer, 27.04.2014.
 */
public interface IClient
{

  void onServerMessage(String pMessage);

  String getServerIP();

  String getMessageToSend();

  int getServerPort();

  boolean disconnect();
}
