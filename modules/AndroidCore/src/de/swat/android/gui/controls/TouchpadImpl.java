package de.swat.android.gui.controls;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

/**
 * Dieses TouchpadImpl stellt ein gewöhnliches TouchpadImpl dar,
 * nur gekapselt und schon fertig vorbereitet
 *
 * @author W.Glanzer, 24.04.2014.
 */
public class TouchpadImpl
{

  private Touchpad.TouchpadStyle touchpadStyle;
  private Skin touchpadSkin;

  private Drawable touchBackground;
  private Drawable touchKnob;

  private Touchpad touchpad;

  public TouchpadImpl()
  {
    touchpadSkin = new Skin();
    touchpadSkin.add("touchBackground", new Texture("data/icon.png"));
    touchpadSkin.add("touchKnob", new Texture("data/icon.png"));

    touchpadStyle = new Touchpad.TouchpadStyle();
    touchBackground = touchpadSkin.getDrawable("touchBackground");
    touchKnob = touchpadSkin.getDrawable("touchKnob");

    touchpadStyle.background = touchBackground;
    touchpadStyle.knob = touchKnob;

    touchpad = new Touchpad(10, touchpadStyle);
    touchpad.setBounds(15, 15, 200, 200);
  }

  public Touchpad getTouchpad()
  {
    return touchpad;
  }
}
