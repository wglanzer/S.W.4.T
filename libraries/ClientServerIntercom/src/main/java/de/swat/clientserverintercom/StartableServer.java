package de.swat.clientserverintercom;

import de.swat.clientserverintercom.server.AbstractServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableServer extends AbstractServer
{

  private static Logger logger = LogManager.getLogger();

  public StartableServer()
  {
    logger.info("Server starting...");
    start(8080);
    logger.info("Server started (Port: " + getServerPort() + ")!");
  }

  public static void main(String[] args)
  {
    new StartableServer();
  }

  @Override
  public void onClientMessage(SendablePackage pMessage, Socket pClient)
  {
    sendClientMessage(pMessage);
  }
}
