package de.swat.clientserverintercom.runnables;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.client.AbstractClient;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketException;

/**
 * @author W.Glanzer, 17.05.2014.
 */
public class ClientListenRunnable implements Runnable
{
  private AbstractClient client;

  public ClientListenRunnable(AbstractClient pClient)
  {
    client = pClient;
  }

  @Override
  public void run()
  {
    try
    {
      Socket socket = client.getSocket();
      BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      String line;
      String tempString = "";
      while(!socket.isClosed() && (line = in.readLine()) != null)
      {
        tempString += line;

        if(tempString.contains(ICSInterConstants.PACKAGE_START) && tempString.contains(ICSInterConstants.PACKAGE_END))
        {
          client.onServerMessage(new SendablePackage(tempString));
          tempString = "";
        }
      }
    }
    catch(SocketException e)
    {
      LogManager.getLogger().error("InputReaderThread stopped (" + e.getCause() + ")", e);
    }
    catch(IOException e)
    {
      LogManager.getLogger().catching(e);
    }
  }
}
