package de.swat.mapCreator.gui.ribbon.actions;

import de.swat.SwatRuntimeException;
import de.swat.constants.IRibbonConstants;
import de.swat.constants.IStaticConstants;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.mapCreator.gui.ribbon.AbstractRibbonAction;
import de.swat.mapCreator.gui.ribbon.IMapCreatorImage;
import de.swat.mapCreator.gui.ribbon.RibbonAction;
import de.swat.utils.ImageUtil;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * @author W. Glanzer, 21.02.14
 */
@RibbonAction
public class SaveAction extends AbstractRibbonAction
{
  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return ImageUtil.loadResizableIcon(SaveAction.class.getResource("diskette4.png"), IRibbonConstants.ICON_SIZE);
  }

  @Override
  public String getTitle()
  {
    return "Save...";
  }

  @Override
  public int getPosition()
  {
    return 200;
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
        fileChooser.removeActionListener(this);
        File selectedFile = fileChooser.getSelectedFile();
        if (selectedFile != null && selectedFile.getName().endsWith("." + IStaticConstants.MAP_ZIP_ENDING))
        {
          try
          {
            pMapCreatorImage.getMap().generateFileObject().generateZip(selectedFile.getParentFile(), selectedFile.getName());
          }
          catch(IOException e1)
          {
            throw new SwatRuntimeException("Could not create zip", e1);
          }
        }
      }
    });
    fileChooser.showOpenDialog(null);
  }

}
