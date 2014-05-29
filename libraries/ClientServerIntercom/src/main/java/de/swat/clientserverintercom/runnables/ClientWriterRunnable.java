package de.swat.clientserverintercom.runnables;

import de.swat.ValueObject;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.client.AbstractClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author W.Glanzer, 17.05.2014.
 */
public class ClientWriterRunnable implements Runnable
{
  public static final Object LOCK = new Object();
  private static ExecutorService execQueue = Executors.newFixedThreadPool(10);
  private AbstractClient client;
  private Logger logger = LogManager.getLogger();

  public ClientWriterRunnable(AbstractClient pClient)
  {
    client = pClient;
  }

  @Override
  public void run()
  {
    final Socket socket = client.getSocket();
    while(!socket.isClosed())
    {
      try
      {
        synchronized(LOCK)
        {
          while(!client.getMessageValue().hasValue())
          {
            LOCK.wait();
          }
        }

        execQueue.execute(new Runnable()
        {
          @Override
          public void run()
          {
            ValueObject<SendablePackage> messageValue = client.getMessageValue();
            while(messageValue.hasValue())
            {
              SendablePackage messageToSend = messageValue.getValue();
              if(messageToSend != null)
              {
                try
                {
                  OutputStream output = socket.getOutputStream();
                  output.write(messageToSend.getSendableString().getBytes());
                  output.write('\n');
                  output.flush();
                }
                catch(Exception e)
                {
                  logger.catching(e);
                }
              }
            }
          }
        });
      }
      catch(Exception e)
      {
        LogManager.getLogger().catching(e);
      }
    }

    execQueue.execute(new Runnable()
    {
      @Override
      public void run()
      {
        try
        {
          socket.close();
        }
        catch(IOException e)
        {
          logger.catching(e);
        }

        logger.info("Socket closed. (" + client + ")");
      }
    });
  }

}
