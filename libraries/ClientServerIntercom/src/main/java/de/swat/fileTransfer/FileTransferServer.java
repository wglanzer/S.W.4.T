package de.swat.fileTransfer;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.server.AbstractServer;
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

  private String applicationDirectory;

  public FileTransferServer(String pApplicationDirectory)
  {
    applicationDirectory = pApplicationDirectory;
    start(ICSInterConstants.FILETRANSFERPORT);
  }

  @Override
  public void onClientMessage(SendablePackage pMessage, Socket pClient)
  {
    String fileName = pMessage.getStringAttribute(ICSInterConstants.FILETRANSFER_FILENAME, "map.txt");
    String clazz = pMessage.getStringAttribute(ICSInterConstants.FILETRANSFER_CLASS, "");

    if(clazz.equals(File.class.getName()))
    {
      try
      {
        LogManager.getLogger().info("Got a File! (name=" + fileName + ")");
        FileUtils.writeStringToFile(new File(applicationDirectory + File.separator + fileName), pMessage.getMessage());
      }
      catch(IOException e)
      {
        LogManager.getLogger().error("Error writing text to file!", e);
      }
    }
  }
}
