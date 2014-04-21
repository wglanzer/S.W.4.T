package de.swat.android.dialog.dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import de.swat.android.constants.AIStaticConstants;

import java.util.HashMap;

/**
 * Dialog, der schon von Haus aus einen Skin besitzt
 *
 * @author W. Glanzer, 19.04.2014
 */
public class AAbstractDialog extends Dialog
{
  private HashMap<Object, IActionListener> actionListenerMap = new HashMap<>();

  public AAbstractDialog()
  {
    super("<DUMMY>", AIStaticConstants.SKIN_DEFAULT);
  }

  public void setText(String pText)
  {
    text(pText);
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
