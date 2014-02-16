package de.swat.accesses;

import de.swat.datamodels.RasterDataModel;
import de.swat.IModelAccess;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class RasterModelAccess implements IModelAccess
{

	private static final RasterDataModel INSTANCE = new RasterDataModel();

	public java.util.ArrayList[][] getRaster()
	{
		return INSTANCE.getRaster();
	}

	public void setRaster(java.util.ArrayList[][] pParam)
	{
		INSTANCE.setRaster(pParam);
	}

	public int getRasterSize()
	{
		return INSTANCE.getRasterSize();
	}

	public void setRasterSize(int pParam)
	{
		INSTANCE.setRasterSize(pParam);
	}

	public de.swat.Map getMap()
	{
		return INSTANCE.getMap();
	}

	public void setMap(de.swat.Map pParam)
	{
		INSTANCE.setMap(pParam);
	}

}
