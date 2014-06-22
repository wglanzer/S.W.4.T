package de.swat.mapCreator.gui.components.propertysheet;

import org.jdesktop.swingx.JXTable;

/**
 * @author W. Glanzer, 14.02.14
 */
public class PropertySheet
{
  private _PropertySheetComponent component;

  public PropertySheet()
  {
    component = new _PropertySheetComponent();
  }

  public _PropertySheetComponent getComponent()
  {
    return component;
  }

  /**
   * Dieser Swing-Container stellt ein PropertySheetComponent dar,
   * wie er im MapCreator links unten verwendet wird.
   *
   * @author W. Glanzer, 02.02.14
   */
  private static class _PropertySheetComponent extends JXTable
  {

    private _PropertySheetComponent()
    {
//      this(new PropertySheetTableModel(LookupUtil.getFieldByAnnotation(DataModel.class, FormProperty.class, "de.swat.datamodels")));
    }

    private _PropertySheetComponent(PropertySheetTableModel pModel)
    {
      setModel(pModel);
    }

  }
}
