package de.swat.datamodels.ribbonactions;

import de.swat.annotationProcessors.annotations.RibbonAction;
import de.swat.constants.IRibbonConstants;
import de.swat.datamodels.AbstractRibbonAction;
import de.swat.datamodels.IMapCreatorImage;
import de.swat.datamodels.Map;
import de.swat.datamodels.accesses.MapModelAccess;
import de.swat.datamodels.util.ImageUtil;
import de.swat.datamodels.util.SaveUtil;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
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
          MapModelAccess newDataModel = (MapModelAccess) SaveUtil.load(fileChooser.getSelectedFile());
          Map map = new Map(newDataModel);
          pMapCreatorImage.setMap(map);
        }
      }
    });
    fileChooser.showOpenDialog(null);
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
