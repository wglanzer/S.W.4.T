package de.swat.clientserverintercom.runnables;

import de.swat.clientserverintercom.server.IServer;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author W.Glanzer, 28.05.2014.
 */
public class ServerConnectionRunnable implements Runnable
{

  private IServer server;
  private ServerSocket serverSocket;

  public ServerConnectionRunnable(IServer pServer)
  {
    server = pServer;
  }

  @Override
  public void run()
  {
    int serverPort = server.getServerPort();

    try
    {
      serverSocket = new ServerSocket(serverPort);

      while(true)
      {
        //Auf Verbindungen hören
        Socket client = serverSocket.accept();
        if(client == null)
          break;

        server.onClientConnect(client);
        new Thread(new ServerListenRunnable(client, server)).start();
      }
    }
    catch(Exception e)
    {
      LogManager.getLogger().catching(e);
    }
  }

  public void shutdown() throws IOException
  {
    serverSocket.close();
  }
}
