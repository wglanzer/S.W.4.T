package de.swat.android;

import com.badlogic.gdx.Screen;
import de.swat.android.logging.ALogger;
import de.swat.logging.LoggerRegisterer;

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

  public AExceptionRedirectScreen()
  {
    try
    {
      LoggerRegisterer.register(new ALogger());

      redirectTo = new AScreen();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    try
    {
      redirectTo.resize(pWidth, pHeight);
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void pause()
  {
    try
    {
      redirectTo.pause();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void resume()
  {
    try
    {
      redirectTo.resume();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void dispose()
  {
    try
    {
      redirectTo.dispose();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void render(float pDelta)
  {
    try
    {
      redirectTo.render(pDelta);
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void show()
  {
    try
    {
      redirectTo.show();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  @Override
  public void hide()
  {
    try
    {
      redirectTo.hide();
    } catch(Throwable e)
    {
      _handleThrowable(e);
    }
  }

  /**
   * Handlet das Throwable-Objekt
   *
   * @param pThrowable Throwable-Objekt
   */
  private void _handleThrowable(Throwable pThrowable)
  {
    try
    {
      LoggerRegisterer.throwable(pThrowable);
    } catch(Throwable e)
    {
      pThrowable.printStackTrace();
    }
  }
}
