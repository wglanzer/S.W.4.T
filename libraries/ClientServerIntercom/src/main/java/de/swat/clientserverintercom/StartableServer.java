package de.swat.clientserverintercom;

import de.swat.clientserverintercom.server.AbstractServer;
import de.swat.clientserverintercom.server.ServerStarter;
import de.swat.logging.ConsoleLogger;
import de.swat.logging.LoggerRegisterer;

import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableServer extends AbstractServer
{

  public StartableServer()
  {
    super(8080);
  }

  public static void main(String[] args)
  {
    LoggerRegisterer.register(new ConsoleLogger());

    System.out.println("Server starting ...");
    ServerStarter.startServer(new StartableServer());
    System.out.println("Server started!");
  }

  @Override
  public void onClientMessage(String pMessage, Socket pClient)
  {
    sendClientMessage(pMessage);
  }
}
