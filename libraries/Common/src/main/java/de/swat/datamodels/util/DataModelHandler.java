package de.swat.datamodels.util;

import de.swat.datamodels.IModelAccess;

/**
 * Der DataModelHandler verschafft Zugriff zu den
 * DataModelAccessen und erstellt ggf. neue Instanzen.
 *
 * @author W. Glanzer, 14.02.14
 */
public abstract class DataModelHandler
{

  public static IModelAccess newModelAccess(Class pModelAccess)
  {
    try
    {
      return (IModelAccess) pModelAccess.newInstance();
    }
    catch (InstantiationException | IllegalAccessException e)
    {
      e.printStackTrace();
    }

    return null;
  }

}
