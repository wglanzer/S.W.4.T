package de.swat.clientserverintercom.runnables;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.server.IServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;

/**
 * @author W.Glanzer, 28.05.2014.
 */
public class ServerListenRunnable implements Runnable
{

  private Socket client;
  private IServer server;

  public ServerListenRunnable(Socket pClient, IServer pServer)
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
