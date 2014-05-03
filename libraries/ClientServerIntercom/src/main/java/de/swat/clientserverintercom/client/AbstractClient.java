package de.swat.clientserverintercom.client;

import de.swat.clientserverintercom.SendablePackage;

import java.util.ArrayList;
import java.util.List;

/**
 * Einfache Klasse, die einen Client darstellt
 *
 * @author W.Glanzer, 27.04.2014.
 */
public abstract class AbstractClient implements IClient
{
  private final List<SendablePackage> messageBuffer = new ArrayList<>();
  private boolean wantsToDisconnect = false;
  private String serverIP;
  private int serverPort;

  protected AbstractClient(String pServerIP, int pServerPort)
  {
    serverIP = pServerIP;
    serverPort = pServerPort;
  }

  @Override
  public abstract void onServerMessage(SendablePackage pMessage);

  @Override
  public void sendServerMessage(SendablePackage pMessage)
  {
    synchronized(messageBuffer)
    {
      messageBuffer.add(pMessage);
    }
  }

  @Override
  public SendablePackage getMessageToSend()
  {
    synchronized(messageBuffer)
    {
      if(messageBuffer.size() <= 0)
        return null;

      SendablePackage toSend = messageBuffer.get(0);
      messageBuffer.remove(toSend);
      return toSend;
    }
  }

  @Override
  public boolean wantsToDisconnect()
  {
    return wantsToDisconnect;
  }

  @Override
  public void disconnect()
  {
    wantsToDisconnect = true;
  }

  @Override
  public String getServerIP()
  {
    return serverIP;
  }

  @Override
  public int getServerPort()
  {
    return serverPort;
  }
}
