package de.swat.clientserverintercom;

import org.junit.Assert;
import org.junit.Test;

/**
 * Testet, ob das Package richtig de- und encodiert
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class Test_Package
{

  @Test
  public void test()
  {
    SendablePackage sendablePackage = new SendablePackage("[[<--test1=val1-->]][[<--test2=val2-->]][[<--test4=val4-->]]hier komm dann die nachricht");

    Assert.assertEquals(sendablePackage.getStringAttribute("test1"), "val1");
    Assert.assertEquals(sendablePackage.getStringAttribute("test2"), "val2");
    Assert.assertEquals(sendablePackage.getStringAttribute("test4"), "val4");

    Assert.assertEquals(sendablePackage.getMessage(), "hier komm dann die nachricht");

    Assert.assertEquals(sendablePackage.getSendableString().length(), (ICSInterConstants.PACKAGE_START + "[[<--test1=val1-->]][[<--encoding=base64-->]][[<--test2=val2-->]][[<--test4=val4-->]]aGllciBrb21tIGRhbm4gZGllIG5hY2hyaWNodA==" + ICSInterConstants.PACKAGE_END).length());
  }

}
