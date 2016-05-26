package de.swat.common.map;

import javax.swing.*;

/**
 * @author W.Glanzer, 08.07.2014.
 */
public class MapTreeRootNode extends MapTreeNode
{

  private final JTree graphicTree;

  public MapTreeRootNode(Object userObject, JTree pGraphicTree)
  {
    super(userObject);
    graphicTree = pGraphicTree;
  }

  public JTree getGraphicTree()
  {
    return graphicTree;
  }
}
