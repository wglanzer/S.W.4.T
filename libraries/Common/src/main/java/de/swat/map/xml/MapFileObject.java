package de.swat.map.xml;

import com.google.common.io.CharStreams;
import de.swat.SwatRuntimeException;

import java.io.*;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Diese Klasse repräsentiert eine Map des Spiels,
 * wie sie auf Platte existiert.
 *
 * @author W.Glanzer, 17.06.2014.
 */
public class MapFileObject
{

  public static final String MAP_FILETYPE = ".smap";
  public static final String LAYER_DIR = "layers";
  public static final String LAYER_NAME = "layer%s.xml";
  /**
   * Liste aller Layers. Sortiert nach z-Index.
   * Index 0 in der Liste: Layer ganz unten,
   * Index n in der Liste: Layer ganz oben.
   */
  public List<XMLLayer> layers = new ArrayList<>();

  public MapFileObject()
  {
  }

  public void addLayer(XMLLayer pLayer)
  {
    layers.add(pLayer);
  }

  /**
   * Generiert aus den Layers u.Ä. ein Zip-File.
   *
   * @param pDirGenerateTo File-Objekt, das als Ziel-Ordner verwendet wird
   * @param pName          Dateiname der ZIP
   * @throws IOException
   */
  public void generateZip(File pDirGenerateTo, String pName) throws IOException
  {
    // Zip-Outputstream, wo alle Dateien hin sollen
    if(!pDirGenerateTo.isDirectory())
      throw new UnsupportedOperationException("File is not a directory!");

    String dirPath = pDirGenerateTo.getPath();
    String path = dirPath + (dirPath.endsWith("/") || dirPath.endsWith("\\") ? "" : File.separator) + pName + MAP_FILETYPE;
    ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path));
    out.setLevel(ZipOutputStream.DEFLATED);

    for(int i = 0; i < layers.size(); i++)
    {
      XMLLayer currLayer = layers.get(i);
      XMLWriter.appendAsZipEntry(out, currLayer.toXML(), LAYER_DIR + File.separator + String.format(LAYER_NAME, i));
    }

    out.close();
  }

  public void fromZip(File pZipFile) throws IOException
  {
    ZipFile file = new ZipFile(pZipFile);

    // Alle Dateien durchgehen
    for(Enumeration currEnum = file.entries(); currEnum.hasMoreElements(); )
    {
      ZipEntry currEntry = (ZipEntry) currEnum.nextElement();
      InputStream is = file.getInputStream(currEntry);
      String read = CharStreams.toString(new InputStreamReader(is));

      String folderName;
      String entryName = currEntry.getName();
      folderName = entryName.split("\\\\")[0];
      if(folderName.equals(entryName))
        folderName = entryName.split("/")[0];

      try
      {
        switch(folderName)
        {
          case LAYER_DIR:
            XMLLayer layer = new XMLLayer(-1);
            layer.fromXML(XMLWriter.toElement(read));
            layers.add(layer);
            break;

          default:
            throw new SwatRuntimeException("Unknown file inside zip (name=" + folderName + ")", null);
        }
      }
      catch(Exception e)
      {
        throw new SwatRuntimeException("Could not add file to map!", e);
      }
      finally
      {
        is.close();
      }
    }
  }
}
