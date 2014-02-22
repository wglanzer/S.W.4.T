package de.swat.accesses;

import de.swat.datamodels.PropertySheetDataModel;
import de.swat.AbstractModelAccess;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class PropertySheetModelAccess extends AbstractModelAccess
{

	private static final long serialVersionUID = 1L;
	private static final PropertySheetDataModel INSTANCE = new PropertySheetDataModel();

	public java.lang.String[] getColumns()
	{
		return INSTANCE.getColumns();
	}

	public void setColumns(java.lang.String[] pParam)
	{
		INSTANCE.setColumns(pParam);
		_fireFieldChanged(this, getFieldByName("columns", PropertySheetDataModel.class), pParam);
	}

}
