package de.swat;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Beschreibt eine Action, kann allgemein
 * verwendet werden
 *
 * @author W. Glanzer, 18.02.2014
 */
public interface IAction
{

  void actionPerformed(ActionEvent pSourceEvent, JComponent pInvoker, IModelAccess pModelAccess);

}