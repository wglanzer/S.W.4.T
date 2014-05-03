package de.swat.fileTransfer;

import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.server.AbstractServer;
import de.swat.clientserverintercom.server.ServerStarter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;
import java.net.Socket;

/**
 * Server, der Dateien zwischen Client -> Server verschicken kann.
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class FileTransferServer extends AbstractServer
{

  private File storeDirectory;

  public FileTransferServer(File pStoreDirectory)
  {
    super(10550);
    storeDirectory = pStoreDirectory;
    ServerStarter.startServer(this);
  }

  @Override
  public void onClientMessage(SendablePackage pMessage, Socket pClient)
  {
    String name = "test.txt";

    try
    {
      FileUtils.writeStringToFile(new File(storeDirectory.getPath() + File.separator + name), pMessage.getMessage());
    }
    catch(IOException e)
    {
      LogManager.getLogger().error("Error writing text to file!", e);
    }
  }
}
