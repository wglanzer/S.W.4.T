package de.swat.annotations;

import java.lang.annotation.*;

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
