package de.swat.clientserverintercom.client;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.constants.IVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class ClientStarter
{

  private static Logger logger = LogManager.getLogger();

  /**
   * Startet einen Client in einem neuen Thread
   *
   * @param pClient Client der gestartet werden soll
   */
  public static Thread startClient(IClient pClient)
  {
    return startClient(pClient, true);
  }

  /**
   * Startet den Client
   *
   * @param pClient    Client der gestartet werden soll
   * @param pNewThread <tt>true</tt> für einen neuen Thread
   */
  public static Thread startClient(IClient pClient, boolean pNewThread)
  {
    ClientRunnable client = new ClientRunnable(pClient);

    if(pNewThread)
    {
      Thread thread = new Thread(client);
      thread.setName(IVersion.MAJOR_NAME + " - Client");
      thread.start();
      return thread;
    }
    else
    {
      client.run();
      return null;
    }
  }

  /**
   * Client-Runnable-Impl
   */
  private static class ClientRunnable implements Runnable
  {
    private IClient client;

    private ClientRunnable(IClient pClient)
    {
      client = pClient;
    }

    @Override
    public void run()
    {
      try
      {
        InetAddress serverAddr = InetAddress.getByName(client.getServerIP());
        Socket socket = new Socket(serverAddr, client.getServerPort());

        new Thread(new InputReaderThread(socket, client)).start();
        while(!client.wantsToDisconnect() && socket.isConnected())
        {
          SendablePackage messageToSend = client.getMessageToSend();
          if(messageToSend != null)
          {
            try
            {
              PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
              out.println(messageToSend.getSendableString());
            }
            catch(Exception e)
            {
              logger.catching(e);
            }
          }
        }
        socket.close();
        logger.info("Socket closed.");
      }
      catch(Exception e)
      {
        logger.catching(e);
      }
    }
  }

  private static class InputReaderThread implements Runnable
  {

    private Socket server;
    private IClient client;

    private InputReaderThread(Socket pServer, IClient pClient)
    {
      server = pServer;
      client = pClient;
    }

    @Override
    public void run()
    {
      try
      {
        BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream()));
        String line;
        String tempString = "";
        while((line = in.readLine()) != null)
        {
          tempString += line;

          if(tempString.contains(ICSInterConstants.PACKAGE_START) && tempString.contains(ICSInterConstants.PACKAGE_END))
          {
            client.onServerMessage(new SendablePackage(tempString));
            tempString = "";
          }
        }
      }
      catch(Exception e)
      {
        logger.error("InputReaderThread stopped (" + e.getCause() + ")", e);
      }
    }
  }
}

