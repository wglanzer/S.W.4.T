package de.swat.utils;

/**
 * @author Alex Biederer, 01.12.13
 */
public class CommonUtil
{

  /**
   * Dreht die Values der Parameter, die übergeben wurden, um
   *
   * @param p1 Erster Parameter, der danach den zweiten Parameter repräsentiert
   * @param p2 Selbes wie p1, nur andersrum
   */
  public static void swap(Object p1, Object p2)
  {
    Object merk = p1;
    p1 = p2;
    p2 = merk;
  }
}
