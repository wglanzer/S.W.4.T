package de.swat.fileTransfer;

import de.swat.clientserverintercom.ICSInterConstants;
import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.client.AbstractClient;
import de.swat.clientserverintercom.client.ClientStarter;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;

import java.io.File;
import java.io.IOException;

/**
 * Client, der Dateien zwischen Client -> Server verschicken kann.
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class FileTransferClient extends AbstractClient
{
  public FileTransferClient(String pServerIP)
  {
    super(pServerIP, ICSInterConstants.FILETRANSFERPORT);
    ClientStarter.startClient(this);
  }

  /**
   * Sendet dem Client ein File
   *
   * @param pFile File, das geschickt werden soll
   */
  public void sendServerMessage(File pFile)
  {
    try
    {
      SendablePackage sendablePackage = new SendablePackage();
      sendablePackage.setMessage(FileUtils.readFileToString(pFile));
      sendablePackage.putProperty(ICSInterConstants.FILETRANSFER_CLASS, File.class.getName());
      sendablePackage.putProperty(ICSInterConstants.FILETRANSFER_FILENAME, pFile.getName());
      sendServerMessage(sendablePackage);
    }
    catch(IOException e)
    {
      LogManager.getLogger().error("Error sending servermessage", e);
    }
  }

  @Override
  public void onServerMessage(SendablePackage pMessage)
  {
    LogManager.getLogger().error(pMessage, new UnsupportedOperationException());
  }
}
