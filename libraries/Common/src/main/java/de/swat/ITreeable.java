package de.swat;

import javax.swing.tree.MutableTreeNode;

/**
 * Gibt an, dass eine Komponente in eine TreeNode
 * umgewandelt werden kann
 *
 * @author W.Glanzer, 23.06.2014.
 */
public interface ITreeable
{

  MutableTreeNode getNode();

}
