package de.swat.mapCreator.gui.ribbon;

import de.swat.SwatRuntimeException;
import de.swat.enums.ERibbonCategory;
import de.swat.enums.ERibbonSubCategory;
import org.jetbrains.annotations.Nullable;
import org.pushingpixels.flamingo.api.common.icon.ResizableIcon;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Abstrakte Klasse für RibbonActions.
 * Hier wird beispielsweise schon die compareTo-Methode erstellt.
 *
 * @author W. Glanzer, 23.02.14
 */
public abstract class AbstractRibbonAction implements IRibbonAction
{

  @Nullable
  @Override
  public ResizableIcon getIcon()
  {
    return null;
  }

  @Override
  public String getTitle()
  {
    return "[dummy]";
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
    return ERibbonSubCategory.FILE;
  }

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }

  @Override
  public void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IMapCreatorImage pMapCreatorImage)
  {
    throw new SwatRuntimeException("Not implemented yet!", null);
  }
}
