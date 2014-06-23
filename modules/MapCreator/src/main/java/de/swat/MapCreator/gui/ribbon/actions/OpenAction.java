package de.swat.mapCreator.gui.ribbon.actions;

import de.swat.SwatRuntimeException;
import de.swat.common.map.Map;
import de.swat.constants.IRibbonConstants;
import de.swat.constants.IStaticConstants;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.map.xml.MapFileObject;
import de.swat.mapCreator.gui.ribbon.AbstractRibbonAction;
import de.swat.mapCreator.gui.ribbon.IMapCreatorImage;
import de.swat.mapCreator.gui.ribbon.RibbonAction;
import de.swat.utils.ImageUtil;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

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
    FileNameExtensionFilter filter = new FileNameExtensionFilter(IStaticConstants.MAP_ZIP_ENDING_DESCRIPTION, IStaticConstants.MAP_ZIP_ENDING);
    fileChooser.setFileFilter(filter);
    fileChooser.setFileHidingEnabled(true);
    fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fileChooser.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        try
        {
          fileChooser.removeActionListener(this);
          File selectedFile = fileChooser.getSelectedFile();
          if(selectedFile != null && selectedFile.getName().endsWith("." + IStaticConstants.MAP_ZIP_ENDING))
          {
            Map map = new Map();
            MapFileObject mapFO = new MapFileObject();
            mapFO.fromZip(selectedFile);
            map.fromFileObject(mapFO);
            pMapCreatorImage.setMap(map);
          }
        }
        catch (Exception ex)
        {
          throw new SwatRuntimeException("", ex);
        }
      }
    });
    fileChooser.showOpenDialog(null);
  }

}
