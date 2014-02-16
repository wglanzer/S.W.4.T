package de.swat.datamodels;

import de.swat.Map;
import de.swat.annotations.DataModel;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author Alex Biederer, 30.11.13
 */
@DataModel
public class RasterDataModel implements Serializable
{
  /**
   * 2-Dimensionales Feld, das eine Arraylist mit
   * Indices zu AbstractCollisionObjectDataModels liefert
   */
  private ArrayList[][] raster;

  /**
   * Größe der Raster (quadratisch) - überprüfung der Effizienz notwendig
   */
  private int rasterSize;

  private Map map;

  public ArrayList[][] getRaster()
  {
    return raster;
  }

  public void setRaster(ArrayList[][] pRaster)
  {
    raster = pRaster;
  }

  public int getRasterSize()
  {
    return rasterSize;
  }

  public void setRasterSize(int pRasterSize)
  {
    rasterSize = pRasterSize;
  }

  public Map getMap()
  {
    return map;
  }

  public void setMap(Map pMap)
  {
    map = pMap;
  }
}
