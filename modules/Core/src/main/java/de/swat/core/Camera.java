package de.swat.core;

import de.swat.core.Entity.Entity;

/**
 * @author Werner Glanzer, 07.10.13
 */
public class Camera
{

  private final int window_width = DefaultSettingsCallback.WINDOW_WIDTH;
  private final int window_height = DefaultSettingsCallback.WINDOW_HEIGHT;

  private Entity focusEntity;

  public static double xOff = 0;
  public static double yOff = 0;

  public Camera(Entity pFocus)
  {
    focusEntity = pFocus;
  }

  public void update(int pMouseX, int pMouseY)
  {
    xOff = focusEntity.posX + (pMouseX - window_width / 2) / 2;
    yOff = focusEntity.posY + (pMouseY - window_height / 2) / 2;
  }
}
