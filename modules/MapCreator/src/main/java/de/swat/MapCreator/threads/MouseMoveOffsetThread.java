package de.swat.MapCreator.threads;

import de.swat.MapCreator.gui.DrawContainer.DrawContainer;
import de.swat.MapCreator.gui.Window;
import de.swat.constants.IWindowConstants;

import java.awt.*;

/**
 * MouseMoveOffsetThread, der es möglich macht,
 * mit der Maus an den Rand zu gehen und damit
 * das X und Y-Offset zu verändern.
 *
 * @author W. Glanzer, 09.12.13
 */
public class MouseMoveOffsetThread
{
  private final DrawContainer workingContainer;
  private final int sleeptime;
  private final Window window;
  public Thread thread;

  public MouseMoveOffsetThread(Window pWindow, int pSleeptime)
  {
    window = pWindow;
    workingContainer = pWindow.getDrawContainer();
    sleeptime = pSleeptime;
    Runnable runnable = _configureRunnable();
    thread = new Thread(runnable);
    thread.setName("MouseMoveOffsetThread");
    thread.start();
  }

  private Runnable _configureRunnable()
  {
    return new Runnable()
    {
      @Override
      public void run()
      {
        int radius = IWindowConstants.DRAWCONTAINER_OFFSET_SCROLLRADIUS;
        int scrollSpeed = IWindowConstants.DRAWCONTAINER_OFFSET_MAXSCROLLSPEED;

        while (!thread.isInterrupted())
        {
          int width = workingContainer.getWidth();
          int height = workingContainer.getHeight();

          Rectangle left = new Rectangle(0, 0, radius, height);
          Rectangle right = new Rectangle(width - radius, 0, radius, height);
          Rectangle top = new Rectangle(0, 0, width, radius);
          Rectangle bottom = new Rectangle(0, height - radius, width, height);

          Point currMousePoint = workingContainer.getMousePosition();
          if (workingContainer.isInitialised() && currMousePoint != null && !workingContainer.isBlocked())
          {
            if (left.contains(currMousePoint))
            {
              workingContainer.setXOff(-scrollSpeed, true);
              //window.updateSpinner();
            }

            if (right.contains(currMousePoint))
            {
              workingContainer.setXOff(scrollSpeed, true);
              //window.updateSpinner();
            }

            if (top.contains(currMousePoint))
            {
              workingContainer.setYOff(-scrollSpeed, true);
              //window.updateSpinner();
            }

            if (bottom.contains(currMousePoint))
            {
              workingContainer.setYOff(scrollSpeed, true);
              //window.updateSpinner();
            }
          }

          try
          {
            Thread.sleep(sleeptime);
          }
          catch (InterruptedException e)
          {
            break;
          }
        }

        System.err.println("Shutting down " + thread.getName() + " (" + thread.getId() + ") ...");
        shutdown();
      }

      private void shutdown()
      {
      }
    };
  }

}
