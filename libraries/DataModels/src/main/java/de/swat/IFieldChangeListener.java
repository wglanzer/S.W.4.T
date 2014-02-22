package de.swat;

import de.swat.datamodels.IDataModel;

import java.lang.reflect.Field;
import java.util.EventObject;

/**
 * Dieser Listener wird bei den DatenModellen über
 * die ModelAccesses  eingehängt. Er dient dazu, mitzubekommen,
 * ob sich ein Feld im DatenModell geändert hat
 *
 * @author W. Glanzer, 22.02.14
 */
public interface IFieldChangeListener
{

  public void fieldChanged(FieldObject pFieldObject);


  class FieldObject extends EventObject
  {
    private final Field sourceField;
    private final Object newValue;

    public FieldObject(IModelAccess pModelAccess, Field pSourceField, Object pNewValue)
    {
      super(pModelAccess);
      sourceField = pSourceField;
      newValue = pNewValue;
    }

    public Object getNewValue()
    {
      return newValue;
    }

    public Field getSourceField()
    {
      return sourceField;
    }
  }
}
