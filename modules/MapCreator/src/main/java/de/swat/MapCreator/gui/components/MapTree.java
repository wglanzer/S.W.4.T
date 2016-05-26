package de.swat.mapCreator.gui.components;

import de.swat.common.map.Map;
import de.swat.common.map.MapTreeNode;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;

/**
 * Tree, der eine Map repräsentieren kann
 *
 * @author W.Glanzer, 23.06.2014.
 */
public class MapTree extends JTree
{
  // Map, für die der Tree angezeigt werden soll
  private Map map;

  public MapTree(Map pMap)
  {
    map = pMap;
    DefaultTreeModel model = new DefaultTreeModel(_getRootNode())
    {
      @Override
      public void reload()
      {
        setRoot(_getRootNode());
        super.reload();
      }
    };
    setModel(model);
    addMouseListener(new PopupMenuListener());
  }

  /**
   * Wird aufgerufen, wenn sich die Map geändert hat
   *
   * @param pNewMap  Neue Map
   */
  public void mapChanged(Map pNewMap)
  {
    map = pNewMap;
    DefaultTreeModel model = new DefaultTreeModel(_getRootNode())
    {
      @Override
      public void reload()
      {
        setRoot(_getRootNode());
        Enumeration<TreePath> paths = getExpandedDescendants(getPathForRow(0));
        super.reload();
        for(TreePath path = paths.nextElement(); paths.hasMoreElements(); )
          expandPath(path);
      }
    };
    setModel(model);
  }

  /**
   * @return Liefert die RootNode des Baumes.
   */
  private TreeNode _getRootNode()
  {
    if(map != null)
      return map.getGraphicRootNode(this);
    else
      return new MapTreeNode();
  }

  /**
   * Implementierung eines Mausadapters, damit
   * für jede Zeile ein unterschiedliches Popup angezeigt werden kann
   */
  private class PopupMenuListener extends MouseAdapter
  {
    @Override
    public void mouseClicked(MouseEvent e)
    {
      if(SwingUtilities.isRightMouseButton(e))
      {
        int row = getRowForPath(getPathForLocation(e.getX(), e.getY()));
        setSelectionRow(row);

        DefaultMutableTreeNode node = (DefaultMutableTreeNode) getLastSelectedPathComponent();

        if (node != null && node instanceof MapTreeNode)
        {
          JPopupMenu menu = ((MapTreeNode) node).getMenu();
          if(menu != null)
            menu.show(MapTree.this, e.getX(), e.getY());
        }
      }
    }
  }
}
