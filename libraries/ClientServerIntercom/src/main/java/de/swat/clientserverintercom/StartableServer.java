package de.swat.clientserverintercom;

import de.swat.clientserverintercom.server.IServer;
import de.swat.clientserverintercom.server.ServerStarter;

import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableServer implements IServer
{

  public static void main(String[] args)
  {
    System.out.println("Server starting ...");
    ServerStarter.startServer(new StartableServer());
    System.out.println("Server started!");
  }

  @Override
  public void onClientConnect(Socket pClient)
  {
    System.out.println("Client connected: " + pClient);
  }

  @Override
  public void onClientMessage(String pMessage)
  {
    System.out.println("Client message: " + pMessage);
  }

  @Override
  public void onClientConnectionInterrupt(Socket pClient)
  {
    System.out.println("Client-Connection interrupted: " + pClient);
  }

  @Override
  public int getServerPort()
  {
    return 8080;
  }
}
