package de.swat.observableList2;

import org.jetbrains.annotations.NotNull;

import javax.swing.event.*;
import java.io.*;
import java.util.*;

/**
 * @param <T>
 * @author Hamish Morgan
 */
public class ObservableList2<T> implements List<T>, Iterable<T>, Collection<T>, Cloneable, Serializable
{

  private transient EventListenerList listenerList;
  private List<T> inner;

  public ObservableList2(List<T> inner)
  {
    listenerList = new EventListenerList();
    this.inner = inner;
  }

  public ObservableList2()
  {
    this(new ArrayList<T>());
  }

  protected ObservableList2(ObservableList2<T> other)
  {
    this(other.inner);
  }

  private void writeObject(ObjectOutputStream out) throws IOException
  {
    out.writeObject(inner);
  }

  @SuppressWarnings("unchecked")
  private void readObject(ObjectInputStream in)
      throws IOException, ClassNotFoundException
  {
    inner = (List<T>) in.readObject();
    listenerList = new EventListenerList();
  }

  public List<T> getInner()
  {
    return inner;
  }

  public int size()
  {
    return inner.size();
  }

  public boolean isEmpty()
  {
    return inner.isEmpty();
  }

  public boolean contains(Object o)
  {
    return inner.contains(o);
  }

  @NotNull
  public Iterator<T> iterator()
  {
    return inner.iterator();
  }

  @NotNull
  public Object[] toArray()
  {
    return inner.toArray();
  }

  @NotNull
  public <T> T[] toArray(@NotNull T[] a)
  {
    return inner.toArray(a);
  }

  public boolean add(T e)
  {
    if (inner.add(e))
    {
      final int index = inner.size() - 1;
      fireIntervalAdded(index, index);
      return true;
    }
    else return false;
  }

  public boolean remove(Object o)
  {
    final int i = inner.indexOf(o);
    if (i != -1)
    {
      T item = inner.remove(i);
      fireIntervalRemoved(i, i);
      return true;
    }
    else return false;
  }

  public boolean containsAll(@NotNull Collection<?> c)
  {
    return inner.containsAll(c);
  }

  public boolean addAll(@NotNull Collection<? extends T> c)
  {
    final int index0 = inner.size() - 1;
    if (inner.addAll(c))
    {
      final int index1 = inner.size() - 1;
      fireIntervalAdded(index0, index1);
      return true;
    }
    else return false;
  }

  public boolean addAll(int index, @NotNull Collection<? extends T> c)
  {
    if (inner.addAll(index, c))
    {
      fireIntervalAdded(index, index + c.size());
      return true;
    }
    else return false;
  }

  public boolean removeAll(@NotNull Collection<?> c)
  {
    final int oldSize = inner.size();
    boolean result = inner.removeAll(c);
    if (result)
      fireIntervalChanged(0, oldSize);
    return result;
  }

  public boolean retainAll(@NotNull Collection<?> c)
  {
    final int oldSize = inner.size();
    boolean result = inner.retainAll(c);
    if (result)
      fireIntervalChanged(0, oldSize);
    return result;
  }

  public void clear()
  {
    if (!inner.isEmpty())
    {
      final int oldSize = inner.size();
      inner.clear();
      fireIntervalRemoved(0, oldSize);
    }
  }

  public T get(int index)
  {
    return inner.get(index);
  }

  public T set(int index, T element) throws UnsupportedOperationException
  {
    final T result = inner.set(index, element);
    fireIntervalChanged(index, index);
    return result;
  }

  public void add(int index, T element)
  {
    inner.add(index, element);
    fireIntervalAdded(index, index);
  }

  public T remove(int index)
  {
    final T result = inner.remove(index);
    fireIntervalRemoved(index, index);
    return result;
  }

  public int indexOf(Object o)
  {
    return inner.indexOf(o);
  }

  public int lastIndexOf(Object o)
  {
    return inner.lastIndexOf(o);
  }

  @NotNull
  public ListIterator<T> listIterator()
  {
    return inner.listIterator();
  }

  @NotNull
  public ListIterator<T> listIterator(int index)
  {
    return inner.listIterator(index);
  }

  @NotNull
  public List<T> subList(int fromIndex, int toIndex)
  {
    return inner.subList(fromIndex, toIndex);
  }

  @Override
  public String toString()
  {
    return inner.toString();
  }

  @Override
  public boolean equals(Object obj)
  {
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    @SuppressWarnings("unchecked")
    final ObservableList2<T> other = (ObservableList2<T>) obj;
    return !(this.inner != other.inner &&
        (this.inner == null || !this.inner.equals(other.inner)));
  }

  @Override
  public int hashCode()
  {
    int hash = 3;
    hash = 47 * hash + (this.inner != null ? this.inner.hashCode() : 0);
    return hash;
  }

  public void addListDataListener(ListDataListener listener)
  {
    listenerList.add(ListDataListener.class, listener);
  }

  public void removeListDataListener(ListDataListener listener)
  {
    listenerList.remove(ListDataListener.class, listener);
  }

  public ListDataListener[] getListDataListeners()
  {
    return listenerList.getListeners(ListDataListener.class);
  }

  public int getListDataListenerCount()
  {
    return listenerList.getListenerCount(ListDataListener.class);
  }

  protected void fireIntervalAdded(int index0, int index1)
  {
    fireListDataEvent(ListDataEvent.INTERVAL_ADDED, index0, index1);
  }

  protected void fireIntervalRemoved(int index0, int index1)
  {
    fireListDataEvent(ListDataEvent.INTERVAL_REMOVED, index0, index1);
  }

  protected void fireIntervalChanged(int index0, int index1)
  {
    fireListDataEvent(ListDataEvent.CONTENTS_CHANGED, index0, index1);
  }

  private void fireListDataEvent(int type, int index0, int index1)
  {
    ListDataEvent event = null;
    final Object[] listeners = listenerList.getListenerList();
    for (int i = listeners.length - 2; i >= 0; i -= 2)
    {
      if (listeners[i] == ListDataListener.class)
      {
        if (event == null)
          event = new ListDataEvent(this, type, index0, index1);
        ((ListDataListener) listeners[i + 1]).intervalAdded(event);
      }
    }
  }
}
