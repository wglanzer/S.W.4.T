package de.swat.clientserverintercom.server;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.runnables.ServerConnectionRunnable;
import de.swat.constants.IVersion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.Nullable;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
  private Logger logger = LogManager.getLogger();
  private ServerConnectionRunnable servRun;

  @Override
  public void stop() throws IOException
  {
    for(Socket currClient : clients)
      currClient.close();
    servRun.shutdown();
    serverPort = -1;
  }

  @Override
  public void start(int pPort)
  {
    serverPort = pPort;
    servRun = new ServerConnectionRunnable(this);
    Thread serverThread = new Thread(servRun);
    serverThread.setName(IVersion.MAJOR_NAME + " - IntercomServer");
    serverThread.start();
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
  public abstract void onClientMessage(SendablePackage pMessage, Socket pClient);

  @Override
  public void sendClientMessage(Socket pClient, SendablePackage pMessage)
  {
    try
    {
      PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(pClient.getOutputStream())), true);
      out.println(pMessage.getSendableString());
    }
    catch(Exception e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void sendClientMessage(SendablePackage pMessage)
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

  @Override
  public Map<String, Runnable> getSpecialActions(Socket pClient)
  {
    HashMap<String, Runnable> hashMap = new HashMap<>();
    hashMap.put(ICSInterConstants.CLIENT_SEARCH_COMMAND, new SpecialActions.SearchAction(pClient));
    return hashMap;
  }
}
