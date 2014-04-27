package de.swat.clientserverintercom.server;

import de.swat.logging.LoggerRegisterer;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementiert einen voll funktionsfähigen Server
 *
 * @author W.Glanzer, 27.04.2014.
 */
public abstract class AbstractServer implements IServer
{

  private final Set<Socket> clients = new HashSet<>();
  private int serverPort = -1;

  public AbstractServer(int pServerPort)
  {
    serverPort = pServerPort;
  }

  @Override
  public void onClientConnect(@Nullable Socket pClient)
  {
    synchronized(clients)
    {
      if(pClient != null)
        clients.add(pClient);
    }
  }

  @Override
  public abstract void onClientMessage(String pMessage, Socket pClient);

  @Override
  public void sendClientMessage(Socket pClient, String pMessage)
  {
    try
    {
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pClient.getOutputStream())), true);
      out.println(pMessage);
    }
    catch(Exception e)
    {
      LoggerRegisterer.throwable(e);
    }
  }

  @Override
  public void sendClientMessage(String pMessage)
  {
    synchronized(clients)
    {
      for(Socket currSocket : clients)
        sendClientMessage(currSocket, pMessage);
    }
  }

  @Override
  public void onClientConnectionInterrupt(Socket pClient)
  {
    synchronized(clients)
    {
      clients.remove(pClient);
    }
  }

  @Override
  public int getServerPort()
  {
    return serverPort;
  }

}
