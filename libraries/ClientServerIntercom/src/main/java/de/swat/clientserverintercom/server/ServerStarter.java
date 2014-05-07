package de.swat.clientserverintercom.server;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.constants.IVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

/**
 * Startet einen IServer
 *
 * @author W.Glanzer, 27.04.2014.
 */
public class ServerStarter
{

  private static Logger logger = LogManager.getLogger();

  /**
   * Startet einen Server in einem neuen Thread
   *
   * @param pServer Server, der gestartet werden soll
   */
  public static Thread startServer(IServer pServer)
  {
    return startServer(pServer, true);
  }

  /**
   * Startet einen Server
   *
   * @param pServer    Server, der gestartet werden soll
   * @param pNewThread <tt>true</tt>, wenn dazu ein neuer Thread gestartet werden soll
   */
  public static Thread startServer(IServer pServer, boolean pNewThread)
  {
    ServerRunnable servRun = new ServerRunnable(pServer);

    if(pNewThread)
    {
      Thread serverThread = new Thread(servRun);
      serverThread.setName(IVersion.MAJOR_NAME + " - IntercomServer");
      serverThread.start();
      return serverThread;
    }
    else
    {
      servRun.run();
      return null;
    }
  }

  /**
   * Server-Runnable-Impl
   */
  private static class ServerRunnable implements Runnable
  {
    private IServer server;

    private ServerRunnable(IServer pServer)
    {
      server = pServer;
    }

    @Override
    public void run()
    {
      int serverPort = server.getServerPort();

      try
      {
        ServerSocket serverSocket = new ServerSocket(serverPort);

        while(true)
        {
          //Auf Verbindungen hören
          Socket client = serverSocket.accept();
          if(client == null)
            break;

          server.onClientConnect(client);
          new Thread(new InputReaderThread(client, server)).start();
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
        logger.catching(e);
      }
    }
  }

  private static class InputReaderThread implements Runnable
  {

    private Socket client;
    private IServer server;

    private InputReaderThread(Socket pClient, IServer pServer)
    {
      client = pClient;
      server = pServer;
    }

    @Override
    public void run()
    {
      try
      {
        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
        String line;
        Map<String, Runnable> specialActions = server.getSpecialActions(client);

        String tempString = "";
        while((line = in.readLine()) != null)
        {
          if(specialActions.containsKey(line))
            specialActions.get(line).run();
          else
          {
            tempString += line;
            if(tempString.contains(ICSInterConstants.PACKAGE_START) && tempString.contains(ICSInterConstants.PACKAGE_END))
            {
              server.onClientMessage(new SendablePackage(tempString), client);
              tempString = "";
            }
          }
        }
      }
      catch(Exception e)
      {
        server.onClientConnectionInterrupt(client);
      }
    }
  }

}
