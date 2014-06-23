package de.swat.mapCreator.gui.components;

import de.swat.common.map.Map;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

/**
 * Tree, der eine Map repräsentieren kann
 *
 * @author W.Glanzer, 23.06.2014.
 */
public class MapTree extends JTree
{

  private Map map;

  public MapTree(Map pMap)
  {
    map = pMap;
    setModel(new DefaultTreeModel(_getRootNode()));
  }

  public void mapChanged(Map pNewMap)
  {
    map = pNewMap;
    setModel(new DefaultTreeModel(_getRootNode()));
  }

  private TreeNode _getRootNode()
  {
    if(map != null)
      return map.getNode();
    else
      return new DefaultMutableTreeNode();
  }
}
