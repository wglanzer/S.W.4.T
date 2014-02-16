package de.swat.core.Utility;

/**
 * @author Werner Glanzer, 05.10.13
 */
public class ConvertUtils
{

  public static Object[] combineArrays(Object[] pArrayOne, Object[] pArrayTwo)
  {
    Object[] temp = new Object[pArrayOne.length + pArrayTwo.length];
    for (int i = 0; i < pArrayOne.length; i++)
    {
      temp[i] = pArrayOne[i];
    }
    for (int i = 0; i < pArrayTwo.length; i++)
    {
      temp[pArrayOne.length + i] = pArrayTwo[i];
    }
    return temp;
  }

}
