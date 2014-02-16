package de.swat.core.Map;

import de.swat.core.DefaultSettingsCallback;

import java.util.HashMap;

/**
 * Hilfsklasse zum erstellen einer Core.Map-Instanz
 *
 * @author Werner Glanzer, 03.10.13
 */
public class MapLoader
{
  private Map currentMap;

  public MapLoader()
  {

  }

  /**
   * Erstellt und lädt die Core.Map mit dem angebenen Namen und speichert
   * sie danach in die Variable "currentMap"
   *
   * @param pName Name der zu ladenden Core.Map
   * @return
   */
  public void loadMap(String pName)
  {
    currentMap = new Map(pName);
    int[][][][] indices = new int[100][100][2][DefaultSettingsCallback.MAXCLUSTERSINRASTERS];
    HashMap<Integer, Cluster> clusters = new HashMap<Integer, Cluster>();
    HashMap<Integer, Structure> structure = new HashMap<Integer, Structure>();

    indices[0][0][0] = new int[]{0};
    clusters.put(0, new Cluster());
    structure.put(0, new Structure());
    currentMap.loadMap(indices, clusters, structure);
  }

  /**
   * Liefert die aktuell geladene Core.Map zurück
   *
   * @return Aktuell geladene Core.Map
   */
  public Map getCurrentMap()
  {
    return currentMap;
  }

  //public void render(double pXOff, double pYOff)
  //{
  //  currentMap.getMapAsImage().draw((float) -pXOff, (float) -pYOff);
  //}

}
