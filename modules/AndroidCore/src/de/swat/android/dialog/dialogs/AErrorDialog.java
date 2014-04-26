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
  private boolean nextHideAllowed = true;
  private boolean detailsShown = false;

  public AErrorDialog(Throwable pThrowable)
  {
    String title = StringUtil.replacePlaceholder(AIStaticConstants.DIALOG_ERROR_TITLE, pThrowable.getClass().getName());
    final String message = AIStaticConstants.DIALOG_ERROR_MESSAGE + StringUtil.getLineSeperator();
    final String detailmessage = StringUtil.convertStacktraceToString(pThrowable, 5);

    setTitle(title);
    setText(message);
    addButton(AIStaticConstants.DIALOG_BTN_OK, new IActionListener()
    {
      @Override
      public void actionPerformed()
      {
        nextHideAllowed = true;
      }
    });
    addButton(AIStaticConstants.DIALOG_BTN_DETAILS, new IActionListener()
    {
      @Override
      public void actionPerformed()
      {
        if(!detailsShown)
        {
          setText(message + StringUtil.getLineSeperator() + detailmessage);
          pack();
          detailsShown = true;
        }
        nextHideAllowed = false;
      }
    });
  }

  @Override
  public void hide()
  {
    if(nextHideAllowed)
      super.hide();

    nextHideAllowed = true;
  }
}
