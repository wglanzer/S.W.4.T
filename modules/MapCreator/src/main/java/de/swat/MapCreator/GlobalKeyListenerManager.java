package de.swat.mapCreator;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

/**
 * @author W. Glanzer, 29.03.2014
 */
public class GlobalKeyListenerManager
{
  private static final KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
  private static GlobalKeyListenerManager defaultInstance;

  public static void init()
  {
    if (defaultInstance == null)
      defaultInstance = new GlobalKeyListenerManager();
  }

  public static GlobalKeyListenerManager getDefault()
  {
    return defaultInstance;
  }

  public void addKeyEventDispatcher(@NotNull KeyEventDispatcher pDispatcher)
  {
    synchronized (keyboardFocusManager)
    {
      keyboardFocusManager.addKeyEventDispatcher(pDispatcher);
    }
  }

  public void removeKeyEventDispatcher(@NotNull KeyEventDispatcher pDispatcher)
  {
    synchronized (keyboardFocusManager)
    {
      keyboardFocusManager.removeKeyEventDispatcher(pDispatcher);
    }
  }
}
