package de.swat.datamodels.ribbonactions;

import de.swat.annotationProcessors.annotations.RibbonAction;
import de.swat.datamodels.AbstractRibbonAction;
import de.swat.datamodels.IMapCreatorImage;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import de.swat.utils.ImageUtil;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Action zur Hintergrundbild-Änderung
 *
 * @author W. Glanzer, 29.03.2014
 */
@RibbonAction
public class ChangeBackgroundAction extends AbstractRibbonAction  //todo Soll ins PropertySheet
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
          pMapCreatorImage.getMap().setBackgroundImage(ImageUtil.loadFileAsImage(fileChooser.getSelectedFile()));
      }
    });
    fileChooser.showOpenDialog(null);
  }

  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return null;
  }

  @Override
  public String getTitle()
  {
    return "Change Background";
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
    return ERibbonSubCategory.MAP;
  }
}