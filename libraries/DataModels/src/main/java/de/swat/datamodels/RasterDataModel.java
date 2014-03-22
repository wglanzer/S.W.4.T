package de.swat.datamodels;

import de.swat.Map;
import de.swat.annotations.DataModel;
import de.swat.observableList2.ObservableList2;

import java.io.Serializable;

/**
 * @author Alex Biederer, 30.11.13
 */
@DataModel
public class RasterDataModel extends SimpleDataModel implements Serializable
{
  /**
   * 2-Dimensionales Feld, das eine Arraylist mit
   * Indices zu AbstractCollisionObjectDataModels liefert
   */
  private ObservableList2<Integer>[][] raster;

  /**
   * Größe der Raster (quadratisch) - überprüfung der Effizienz notwendig
   */
  private int rasterSize;

  private Map map;

  public ObservableList2<Integer>[][] getRaster()
  {
    return raster;
  }

  public void setRaster(ObservableList2<Integer>[][] pRaster)
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
