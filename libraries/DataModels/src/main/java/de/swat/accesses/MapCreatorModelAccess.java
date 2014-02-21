package de.swat.accesses;

import de.swat.datamodels.MapCreatorDataModel;
import de.swat.IModelAccess;
import java.io.*;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class MapCreatorModelAccess implements IModelAccess, Serializable
{

	private static final long serialVersionUID = 1L;
	private static final MapCreatorDataModel INSTANCE = new MapCreatorDataModel();

	public de.swat.Map getMap()
	{
		return INSTANCE.getMap();
	}

	public void setMap(de.swat.Map pParam)
	{
		INSTANCE.setMap(pParam);
	}

}
