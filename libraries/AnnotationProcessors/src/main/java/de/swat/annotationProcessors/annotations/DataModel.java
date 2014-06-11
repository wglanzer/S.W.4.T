package de.swat.annotationProcessors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation, die ein Datenmodell
 * markiert
 *
 * @author W. Glanzer, 06.12.13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DataModel
{
}
