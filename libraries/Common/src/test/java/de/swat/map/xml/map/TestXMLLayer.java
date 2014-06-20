package de.swat.map.xml.map;

import de.swat.map.xml.XMLLayer;
import de.swat.map.xml.XMLWriter;
import de.swat.map.xml.dummy.DummyXMLLayerGenerator;
import junit.framework.Assert;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author W.Glanzer, 17.06.2014.
 */
public class TestXMLLayer
{

  @Test
  public void testXMLLayer() throws IOException, JDOMException
  {
    File xmlFile = File.createTempFile("test", "xml");

    //XML-Datei schreiben
    XMLLayer xmlLayer = DummyXMLLayerGenerator.generateDummyXMLLayer(0);
    Element expected = xmlLayer.toXML();

    try
    {
      XMLWriter.toFile(expected, xmlFile);
    }
    catch(IOException e)
    {
      e.printStackTrace();
    }

    //Datei lesen
    SAXBuilder builder = new SAXBuilder();
    Document doc = builder.build(xmlFile);

    // Provisorische Überprüfungen, da ein Equals nicht funktrioniert
    Element actual = doc.getRootElement();

    Assert.assertEquals(expected.getChildren().size(), actual.getChildren().size());
    Assert.assertEquals(expected.getChildren().toString(), actual.getChildren().toString());
    Assert.assertEquals(expected.getAttributes().size(), actual.getAttributes().size());
    Assert.assertEquals(expected.getAttributes().toString(), actual.getAttributes().toString());
  }
}
