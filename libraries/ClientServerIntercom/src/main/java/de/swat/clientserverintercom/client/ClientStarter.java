package de.swat.clientserverintercom.client;

import de.swat.constants.IVersion;
import de.swat.logging.LoggerRegisterer;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class ClientStarter
{

  /**
   * Startet einen Client in einem neuen Thread
   *
   * @param pClient Client der gestartet werden soll
   */
  public static void startClient(IClient pClient)
  {
    startClient(pClient, true);
  }

  /**
   * Startet den Client
   *
   * @param pClient    Client der gestartet werden soll
   * @param pNewThread <tt>true</tt> für einen neuen Thread
   */
  public static void startClient(IClient pClient, boolean pNewThread)
  {
    ClientRunnable client = new ClientRunnable(pClient);

    if(pNewThread)
    {
      Thread thread = new Thread(client);
      thread.setName(IVersion.MAJOR_NAME + " - Client");
      thread.start();
    }
    else
      client.run();
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
        while(!client.wantsToDisconnect())
        {
          String messageToSend = client.getMessageToSend();
          if(messageToSend != null)
          {
            try
            {
              PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
              out.println(messageToSend);
            }
            catch(Exception e)
            {
              LoggerRegisterer.throwable(e);
            }
          }
        }
        socket.close();
      }
      catch(Exception e)
      {
        LoggerRegisterer.throwable(e);
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
        while((line = in.readLine()) != null)
          client.onServerMessage(line);
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }
}

