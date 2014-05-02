package de.swat.android.desktop;

import com.badlogic.gdx.Screen;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Dieser Screen leitet an einen anderen weiter.
 * Hier werden nur die Exceptions des anderen abgefangen, sodass kein
 * critical-error mehr entstehen kann.
 *
 * @author W. Glanzer, 06.04.2014
 */
public class AExceptionRedirectScreen implements Screen
{
  private Screen redirectTo;
  private Logger logger = LogManager.getLogger();

  public AExceptionRedirectScreen()
  {
    try
    {
      redirectTo = new AScreen();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    try
    {
      redirectTo.resize(pWidth, pHeight);
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void pause()
  {
    try
    {
      redirectTo.pause();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void resume()
  {
    try
    {
      redirectTo.resume();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void dispose()
  {
    try
    {
      redirectTo.dispose();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void render(float pDelta)
  {
    try
    {
      redirectTo.render(pDelta);
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void show()
  {
    try
    {
      redirectTo.show();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

  @Override
  public void hide()
  {
    try
    {
      redirectTo.hide();
    }
    catch(Throwable e)
    {
      logger.catching(e);
    }
  }

}
