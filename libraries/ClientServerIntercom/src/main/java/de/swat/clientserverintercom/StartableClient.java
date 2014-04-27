package de.swat.clientserverintercom;

import de.swat.clientserverintercom.client.AbstractClient;
import de.swat.clientserverintercom.client.ClientStarter;

/**
 * Client, der startbar ist
 *
 * @author W.Glanzer, 27.04.2014.
 */
public class StartableClient extends AbstractClient
{

  protected StartableClient()
  {
    super("127.0.0.1", 8080);
    sendServerMessage("I'm connected!");
  }

  public static void main(String[] args)
  {
    ClientStarter.startClient(new StartableClient());
  }

  @Override
  public void onServerMessage(String pMessage)
  {
    System.out.println("Nachricht vom Server: " + pMessage);
  }
}
