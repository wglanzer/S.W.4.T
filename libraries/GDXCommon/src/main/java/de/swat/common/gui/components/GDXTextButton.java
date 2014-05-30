package de.swat.common.gui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import de.swat.Insets;

/**
 * LibGDX-Impl: Button, der mit Text und einem Shader versehen werden kann
 *
 * @author W.Glanzer, 30.05.2014.
 */
public class GDXTextButton extends Button
{
  private final Label label;
  private TextButton.TextButtonStyle style;
  private ShaderProgram shader;
  private BitmapFont font;
  private Insets insets = new Insets(1, 1, 1, 1);

  public GDXTextButton(String text, Skin pSkin, BitmapFont pFont)
  {
    font = pFont;
    setSkin(pSkin);
    style = pSkin.get(TextButton.TextButtonStyle.class);
    label = new Label(text, new Label.LabelStyle(style.font, style.fontColor));
    setStyle(style);
    label.setAlignment(Align.center);
    setWidth(getPrefWidth());
    setHeight(getPrefHeight());
    add(label).expand().fill();
  }

  @Override
  protected void sizeChanged()
  {
    super.sizeChanged();
    label.setFontScale((getWidth() - insets.left - insets.right) / font.getBounds(label.getText()).width);
  }

  public void draw(SpriteBatch pBatch, float pParentAlpha)
  {
    Color fontColor;
    if(isDisabled() && style.disabledFontColor != null)
      fontColor = style.disabledFontColor;
    else if(isPressed() && style.downFontColor != null)
      fontColor = style.downFontColor;
    else if(isChecked() && style.checkedFontColor != null)
      fontColor = (isOver() && style.checkedOverFontColor != null) ? style.checkedOverFontColor : style.checkedFontColor;
    else if(isOver() && style.overFontColor != null)
      fontColor = style.overFontColor;
    else
      fontColor = style.fontColor;
    if(fontColor != null) label.getStyle().fontColor = fontColor;

    getChildren().clear();
    super.draw(pBatch, pParentAlpha);

    if(shader != null)
      pBatch.setShader(shader);

    float x = getX();
    float y = getY() + 4 * getScaleY();
    label.translate(x, y);
    label.draw(pBatch, pParentAlpha);
    label.translate(-x, -y);
    if(shader != null)
      pBatch.setShader(null);
  }

  public void setTextShader(ShaderProgram pShader)
  {
    shader = pShader;
  }


  // SETTER

  public void setBorder(int pTop, int pLeft, int pBottom, int pRight)
  {
    insets = new Insets(pTop, pLeft, pBottom, pRight);
  }

  public TextButton.TextButtonStyle getStyle()
  {
    return style;
  }

  public void setStyle(ButtonStyle pStyle)
  {
    if(!(pStyle instanceof TextButton.TextButtonStyle))
      throw new IllegalArgumentException("Style must be a TextButton.TextButtonStyle.");
    super.setStyle(pStyle);
    this.style = (TextButton.TextButtonStyle) pStyle;
    TextButton.TextButtonStyle textButtonStyle = (TextButton.TextButtonStyle) pStyle;
    Label.LabelStyle labelStyle = label.getStyle();
    labelStyle.font = textButtonStyle.font;
    labelStyle.fontColor = textButtonStyle.fontColor;
    label.setStyle(labelStyle);
  }

  public Insets getInsets()
  {
    return insets;
  }

  // GETTER

  public void setInsets(Insets pInsets)
  {
    insets = pInsets;
  }

  public Label getLabel()
  {
    return label;
  }

  public CharSequence getText()
  {
    return label.getText();
  }

  public void setText(String pText)
  {
    label.setText(pText);
  }
}

