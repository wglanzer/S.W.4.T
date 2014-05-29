package de.swat.clientserverintercom;

import de.swat.clientserverintercom.client.AbstractClient;
import de.swat.clientserverintercom.server.AbstractServer;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Testet, ob Client und Server miteinander kommunizieren können
 *
 * @author W.Glanzer, 07.05.2014.
 */
public class Test_ClientServerConnection
{
  private static final long TIMEOUT = 500;
  private final Object lock = new Object();
  private AbstractClient client;
  private AbstractServer server;
  private String lastGotMessage = "";

  @Before
  public void before()
  {
    server = new AbstractServer()
    {
      @Override
      public void onClientMessage(SendablePackage pMessage, Socket pClient)
      {
        sendClientMessage(pMessage);
      }
    };
    server.start(25525);

    client = new AbstractClient()
    {
      @Override
      public void onServerMessage(SendablePackage pMessage)
      {
        lastGotMessage = pMessage.getMessage();
        synchronized(lock)
        {
          lock.notifyAll();
        }
      }
    };

    try
    {
      client.connect(InetAddress.getByName("127.0.0.1"), 25525);
    }
    catch(IOException e)
    {
      LogManager.getLogger().catching(e);
    }
  }

  @Test
  public void test() throws Exception
  {
    String message1 = _prepareMessageAndSendAndWait("Hello World");
    Assert.assertEquals(message1, "Hello World");

    String message2 = _prepareMessageAndSendAndWait("äöü<>?!_.,<<8/^°€{}()[]=-*+@");
    Assert.assertEquals(message2, "äöü<>?!_.,<<8/^°€{}()[]=-*+@");
  }

  /**
   * Sendet eine Nachricht zum Server und erwartet diese wieder zurück
   */
  private String _prepareMessageAndSendAndWait(String pMessage) throws Exception
  {
    client.sendServerMessage(new SendablePackage(pMessage));

    synchronized(lock)
    {
      lock.wait(TIMEOUT);
    }

    return lastGotMessage;
  }

  @After
  public void after()
  {
    try
    {
      client.disconnect();
      server.stop();
    }
    catch(IOException e)
    {
      LogManager.getLogger().catching(e);
    }
  }
}
