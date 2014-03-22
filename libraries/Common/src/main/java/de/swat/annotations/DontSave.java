package de.swat.annotations;

import java.lang.annotation.*;

/**
 * Marker-Annotation für Felder,
 * die nicht im SaveUtil gespeichert
 * werden sollen dürfen
 *
 * @author W. Glanzer, 24.02.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface DontSave
{
}
