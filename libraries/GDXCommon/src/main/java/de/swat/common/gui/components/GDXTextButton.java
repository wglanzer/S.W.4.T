package de.swat.common.gui.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
  private Insets insets = new Insets(10, 10, 10, 10);
  private boolean automaticLayout = false;

  public GDXTextButton(String text, Skin pSkin, BitmapFont pFont)
  {
    this(text, pSkin, pFont, false, 1.0f);
  }

  public GDXTextButton(String text, Skin pSkin, BitmapFont pFont, boolean pAutomaticLayout, float pFontScale)
  {
    font = pFont;
    automaticLayout = pAutomaticLayout;
    setSkin(pSkin);
    style = pSkin.get(TextButton.TextButtonStyle.class);
    label = new Label(text, new Label.LabelStyle(style.font, style.fontColor));
    setStyle(style);
    label.setAlignment(Align.center);
    label.setFontScale(pFontScale);
    setWidth(getPrefWidth() + insets.left + insets.right);
    setHeight(getPrefHeight() + insets.top + insets.bottom);
    add(label).expand().fill();
  }

  private void _doAutomaticLayout(float pFontScale)
  {
    BitmapFont.TextBounds bounds = font.getBounds(label.getText());
    float width = pFontScale * bounds.width + insets.left + insets.right;
    float height = pFontScale * bounds.height + insets.bottom + insets.top;

    setSize(width, height);
  }

  @Override
  protected void sizeChanged()
  {
    super.sizeChanged();

    if(automaticLayout)
    {
      _doAutomaticLayout(Math.max(label.getFontScaleX(), label.getFontScaleY()));
    }
    else
    {
      BitmapFont.TextBounds bounds = font.getBounds(label.getText());
      float widthScale = (getWidth() - insets.left - insets.right) / bounds.width;
      float heightScale = (getHeight() - insets.top - insets.bottom) / bounds.height;
      label.setFontScale(Math.min(widthScale, heightScale));
    }
  }

  public void setTextShader(ShaderProgram pShader)
  {
    shader = pShader;
  }  public void draw(Batch pBatch, float pParentAlpha)
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
    label.setPosition(label.getX() + x, label.getY() + y);
    label.draw(pBatch, pParentAlpha);
    label.setPosition(label.getX() - x, label.getY() - y);
    if(shader != null)
      pBatch.setShader(null);
  }

  public void setBorder(int pTop, int pLeft, int pBottom, int pRight)
  {
    insets = new Insets(pTop, pLeft, pBottom, pRight);
  }


  // SETTER

  public Insets getInsets()
  {
    return insets;
  }

  public void setInsets(Insets pInsets)
  {
    insets = pInsets;
  }  public TextButton.TextButtonStyle getStyle()
  {
    return style;
  }

  public Label getLabel()
  {
    return label;
  }  public void setStyle(ButtonStyle pStyle)
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

  public CharSequence getText()
  {
    return label.getText();
  }

  // GETTER

  public void setText(String pText)
  {
    label.setText(pText);
  }






}

