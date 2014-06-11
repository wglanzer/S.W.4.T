package de.swat.datamodels;

import de.swat.annotationProcessors.annotations.FormProperty;
import org.jetbrains.annotations.Nullable;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import java.lang.reflect.Field;
import java.util.Collection;

/**
 * Model für die PropertySheetComponent
 *
 * @author W. Glanzer, 08.02.14
 */
public class PropertySheetTableModel implements TableModel
{

  private String[][] keyValueArray;

  public PropertySheetTableModel(@Nullable Collection<Field> pFields)
  {
    if (pFields == null)
      return;

    keyValueArray = new String[pFields.size()][2];
    Object[] fieldArray = pFields.toArray();

    //Hier wird jedes Feld durchgegangen und dessen key und value herausgeschrieben in das Array
    //key = name, der in der Annotation angegeben ist
    //value = value des Feldes
    for (int i = 0; i < pFields.size(); i++)
    {
      Field currField = (Field) fieldArray[i];
      FormProperty formProperty = currField.getAnnotation(FormProperty.class);

      String value = formProperty.defaultValue();
      String key = currField.getName();

      keyValueArray[i][0] = key;
      keyValueArray[i][1] = value;
    }

  }

  @Override
  public int getRowCount()
  {
    return keyValueArray != null ? keyValueArray.length : 0;
  }

  @Override
  public int getColumnCount()
  {
    return 2;
  }

  @Override
  public String getColumnName(int columnIndex)
  {
    return columnIndex == 0 ? "key" : "value";
  }

  @Override
  public Class<?> getColumnClass(int columnIndex)
  {
    return String.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex)
  {
    return columnIndex == 1;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex)
  {
    return keyValueArray[rowIndex][columnIndex];
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex)
  {
    keyValueArray[rowIndex][columnIndex] = aValue.toString();
  }

  @Override

  public void addTableModelListener(TableModelListener l)
  {
  }

  @Override
  public void removeTableModelListener(TableModelListener l)
  {
  }

}
