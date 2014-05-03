package de.swat.android.dialog.dialogs;

import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import de.swat.android.AAssets;

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
  private Skin skin = AAssets.get().getSkinDefault();

  public AAbstractDialog()
  {
    super("<DUMMY>", AAssets.get().getSkinDefault());
  }

  /**
   * Setzt den Text des Labels
   *
   * @param pText Text, den das Label danach haben soll
   */
  public void setText(String pText)
  {
    if(text != null)
      getContentTable().removeActor(text);
    text = new Label(pText, skin.get(Label.LabelStyle.class));
    text(text);
  }

  /**
   * Fügt einen Button hinzu
   *
   * @param pCaption  Text des neuen Buttons
   * @param pListener Listener, der bei Betätigung des Buttons anspringt
   */
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

  /**
   * ActionListener der anspringt, wenn man auf einen Button drückt
   */
  public interface IActionListener
  {
    /**
     * Es wurde auf einen Button geklickt
     */
    void actionPerformed();
  }
}
