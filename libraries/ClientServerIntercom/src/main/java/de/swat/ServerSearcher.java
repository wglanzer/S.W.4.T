package de.swat;

import de.swat.clientserverintercom.ICSInterConstants;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

/**
 * Durchsucht das lokale Netz nach Servern
 *
 * @author W.Glanzer, 04.05.2014.
 */
public class ServerSearcher
{
  private static final int CONNECT_TIMEOUT = 10;
  private static final int MESSAGE_TIMEOUT = 10;

  private static ExecutorService es;
  private static Map<Future<Boolean>, String> futures = new HashMap<>();

  /**
   * Gibt alle verfügbaren Server im lokalen Subnet zurück (ThreadCount: 40)
   *
   * @return Vector mit IPs als Strings
   */
  public static Vector<String> search()
  {
    return search(40);
  }

  /**
   * Gibt alle verfügbaren Server im lokalen Subnet zurück
   *
   * @param pNumberSearchThreads  Gibt an, wie viele Threads zum Suchen verwendet werden sollen
   * @return Vector mit IPs als Strings
   */
  public static Vector<String> search(int pNumberSearchThreads)
  {
    Vector<String> returnVector = new Vector<>();
    es = Executors.newFixedThreadPool(pNumberSearchThreads);

    try
    {
      LogManager.getLogger().info("Start searching...");

      List<String> allPossibleIPs = getAllPossibleIPs();

      for(String currIP : allPossibleIPs)
        futures.put(_connectable(currIP, ICSInterConstants.SERVER_PORT), currIP);

      es.shutdown();

      for(Future<Boolean> currConnectable : futures.keySet())
      {
        try
        {
          if(currConnectable.get(CONNECT_TIMEOUT + MESSAGE_TIMEOUT, TimeUnit.MILLISECONDS))
            returnVector.add(futures.get(currConnectable));
        }
        catch (Exception e)
        {
        }
      }

      LogManager.getLogger().info("Search complete!");
    }
    catch(Exception e)
    {
      LogManager.getLogger().error("Error while searching for servers!", e);
    }

    return returnVector;
  }

  /**
   * Erstellt ein neues Future das zurückgibt, ob man mit der übergebenen IP und dem übergebenen Port
   * auf einen Socket zugreifen kann
   *
   * @param ip    IP-Adresse des Sockets
   * @param port  Port des Sockets
   */
  private static Future<Boolean> _connectable(final String ip, final int port)
  {
    return es.submit(new Callable<Boolean>()
    {
      @Override
      public Boolean call()
      {
        try (Socket socket = new Socket())
        {
          socket.connect(new InetSocketAddress(ip, port), CONNECT_TIMEOUT);

          new PrintStream(socket.getOutputStream()).println(ICSInterConstants.CLIENT_SEARCH_COMMAND);
          BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
          String line;
          if((line = in.readLine()) != null)
          {
            if(line.equals(ICSInterConstants.SERVER_HEARTBEAT))
            {
              socket.close();
              return true;
            }
          }

          return false;
        }
        catch(Exception ex)
        {
          return false;
        }
      }
    });
  }

  /**
   * Gibt alle IPs der Computer zurück, die im lokalen Subnet sind
   *
   * @return Liste aus IPs der lokalen Subnet-Computern
   * @throws Exception
   */
  public static List<String> getAllPossibleIPs() throws Exception
  {
    InetAddress localHost = Inet4Address.getLocalHost();
    NetworkInterface networkInterface = NetworkInterface.getByInetAddress(localHost);
    String ipAddress = localHost.getHostAddress();
    short subnetPref = networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength();

    String[] ip = ipAddress.split("\\.");
    Integer[] subnetMask = _convertPrefixToMask(16);

    return _listIPs(_IPNum(Integer.parseInt(ip[0]), Integer.parseInt(ip[1]), Integer.parseInt(ip[2]), Integer.parseInt(ip[3])),
        _IPNum(subnetMask[0], subnetMask[1], subnetMask[2], subnetMask[3]));
  }

  /**
   * Konvertiert eine Subnet-Prefix in eine Subnet-Mask
   *
   * @param pPrefix Prefix der Subnet
   * @return Int[] mit 4 Stellene
   */
  private static Integer[] _convertPrefixToMask(int pPrefix)
  {
    List<Integer> ip = new ArrayList<>();
    while(pPrefix >= 8)
    {
      ip.add(255);
      pPrefix -= 8;
    }
    if(pPrefix % 8 != 0)
      ip.add(256 - (int) Math.pow(2.0, 8 - (pPrefix % 8)));
    while(ip.size() < 4)
      ip.add(0);

    return ip.toArray(new Integer[ip.size()]);
  }

  /**
   * Wandelt eine IP/Subnetmask in eine Integer-Zahl um
   *
   * @param a Erstes Oktett
   * @param b Zweites Oktett
   * @param c Drittes Oktett
   * @param d Viertes Oktett
   * @return Integer der vier oktette
   */
  private static int _IPNum(int a, int b, int c, int d)
  {
    return ((a * 256 | b) * 256 | c) * 256 | d;
  }

  /**
   * Gibt alle IPs, die der SubnetMask und der IP entsprechen, zurück.l
   *
   * @param ip   IPv4-Adresse
   * @param mask Subnet-Mask zur IP-Adresse
   */
  private static List<String> _listIPs(int ip, int mask)
  {
    int index;
    int count = 1;
    int temp = mask;
    long a, b, c, d;
    List<String> returnIPs = new ArrayList<>();

    while((temp & 1) == 0)
    {
      count *= 2;
      temp >>= 1;
    }

    for(index = 1; index < count - 1; index++)
    {
      long newIP = ((ip & mask) | index) & 0xFFFFFFFFL;

      d = newIP & 0xFF;
      c = (newIP / 256) & 0xFF;
      b = (newIP / 65536) & 0xFF;
      a = (newIP / 16777216) & 0xFF;

      returnIPs.add(a + "." + b + "." + c + "." + d);
    }

    return returnIPs;
  }
}
