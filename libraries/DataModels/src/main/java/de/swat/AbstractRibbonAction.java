package de.swat;

/**
 * Abstrakte Klasse für RibbonActions.
 * Hier wird beispielsweise schon die compareTo-Methode erstellt.
 *
 * @author W. Glanzer, 23.02.14
 */
public abstract class AbstractRibbonAction implements IRibbonAction
{

  @Override
  public int compareTo(Object o)
  {
    return 0;
  }
}
