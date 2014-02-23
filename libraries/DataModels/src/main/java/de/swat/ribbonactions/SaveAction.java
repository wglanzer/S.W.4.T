package de.swat.ribbonactions;

import de.swat.*;
import de.swat.accesses.*;
import de.swat.annotations.RibbonAction;
import de.swat.constants.IRibbonConstants;
import de.swat.enums.*;
import de.swat.util.*;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.*;

/**
 * @author W. Glanzer, 21.02.14
 */
@RibbonAction
public class SaveAction extends AbstractRibbonAction
{
  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IModelAccess pModelAccess)
  {
    if (pModelAccess instanceof MapCreatorModelAccess)
    {
      final MapCreatorModelAccess modelAccess = (MapCreatorModelAccess) pModelAccess;

      final JFileChooser fileChooser = new JFileChooser();
      fileChooser.addActionListener(new ActionListener()
      {
        @Override
        public void actionPerformed(ActionEvent e)
        {
          fileChooser.removeActionListener(this);
          if (fileChooser.getSelectedFile() != null)
            SaveUtil.save(modelAccess.getMap().getModelAccess(), fileChooser.getSelectedFile());
        }
      });
      fileChooser.showOpenDialog(null);
    }
  }

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

}
