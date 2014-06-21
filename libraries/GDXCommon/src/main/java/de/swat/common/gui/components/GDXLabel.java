package de.swat.common.gui.components;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

/**
 * Label, das allerdings zum Rendern des Fonts den FontShader benutzt
 *
 * @author W.Glanzer, 03.06.2014.
 */
public class GDXLabel extends Label
{
  private ShaderProgram shader;

  public GDXLabel(CharSequence text, Skin skin)
  {
    super(text, skin);
  }

  @Override
  public void draw(Batch batch, float parentAlpha)
  {
    if(shader != null)
      batch.setShader(shader);

    super.draw(batch, parentAlpha);

    if(shader != null)
      batch.setShader(null);
  }

  public void setFontShader(ShaderProgram pShader)
  {
    shader = pShader;
  }
}
