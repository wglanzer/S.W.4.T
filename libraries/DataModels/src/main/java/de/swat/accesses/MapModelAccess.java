package de.swat.accesses;

import de.swat.datamodels.MapDataModel;
import de.swat.AbstractModelAccess;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class MapModelAccess extends AbstractModelAccess
{

	private static final long serialVersionUID = 1L;
	private static final MapDataModel INSTANCE = new MapDataModel();

	public de.swat.Raster getRaster()
	{
		return INSTANCE.getRaster();
	}

	public void setRaster(de.swat.Raster pParam)
	{
		INSTANCE.setRaster(pParam);
		_fireFieldChanged(this, getFieldByName("raster", MapDataModel.class), pParam);
	}

	public java.util.ArrayList<de.swat.dataModels.Map.AbstractCollisionObjectDataModel> getCollisionObjects()
	{
		return INSTANCE.getCollisionObjects();
	}

	public void setCollisionObjects(java.util.ArrayList<de.swat.dataModels.Map.AbstractCollisionObjectDataModel> pParam)
	{
		INSTANCE.setCollisionObjects(pParam);
		_fireFieldChanged(this, getFieldByName("collisionObjects", MapDataModel.class), pParam);
	}

	public de.swat.dataModels.Map.StructureCollisionObjectDataModel getCurrentStructureObject()
	{
		return INSTANCE.getCurrentStructureObject();
	}

	public void setCurrentStructureObject(de.swat.dataModels.Map.StructureCollisionObjectDataModel pParam)
	{
		INSTANCE.setCurrentStructureObject(pParam);
		_fireFieldChanged(this, getFieldByName("currentStructureObject", MapDataModel.class), pParam);
	}

	public de.swat.dataModels.Map.Structure getCurrentStructure()
	{
		return INSTANCE.getCurrentStructure();
	}

	public void setCurrentStructure(de.swat.dataModels.Map.Structure pParam)
	{
		INSTANCE.setCurrentStructure(pParam);
		_fireFieldChanged(this, getFieldByName("currentStructure", MapDataModel.class), pParam);
	}

}
