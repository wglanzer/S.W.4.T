package de.swat.mapCreator.gui.ribbon.actions;

import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.mapCreator.gui.dialogs.SendMapDialog;
import de.swat.mapCreator.gui.ribbon.AbstractRibbonAction;
import de.swat.mapCreator.gui.ribbon.IMapCreatorImage;
import de.swat.mapCreator.gui.ribbon.RibbonAction;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * @author W.Glanzer, 24.06.2014.
 */
@RibbonAction
public class SendMapAction extends AbstractRibbonAction
{

  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return new EmptyResizableIcon(16);
  }

  @Override
  public String getTitle()
  {
    return "Transmit...";
  }

  @Override
  public int getPosition()
  {
    return 0;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return ERibbonCategory.COMMON;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return ERibbonSubCategory.REMOTE;
  }

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {
    SendMapDialog dialog = new SendMapDialog();
    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
    dialog.setVisible(true);
  }
}
