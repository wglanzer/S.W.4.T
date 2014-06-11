package de.swat.annotationProcessors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
