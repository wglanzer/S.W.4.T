package de.swat.accesses;

import de.swat.datamodels.RasterDataModel;
import de.swat.AbstractModelAccess;
import javax.annotation.Generated;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
@Generated(value = "de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor")
public class RasterModelAccess extends AbstractModelAccess
{

	private static final long serialVersionUID = 1L;
	private static final RasterDataModel INSTANCE = new RasterDataModel();

	public java.util.ArrayList<java.lang.Integer>[][] getRaster()
	{
		return INSTANCE.getRaster();
	}

	public void setRaster(java.util.ArrayList<java.lang.Integer>[][] pParam)
	{
		INSTANCE.setRaster(pParam);
		_fireFieldChanged(this, getFieldByName("raster", RasterDataModel.class), pParam);
	}

	public int getRasterSize()
	{
		return INSTANCE.getRasterSize();
	}

	public void setRasterSize(int pParam)
	{
		INSTANCE.setRasterSize(pParam);
		_fireFieldChanged(this, getFieldByName("rasterSize", RasterDataModel.class), pParam);
	}

	public de.swat.Map getMap()
	{
		return INSTANCE.getMap();
	}

	public void setMap(de.swat.Map pParam)
	{
		INSTANCE.setMap(pParam);
		_fireFieldChanged(this, getFieldByName("map", RasterDataModel.class), pParam);
	}

}
