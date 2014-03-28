package de.swat.ribbonactions;

import de.swat.AbstractRibbonAction;
import de.swat.annotations.RibbonAction;
import de.swat.constants.IRibbonConstants;
import de.swat.enums.*;
import de.swat.util.ImageUtil;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Action, um ein MapDataModel zu von der Festplatte zu laden
 *
 * @author W. Glanzer, 18.02.14
 */
@RibbonAction
public class OpenAction extends AbstractRibbonAction
{
  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker)
  {
    //if (pModelAccess instanceof MapCreatorModelAccess)
    //{
    //  final MapCreatorModelAccess modelAccess = (MapCreatorModelAccess) pModelAccess;
    //
    //  final JFileChooser fileChooser = new JFileChooser();
    //  fileChooser.addActionListener(new ActionListener()
    //  {
    //    @Override
    //    public void actionPerformed(ActionEvent e)
    //    {
    //      fileChooser.removeActionListener(this);
    //      if (fileChooser.getSelectedFile() != null)
    //      {
    //        MapModelAccess newDataModel = (MapModelAccess) SaveUtil.load(fileChooser.getSelectedFile());
    //        MapCreatorMap map = new MapCreatorMap(modelAccess, new Map(newDataModel));
    //        modelAccess.setMapCreatorMap(map);
    //      }
    //    }
    //  });
    //  fileChooser.showOpenDialog(null);
    //}
  }

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

}
