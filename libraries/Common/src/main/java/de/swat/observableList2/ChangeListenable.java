package de.swat.observableList2;

import javax.swing.event.ChangeListener;

/**
 * @author Hamish Morgan
 */
public interface ChangeListenable {

  void addChangeListener(ChangeListener listener);

  void removeChangeListener(ChangeListener listener);

  ChangeListener[] getChangeListeners();

  int getChangeListenerCount();
}
