package de.swat.android;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.*;
import java.util.Enumeration;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class ServerThread
{

  // designate a port
  public static final int SERVERPORT = 8080;
  // default ip
  public static String SERVERIP = "192.168.2.102";
  private static ServerSocket serverSocket;

  public static void main(String[] args)
  {
    Thread thread = new Thread(new thread());
    thread.start();
  }

  // gets the ip address of your phone's network
  private String getLocalIpAddress()
  {
    try
    {
      for(Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); )
      {
        NetworkInterface intf = en.nextElement();
        for(Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); )
        {
          InetAddress inetAddress = enumIpAddr.nextElement();
          if(!inetAddress.isLoopbackAddress())
          {
            return inetAddress.getHostAddress().toString();
          }
        }
      }
    }
    catch(SocketException ex)
    {
      System.out.println("ServerActivity"+ ex.toString());
    }
    return null;
  }

  public static class thread implements Runnable
  {

    public void run()
    {
      try
      {
        if(SERVERIP != null)
        {
          System.out.println("Listening on IP: " + SERVERIP);
          serverSocket = new ServerSocket(SERVERPORT);
          while(true)
          {
            // listen for incoming clients
            Socket client = serverSocket.accept();
            System.out.println("Connected.");

            try
            {
              BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
              String line = null;
              while((line = in.readLine()) != null)
              {
                System.out.println("ServerActivity"+ line);
              }
              break;
            }
            catch(Exception e)
            {
                  System.out.println("Oops. Connection interrupted. Please reconnect your phones.");
              e.printStackTrace();
            }
          }
        }
        else
        {
              System.out.println("Couldn't detect internet connection.");
        }
      }
      catch(Exception e)
      {
            System.out.println("Error");
        e.printStackTrace();
      }
    }
  }

}
