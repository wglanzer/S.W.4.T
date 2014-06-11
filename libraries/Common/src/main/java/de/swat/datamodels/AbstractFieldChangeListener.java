package de.swat.datamodels;

import de.swat.exceptions.SwatRuntimeException;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Implementierung für den FieldChangeListener
 *
 * @author W. Glanzer, 24.02.14
 */
public abstract class AbstractFieldChangeListener
{

  private final ArrayList<IFieldChangeListener> listenerList = new ArrayList<>();

  /**
   * Feuert ein neu erstelltes FieldChangeObject an alle Listener
   *
   * @param pSource      Source, wo das Event herkommnt
   * @param pFieldName   Feld, das sich geändert hat
   * @param pSourceClass Klasse, in der das Feld steckt
   * @param pNewValue    Neuer Wert des Feldes
   */
  public void fireChange(Object pSource, String pFieldName, Class pSourceClass, Object pNewValue)
  {
    fireChange(new FieldChangeObject(pSource, getFieldByName(pFieldName, pSourceClass), pNewValue));
  }

  /**
   * Feuert das FieldChangeObject an alle listener weiter
   *
   * @param pObject Object, das herumgefeuert wird
   */
  public void fireChange(FieldChangeObject pObject)
  {
    synchronized (listenerList)
    {
      for (IFieldChangeListener currListener : listenerList)
      {
        currListener.fieldChanged(pObject);
      }
    }
  }

  /**
   * Fügt den angegebenen Listener in die listenerList ein
   *
   * @param pListener Listener, der eingefügt werden soll
   */
  public void addFieldChangeListener(IFieldChangeListener pListener)
  {
    synchronized (listenerList)
    {
      listenerList.add(pListener);
    }
  }

  /**
   * Entfernt den angegenbenen Listener aus der listenerList
   *
   * @param pListener Listener, der entfernt werden soll
   */
  public void removeFieldChangeListener(IFieldChangeListener pListener)
  {
    synchronized (listenerList)
    {
      listenerList.remove(pListener);
    }
  }

  /**
   * Liefert ein Feld anhand eines Namens
   *
   * @param pName        Namen des Feldes
   * @param pSourceClass SourceKlasse, in der das Objekt vorkommt
   * @return Feld
   */
  public Field getFieldByName(String pName, Class pSourceClass)
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
