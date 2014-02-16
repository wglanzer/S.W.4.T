package de.swat.datamodels;

import de.swat.Raster;
import de.swat.annotations.DataModel;
import de.swat.constants.IWindowConstants;
import de.swat.dataModels.Map.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Werner Glanzer, 28.11.13
 */
@DataModel
public class MapDataModel extends SimpleDataModel
{
  /**
   * 2-Dimensionales Array, welches über x und y Koordinate
   * eines Rasters auf eine Liste mit den Indices der im
   * jeweiligen Rasterenthaltenen Strukturen zugreifen lässt
   */
  private Raster raster;

  /**
   * Ungeordnete Liste aller Collisions-Objekte. Die Indices
   * kommen von der ArrayList eines Rasters.
   */
  private ArrayList<AbstractCollisionObjectDataModel> collisionObjects = new ArrayList<>();

  /**
   * Attribute, die nur für das hinzufügen von Strukturen im MapCreator gebraucht werden
   */
  private StructureCollisionObjectDataModel currentStructureObject;
  private Structure currentStructure;


  public StructureCollisionObjectDataModel getCurrentStructureObject()
  {
    return currentStructureObject;
  }

  public void setCurrentStructureObject(StructureCollisionObjectDataModel pCurrentStructureObject)
  {
    currentStructureObject = pCurrentStructureObject;
  }

  public Structure getCurrentStructure()
  {
    return currentStructure;
  }

  public void setCurrentStructure(Structure pCurrentStructure)
  {
    currentStructure = pCurrentStructure;
  }

  public ArrayList<AbstractCollisionObjectDataModel> getCollisionObjects()
  {
    return collisionObjects;
  }

  public void setCollisionObjects(ArrayList<AbstractCollisionObjectDataModel> pCollisionObjects)
  {
    collisionObjects = pCollisionObjects;
  }

  public Raster getRaster()
  {
    return raster;
  }

  public void setRaster(Raster pRasterDataModel)
  {
    raster = pRasterDataModel;
  }
}