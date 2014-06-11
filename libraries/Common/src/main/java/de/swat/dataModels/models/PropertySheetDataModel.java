package de.swat.datamodels.models;

import de.swat.annotationProcessors.annotations.DataModel;

/**
 * @author W. Glanzer, 02.02.14
 */
@DataModel
public class PropertySheetDataModel extends SimpleDataModel
{

  /**
   * Spalten, die ein PropertySheet enthalten soll.
   * Es geht heirbei allerdings nur im die Anzahl, da
   * die Spalten nicht angezeigt werden.
   */
  private String[] columns = new String[]{"key", "value"};

  public String[] getColumns()
  {
    return columns;
  }

  public void setColumns(String[] pColumns)
  {
    columns = pColumns;
  }
}
