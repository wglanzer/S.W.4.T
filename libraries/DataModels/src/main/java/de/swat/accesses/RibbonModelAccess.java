package de.swat.accesses;

import de.swat.datamodels.RibbonDataModel;
import de.swat.AbstractModelAccess;
import javax.annotation.Generated;
/**
 * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!
 * @see de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor
 */
@Generated(value = "de.swat.annotationProcessors.dataModelProcessor.DataModelProcessor")
public class RibbonModelAccess extends AbstractModelAccess
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
		_fireFieldChanged(this, getFieldByName("buttonWidth", RibbonDataModel.class), pParam);
	}

	public java.util.Set<de.swat.IRibbonAction> getChildren()
	{
		return INSTANCE.getChildren();
	}

	public void setChildren(java.util.Set<de.swat.IRibbonAction> pParam)
	{
		INSTANCE.setChildren(pParam);
		_fireFieldChanged(this, getFieldByName("children", RibbonDataModel.class), pParam);
	}

}
