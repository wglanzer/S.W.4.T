package de.swat.accesses;

import de.swat.datamodels.RibbonDataModel;
import de.swat.IModelAccess;
import java.io.*;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
public class RibbonModelAccess implements IModelAccess, Serializable
{

	private static final long serialVersionUID = 1L;
	private static final RibbonDataModel INSTANCE = new RibbonDataModel();

	public int getButtonWidth()
	{
		return INSTANCE.getButtonWidth();
	}

	public void setButtonWidth(int pParam)
	{
		INSTANCE.setButtonWidth(pParam);
	}

	public java.util.Set<de.swat.IRibbonAction> getChildren()
	{
		return INSTANCE.getChildren();
	}

	public void setChildren(java.util.Set<de.swat.IRibbonAction> pParam)
	{
		INSTANCE.setChildren(pParam);
	}

}
