package de.swat.clientserverintercom.server;

import de.swat.clientserverintercom.ICSInterConstants;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @author W.Glanzer, 04.05.2014.
 */
public class SpecialActions
{

  /**
   * Sendet einem bestimmten Client einen Heartbeat
   */
  public static class SearchAction implements Runnable
  {
    private Socket client;

    public SearchAction(Socket pClient)
    {
      client = pClient;
    }

    @Override
    public void run()
    {
      try
      {
        new PrintStream(client.getOutputStream()).println(ICSInterConstants.SERVER_HEARTBEAT);
      }
      catch(IOException e)
      {
        LogManager.getLogger().error("Error while sending a server_hearbeat", e);
      }
    }
  }

}
