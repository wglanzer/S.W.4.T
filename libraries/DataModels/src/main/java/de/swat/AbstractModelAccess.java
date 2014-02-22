package de.swat;

import de.swat.exceptions.SwatRuntimeException;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.*;

/**
 * Abstrakte Klasse für alle ModelAccesses
 *
 * @author W. Glanzer, 22.02.14
 */
public abstract class AbstractModelAccess implements IModelAccess, Serializable
{
  private final Set<IFieldChangeListener> listeners = new HashSet<>();

  /**
   * Feuert das fieldChanged-Event
   */
  protected void _fireFieldChanged(IModelAccess pSourceModelAccess, Field pSourceField, Object pNewValue)
  {
    synchronized (listeners)
    {
      IFieldChangeListener.FieldObject fieldObject = new IFieldChangeListener.FieldObject(pSourceModelAccess, pSourceField, pNewValue);
      for (IFieldChangeListener currListener : listeners)
        currListener.fieldChanged(fieldObject);
    }
  }

  /**
   * Fügt den übergebenen FieldChangeListener hinzu
   *
   * @param pListener FieldChangeListener
   */
  public void addFieldChangeListener(IFieldChangeListener pListener)
  {
    synchronized (listeners)
    {
      listeners.add(pListener);
    }
  }

  /**
   * Entfernt den übergebenen FieldChangeListener
   *
   * @param pListener FieldChangeListener
   */
  public void removeFieldChangeListener(IFieldChangeListener pListener)
  {
    synchronized (listeners)
    {
      listeners.remove(pListener);
    }
  }

  protected Field getFieldByName(String pName, Class pSourceClass)
  {
    try
    {
      return pSourceClass.getDeclaredField(pName);
    }
    catch (NoSuchFieldException e)
    {
      throw new SwatRuntimeException("Internal error! Please report this bug!", e);
    }
  }
}
