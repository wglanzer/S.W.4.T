package de.swat.android.dialog.dialogs;

import de.swat.android.constants.AIStaticConstants;
import de.swat.utils.StringUtil;

/**
 * Zeigt einen Error an
 *
 * @author W. Glanzer, 19.04.2014
 */
public class AErrorDialog extends AAbstractDialog
{

  public AErrorDialog(Throwable pThrowable)
  {
    String title = StringUtil.replacePlaceholder(AIStaticConstants.DIALOG_ERROR_TITLE, pThrowable.getClass().getName());
    String message = AIStaticConstants.DIALOG_ERROR_MESSAGE + StringUtil.getLineSeperator();
    message += StringUtil.getLineSeperator() + StringUtil.convertStacktraceToString(pThrowable.getStackTrace(), 5);

    setTitle(title);
    setText(message);
    addButton(AIStaticConstants.DIALOG_BTN_OK, new IActionListener()
    {
      @Override
      public void actionPerformed()
      {
      }
    });
  }


}
