package de.swat.core.Map;

import de.swat.core.DefaultSettingsCallback;
import de.swat.core.Utility.Log;
import de.swat.utils.ImageUtil;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;

/**
 * Repräsentiert eine Core.Map bzw. ein Level des Spiels
 *
 * @author Werner Glanzer, 02.10.13
 */
public class Map
{
  /*Repräsentiert die Hintergrundmap, auf der statische Objekte und sichtbare Wände platziert sind*/
  private BufferedImage backgroundMap;
  /**
   * Repräsentiert die Map. Dies beinhaltet die CollisionMap, sowie andere Maps, die benötigt werden
   * inhalt[x][y][index][clusterIndex/actionIndex] = {0,4,7,14}
   */
  private int[][][][] indices = new int[100][100][2][DefaultSettingsCallback.MAXCLUSTERSINRASTERS];
  private HashMap<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
  private HashMap<Integer, Structure> structure = new HashMap<Integer, Structure>();
  private boolean mapLoaded = false;

  public Map(String pName)
  {
    backgroundMap = ImageUtil.loadFileAsImage(new File(DefaultSettingsCallback.MAP_PATH + pName + ".png"));
  }

  public void loadMap(int[][][][] pIndices, HashMap<Integer, Cluster> pClusters, HashMap<Integer, Structure> pStructure)
  {
    indices = pIndices;
    clusters = pClusters;
    structure = pStructure;
    Log.log("Indices-Size", indices, "Cluster-Size", clusters, "Structure-Size", structure);
    mapLoaded = true;
  }

  /**
   * Liefert die BackgroundMap als BigImage zurück.
   * Diese enthält NUR das BigImage. Also weder CollisionMap, etc.
   *
   * @return BigImage als Sprite
   */
  public BufferedImage getMapAsImage()
  {
    return backgroundMap;
  }

}
