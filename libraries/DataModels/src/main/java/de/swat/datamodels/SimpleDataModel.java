package de.swat.datamodels;

/**
 * @author W. Glanzer, 02.02.14
 */
public abstract class SimpleDataModel implements IDataModel
{
  @Override
  public String getName()
  {
    return getClass().getSimpleName();
  }
}
