package de.swat.MapCreator.gui.ribbon.applicationMenu;

import de.swat.MapCreator.gui.dialogs.SendMapDialog;
import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author W.Glanzer, 03.05.2014.
 */
public class SendMapAction extends RibbonApplicationMenuEntryPrimary
{

  public SendMapAction()
  {
    super(new EmptyResizableIcon(32), "Send map to S.W.4.T-Server", new _ActionListener(), JCommandButton.CommandButtonKind.ACTION_ONLY);
  }

  private static class _ActionListener implements ActionListener
  {
    @Override
    public void actionPerformed(ActionEvent e)
    {
      SendMapDialog dialog = new SendMapDialog();
      dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
      dialog.setVisible(true);
    }
  }

}
