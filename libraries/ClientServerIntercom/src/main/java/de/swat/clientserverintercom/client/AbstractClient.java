package de.swat.clientserverintercom.client;

import de.swat.NoConnectionException;
import de.swat.ValueObject;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.runnables.ClientListenRunnable;
import de.swat.clientserverintercom.runnables.ClientWriterRunnable;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Einfache Klasse, die einen Client darstellt
 *
 * @author W.Glanzer, 27.04.2014.
 */
public abstract class AbstractClient implements IClient
{
  private final ValueObject<SendablePackage> messageValue = new ValueObject<>();
  private String serverIP = null;
  private int serverPort = -1;
  private Thread writerThread;
  private Thread listenerThread;
  private Socket socket;

  @Override
  public abstract void onServerMessage(SendablePackage pMessage);

  @Override
  public void sendServerMessage(final SendablePackage pMessage) throws NoConnectionException
  {
    if(socket == null || !socket.isConnected())
      throw new NoConnectionException();

    synchronized(ClientWriterRunnable.LOCK)
    {
      messageValue.addValue(pMessage);

      //Alle Threads aufwecken
      ClientWriterRunnable.LOCK.notifyAll();
    }
  }

  public final ValueObject<SendablePackage> getMessageValue()
  {
    return messageValue;
  }

  @Override
  public void connect(InetAddress pConnectTo, int pPort) throws IOException
  {
    socket = new Socket(pConnectTo, pPort);
    serverIP = pConnectTo.getHostAddress();
    serverPort = pPort;

    Runnable listenRunnable = new ClientListenRunnable(this);
    listenerThread = new Thread(listenRunnable);
    listenerThread.setName("ListenerThread - IP:" + serverIP + " - Port:" + serverPort);
    listenerThread.start();

    Runnable writerRunnable = new ClientWriterRunnable(this);
    writerThread = new Thread(writerRunnable);
    writerThread.setName("WriterThread - IP:" + serverIP + " - Port:" + serverPort);
    writerThread.start();
  }

  @Override
  public void disconnect() throws IOException
  {
    socket.close();
  }

  public Socket getSocket()
  {
    return socket;
  }

  @Override
  public String getServerIP() throws NoConnectionException
  {
    if(socket == null || !socket.isConnected())
      throw new NoConnectionException();

    return serverIP;
  }

  @Override
  public int getServerPort() throws NoConnectionException
  {
    if(socket == null || !socket.isConnected())
      throw new NoConnectionException();

    return serverPort;
  }
}
