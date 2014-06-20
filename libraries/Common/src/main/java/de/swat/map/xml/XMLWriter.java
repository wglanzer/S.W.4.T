package de.swat.map.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Schreibt ein XML auf verschiedene Medien
 *
 * @author W.Glanzer, 18.06.2014.
 */
public class XMLWriter
{

  /**
   * Schreibt ein XML-Element auf Festplatte
   *
   * @param pRoot Root-Element des XML
   * @param pFile File-Objekt, auf das geschrieben werden soll
   * @throws IOException Wenn beim Schreiben ein Fehler aufgetreten ist
   */
  public static void toFile(Element pRoot, File pFile) throws IOException
  {
    Document doc = new Document(pRoot);
    XMLOutputter xmlOutput = new XMLOutputter();
    xmlOutput.setFormat(Format.getPrettyFormat());
    xmlOutput.output(doc, new FileWriter(pFile));
  }

  /**
   * Wandelt ein JDOM2-Element in einen String um.
   * PrettyFormat wird verwendet.
   *
   * @param pElement XML-Element
   * @return Element als String
   */
  public static String toString(Element pElement)
  {
    XMLOutputter xmlOutput = new XMLOutputter();
    xmlOutput.setFormat(Format.getPrettyFormat());
    return xmlOutput.outputString(pElement);
  }

  /**
   * Fügt das angegebene Element an den angegebenen Outputstream
   * unter dem angegebenen Namen als Zip-Eintrag ein.
   *
   * @param pOutputStream Outputstream, der die ZIP-Datei erstellt
   * @param pElement      XML-Element
   * @param pFileName     Dateiname des Elements
   * @throws IOException Wenn dabei ein Fehler aufgetreten ist
   */
  public static void appendAsZipEntry(ZipOutputStream pOutputStream, Element pElement, String pFileName) throws IOException
  {
    ZipEntry e = new ZipEntry(pFileName);
    pOutputStream.putNextEntry(e);

    byte[] data = toString(pElement).getBytes();
    pOutputStream.write(data, 0, data.length);
    pOutputStream.closeEntry();
  }

  /**
   * Ließt einen XMLString aus und gibt ihn als
   * JDOM2-Element wieder zurück
   *
   * @param pXMLString  XML als String
   * @return JDOM2-Element
   */
  public static Element toElement(String pXMLString) throws JDOMException, IOException
  {
    StringReader stringReader = new StringReader(pXMLString);
    SAXBuilder builder = new SAXBuilder();
    Document doc = builder.build(stringReader);
    Element elem = doc.getRootElement();
    return elem.detach();
  }
}
