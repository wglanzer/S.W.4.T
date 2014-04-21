package de.swat.android.dialog;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.android.dialog.dialogs.AErrorDialog;

/**
 * Kann Dialoge anzeigen. Es muss allerdings vor dem ersten Benutzen
 * das init() ausgeführt werden, da sonst der Context null ist
 *
 * @author W. Glanzer, 18.04.2014
 */
public class ADialogDisplayer
{

  private static Stage stage;

  public ADialogDisplayer(Stage pStage)
  {
    stage = pStage;
  }

  /**
   * Setzt die Stage auf der die Dialoge angezeigt werden
   *
   * @param pStage Stage auf der die Dialoge angezeigt werden
   */
  public static void setStage(Stage pStage)
  {
    stage = pStage;
  }

  /**
   * Erzeugt einen neuen Error-Dialog mit dem übergebenen Throwable
   *
   * @param pThrowable Error der ausgegeben werden soll
   */
  public static void errorDialog(Throwable pThrowable)
  {
    AErrorDialog aErrorDialog = new AErrorDialog(pThrowable);
    aErrorDialog.show(stage);
  }
}
