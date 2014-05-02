package de.swat.android.desktop.dialog.dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.android.desktop.constants.AIStaticConstants;

import java.util.HashMap;

/**
 * Dialog, der schon von Haus aus einen Skin besitzt
 *
 * @author W. Glanzer, 19.04.2014
 */
public class AAbstractDialog extends Dialog
{
  private HashMap<Object, IActionListener> actionListenerMap = new HashMap<>();
  private Label text;
  private Skin skin = AIStaticConstants.SKIN_DEFAULT;

  public AAbstractDialog()
  {
    super("<DUMMY>", AIStaticConstants.SKIN_DEFAULT);
  }

  public void setText(String pText)
  {
    if(text != null)
      getContentTable().removeActor(text);
    text = new Label(pText, skin.get(Label.LabelStyle.class));
    text(text);
  }

  public void addButton(String pCaption, IActionListener pListener)
  {
    Object identifyingObject = new Object();
    actionListenerMap.put(identifyingObject, pListener);
    button(pCaption, identifyingObject);
  }

  @Override
  protected void result(Object object)
  {
    IActionListener actionListener = actionListenerMap.get(object);
    if(actionListener != null)
      actionListener.actionPerformed();
  }

  public interface IActionListener
  {
    void actionPerformed();
  }
}
