package de.swat.map.xml.map;

import com.google.common.io.CharStreams;
import de.swat.map.xml.MapFileObject;
import de.swat.map.xml.dummy.DummyXMLLayerGenerator;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Testet, ob das Zippen der Map funktioniert
 *
 * @author W.Glanzer, 20.06.2014.
 */
public class TestMapZipping
{

  @Before
  public void before() throws IOException
  {
    MapFileObject fileObject = new MapFileObject();
    fileObject.addLayer(DummyXMLLayerGenerator.generateDummyXMLLayer(0));
    fileObject.addLayer(DummyXMLLayerGenerator.generateDummyXMLLayer(1));
    fileObject.addLayer(DummyXMLLayerGenerator.generateDummyXMLLayer(2));
    fileObject.addLayer(DummyXMLLayerGenerator.generateDummyXMLLayer(3));

//    fileObject.generateZip(new File("Z:\\workspace-IntelliJ\\S.W.4.T\\libraries\\Common\\src\\test\\java\\de\\swat\\map\\xml\\map\\TestMapZipping.java").getParentFile(), "testMap");
    fileObject.generateZip(_getTargetDir(), "testMap");
  }

  @Test
  public void testMapZipping() throws IOException
  {
    ZipFile file = new ZipFile(_getTargetDir() + "/testMap" + MapFileObject.MAP_FILETYPE);

    int counter = 0;
    for(Enumeration e = file.entries(); e.hasMoreElements(); )
    {
      ZipEntry currEntry = (ZipEntry) e.nextElement();

      InputStream is = file.getInputStream(currEntry);
      String read = CharStreams.toString(new InputStreamReader(is));

      Assert.assertTrue(!read.isEmpty());
      Assert.assertTrue(read.startsWith("<layer "));
      Assert.assertTrue(read.endsWith("</layer>"));

      counter++;
    }

    Assert.assertEquals(counter, 4);
  }

//  public static void main(String[] args) throws IOException
//  {
//    new TestMapZipping().before();
//  }

  public File _getTargetDir()
  {
    String relPath = getClass().getProtectionDomain().getCodeSource().getLocation().getFile();
    File targetDir = new File(relPath + "../../target");
    if(!targetDir.exists())
      targetDir.mkdir();

    return targetDir;
  }
}
