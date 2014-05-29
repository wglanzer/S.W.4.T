package de.swat.clientserverintercom;

import de.swat.clientserverintercom.client.AbstractClient;
import de.swat.clientserverintercom.client.IClient;
import org.apache.logging.log4j.LogManager;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

/**
 * Client, der startbar ist
 *
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableClient extends AbstractClient
{

  protected StartableClient()
  {
    new Thread(new _EndlessThread(this)).start();
  }

  public static void main(String[] args)
  {
    try
    {
      new StartableClient().connect(InetAddress.getByName("127.0.0.1"), 8080);
    }
    catch(IOException e)
    {
      LogManager.getLogger().catching(e);
    }
  }

  @Override
  public void onServerMessage(SendablePackage pMessage)
  {
    System.out.println("Server: " + pMessage);
  }

  private class _EndlessThread implements Runnable
  {
    private IClient client;

    private _EndlessThread(IClient pClient)
    {
      client = pClient;
    }

    @Override
    public void run()
    {
      Scanner in = new Scanner(System.in);

      while(in.hasNext())
      {
        String s = in.next();
        client.sendServerMessage(new SendablePackage(s));
      }
    }
  }
}
