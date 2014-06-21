package de.swat.MapCreator.ribbon.actions;

import de.swat.constants.IRibbonConstants;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.utils.ImageUtil;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action, um ein MapDataModel zu von der Festplatte zu laden
 *
 * @author W. Glanzer, 18.02.14
 */
@RibbonAction
public class OpenAction extends AbstractRibbonAction
{
  @Override
  public ResizableIcon getIcon()
  {
    return ImageUtil.loadResizableIcon(OpenAction.class.getResource("folder.png"), IRibbonConstants.ICON_SIZE);
  }

  @Override
  public String getTitle()
  {
    return "Open...";
  }

  @Override
  public int getPosition()
  {
    return 100;
  }

  @Override
  public ERibbonCategory getCategory()
  {
    return ERibbonCategory.COMMON;
  }

  @Override
  public ERibbonSubCategory getSubCategory()
  {
    return ERibbonSubCategory.FILE;
  }

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, final IMapCreatorImage pMapCreatorImage)
  {
    final JFileChooser fileChooser = new JFileChooser();
    fileChooser.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        fileChooser.removeActionListener(this);
        if (fileChooser.getSelectedFile() != null)
        {
//          Map map = new Map();
          //          pMapCreatorImage.setMap(map);
        }
      }
    });
    fileChooser.showOpenDialog(null);
  }

}
