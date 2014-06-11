package de.swat.datamodels;

import java.lang.reflect.Field;
import java.util.EventObject;

/**
 * FieldChangeObject, das beim IFieldChangeListener herumgereicht wird
 *
 * @author W. Glanzer, 24.02.14
 */
public class FieldChangeObject extends EventObject
{
  private final Field sourceField;
  private final Object newValue;

  public FieldChangeObject(Object pSourceObject, Field pSourceField, Object pNewValue)
  {
    super(pSourceObject);
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
