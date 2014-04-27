package de.swat.clientserverintercom;

import de.swat.clientserverintercom.client.ClientStarter;
import de.swat.clientserverintercom.client.IClient;

/**
 * Client, der startbar ist
 *
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableClient implements IClient
{

  public static void main(String[] args)
  {
    ClientStarter.startClient(new StartableClient());
  }

  @Override
  public void onServerMessage(String pMessage)
  {
    System.out.println("Server sent a message: " + pMessage);
  }

  @Override
  public String getServerIP()
  {
    return "127.0.0.1";
  }

  @Override
  public String getMessageToSend()
  {
    try
    {
      Thread.sleep(5000);
    }
    catch(InterruptedException e)
    {
    }


    return  "Message " + hashCode();
  }

  @Override
  public int getServerPort()
  {
    return 8080;
  }

  @Override
  public boolean disconnect()
  {
    return false;
  }
}
