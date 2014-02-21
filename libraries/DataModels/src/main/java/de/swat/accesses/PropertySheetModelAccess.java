package de.swat.accesses;

import de.swat.datamodels.PropertySheetDataModel;
import de.swat.IModelAccess;
import java.io.*;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class PropertySheetModelAccess implements IModelAccess, Serializable
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
	}

}
