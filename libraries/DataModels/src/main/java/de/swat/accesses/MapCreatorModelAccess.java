package de.swat.accesses;

import de.swat.datamodels.MapCreatorDataModel;
import de.swat.IModelAccess;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class MapCreatorModelAccess implements IModelAccess
{

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
