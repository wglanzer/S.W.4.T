package de.swat.util;

import de.swat.*;
import de.swat.accesses.*;
import de.swat.observableList2.ObservableList2;
import org.junit.*;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Testet mit dem MapDataModel, ob das SaveUtil funktioniert.
 * Erstellt eine neue Datei im selben Verzeichnis wie das TestSaveUtil,
 * und ruft SaveUtil.save() und SaveUtil.load() auf.
 * Hierbei wird danach dann getestet, ob mit getRaster() das selbe Objekt
 * rauskommt, wie zuvor mit getRaster() gesetzt wurde.
 *
 * @author W. Glanzer, 21.02.2014
 */
public class TestSaveUtil
{

  @Test
  public void Test_SaveUtil() throws IOException
  {
    ObservableList2<Point> collPoints = new ObservableList2<>();
    collPoints.add(new Point(20, 20));
    collPoints.add(new Point(30, 20));

    MapCreatorModelAccess mapModelAccess = new MapCreatorModelAccess();
    MapCreatorMap map = new MapCreatorMap(mapModelAccess);
    map.setCollisionPoints(collPoints);
    mapModelAccess.setMapCreatorMap(map);

    File file = new File("libraries\\DataModels\\src\\test\\java\\de\\swat\\util\\SaveUtilTest.txt");

    //noinspection ResultOfMethodCallIgnored
    file.createNewFile();
    file.deleteOnExit();

    Assert.assertTrue(file.canWrite());

    SaveUtil.save(mapModelAccess, file);

    IModelAccess loaded = SaveUtil.load(file);
    MapCreatorModelAccess modelAccess = (MapCreatorModelAccess) loaded;
    MapCreatorMap map2 = modelAccess.getMapCreatorMap();
    Assert.assertArrayEquals(collPoints.toArray(), map2.getCollisionPoints().toArray());
    Assert.assertEquals(map, map2);
  }

}
