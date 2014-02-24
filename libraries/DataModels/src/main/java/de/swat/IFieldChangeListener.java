package de.swat;

/**
 * Dieser Listener wird bei den DatenModellen über
 * die ModelAccesses  eingehängt. Er dient dazu, mitzubekommen,
 * ob sich ein Feld im DatenModell geändert hat
 *
 * @author W. Glanzer, 22.02.14
 */
public interface IFieldChangeListener
{

  public void fieldChanged(FieldChangeObject pFieldObject);

}
