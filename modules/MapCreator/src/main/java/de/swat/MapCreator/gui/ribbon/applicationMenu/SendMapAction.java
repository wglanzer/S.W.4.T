package de.swat.MapCreator.gui.ribbon.applicationMenu;

import org.pushingpixels.flamingo.api.common.JCommandButton;
import org.pushingpixels.flamingo.api.common.icon.EmptyResizableIcon;
import org.pushingpixels.flamingo.api.ribbon.RibbonApplicationMenuEntryPrimary;

/**
 * @author W.Glanzer, 03.05.2014.
 */
public class SendMapAction extends RibbonApplicationMenuEntryPrimary
{

  public SendMapAction()
  {
    super(new EmptyResizableIcon(32), "<DUMMY>", null, JCommandButton.CommandButtonKind.ACTION_ONLY);
  }


}
