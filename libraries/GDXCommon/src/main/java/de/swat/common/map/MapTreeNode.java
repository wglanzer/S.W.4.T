package de.swat.common.map;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * @author W.Glanzer, 08.07.2014.
 */
public class MapTreeNode extends DefaultMutableTreeNode
{

  private JPopupMenu menu;

  public MapTreeNode()
  {
  }

  public MapTreeNode(Object userObject)
  {
    super(userObject);
  }

  public JPopupMenu getMenu()
  {
    return menu;
  }

  public void setMenu(JPopupMenu pMenu)
  {
    menu = pMenu;
  }
}
