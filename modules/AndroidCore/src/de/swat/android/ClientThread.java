package de.swat.android;


import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

/**
 * @author W.Glanzer, 27.04.2014.
 */
public class ClientThread
{

  private static String serverIpAddress = "192.168.2.102";
  private static boolean connected = false;

  public static void main(String[] args)
  {
    Thread one = new Thread(new thread());
    one.start();
  }

  public static class thread implements Runnable
  {

    public void run()
    {
      try
      {
        InetAddress serverAddr = InetAddress.getByName(serverIpAddress);
        System.out.println("ClientActivity" + "C: Connecting...");
        Socket socket = new Socket(serverAddr, 8080);
        connected = true;
        while(connected)
        {
          try
          {
            System.out.println("ClientActivity" + "C: Sending command.");
            PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket
                .getOutputStream())), true);
            // where you issue the commands
            out.println("Hey Server!");

            Thread.sleep(1000);

            System.out.println("ClientActivity" + "C: Sent.");
          }
          catch(Exception e)
          {
            System.err.println("ClientActivity" + "S: Error" + e);
          }
        }
        socket.close();
        System.out.println("ClientActivity" + "C: Closed.");
      }
      catch(Exception e)
      {
        System.err.println("ClientActivity" + "C: Error" + e);
        connected = false;
      }
    }
  }

}
