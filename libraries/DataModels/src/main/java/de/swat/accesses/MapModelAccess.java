package de.swat.accesses;

import de.swat.datamodels.MapDataModel;
import de.swat.IModelAccess;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class MapModelAccess implements IModelAccess
{

	private static final MapDataModel INSTANCE = new MapDataModel();

	public de.swat.datamodels.raster.Raster getRaster()
	{
		return INSTANCE.getRaster();
	}

	public void setRaster(de.swat.datamodels.raster.Raster pParam)
	{
		INSTANCE.setRaster(pParam);
	}

	public java.util.ArrayList<de.swat.dataModels.Map.AbstractCollisionObjectDataModel> getCollisionObjects()
	{
		return INSTANCE.getCollisionObjects();
	}

	public void setCollisionObjects(java.util.ArrayList<de.swat.dataModels.Map.AbstractCollisionObjectDataModel> pParam)
	{
		INSTANCE.setCollisionObjects(pParam);
	}

	public de.swat.dataModels.Map.StructureCollisionObjectDataModel getCurrentStructureObject()
	{
		return INSTANCE.getCurrentStructureObject();
	}

	public void setCurrentStructureObject(de.swat.dataModels.Map.StructureCollisionObjectDataModel pParam)
	{
		INSTANCE.setCurrentStructureObject(pParam);
	}

	public de.swat.dataModels.Map.Structure getCurrentStructure()
	{
		return INSTANCE.getCurrentStructure();
	}

	public void setCurrentStructure(de.swat.dataModels.Map.Structure pParam)
	{
		INSTANCE.setCurrentStructure(pParam);
	}

}
