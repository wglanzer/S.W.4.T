package de.swat;

import java.util.ArrayList;

/**
 * @author W.Glanzer, 11.05.2014.
 */
public class ValueObject<T>
{

  private final Object synchronizeObject = new Object();
  private ArrayList<T> value = new ArrayList<>();

  public T getValue()
  {
    synchronized(synchronizeObject)
    {
      if(hasValue())
      {
        T val = value.get(0);
        value.remove(0);
        return val;
      }
      else
        return null;
    }
  }

  public boolean hasValue()
  {
    return value.size() > 0;
  }

  public void addValue(T pObject)
  {
    synchronized(synchronizeObject)
    {
      value.add(pObject);
    }
  }
}
