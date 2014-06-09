package de.swat.entity.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import de.swat.common.gui.renderer.ShapeRenderUtil;
import de.swat.entity.IControllable;
import de.swat.entity.IMovableEntity;

/**
 * Spieler
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class Player extends BaseEntity implements IMovableEntity, IControllable
{

  private float delta = 0;

  public Player()
  {
    setSize(50, 50);
  }

  @Override
  public void draw(SpriteBatch batch, float parentAlpha)
  {
    super.draw(batch, parentAlpha);
    delta += Gdx.graphics.getDeltaTime() * 100f;

    int width = Gdx.graphics.getWidth();
    int height = Gdx.graphics.getHeight();
    ShapeRenderUtil.drawRect(width / 2 - 20, height / 2 - 20, 40, 40, delta, Color.BLUE);
  }
}
