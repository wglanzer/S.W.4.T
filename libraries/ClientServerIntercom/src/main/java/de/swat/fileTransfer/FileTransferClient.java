package de.swat.fileTransfer;

import de.swat.clientserverintercom.SendablePackage;
import de.swat.clientserverintercom.client.AbstractClient;
import de.swat.clientserverintercom.client.ClientStarter;
import org.apache.logging.log4j.LogManager;

/**
 * Client, der Dateien zwischen Client -> Server verschicken kann.
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class FileTransferClient extends AbstractClient
{
  public FileTransferClient(String pServerIP)
  {
    super(pServerIP, 10550);
    ClientStarter.startClient(this);

    sendServerMessage(new SendablePackage("Hallo Welt!"));
  }

  @Override
  public void onServerMessage(SendablePackage pMessage)
  {
    LogManager.getLogger().error(pMessage, new UnsupportedOperationException());
  }
}
