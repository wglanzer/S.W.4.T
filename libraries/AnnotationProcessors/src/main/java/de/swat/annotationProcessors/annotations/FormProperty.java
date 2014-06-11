package de.swat.annotationProcessors.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Markiert ein Feld, das im PropertySheet des MapCreators
 * angezeigt wird.
 *
 * @author W. Glanzer, 02.02.14
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FormProperty
{

  String defaultValue() default "default not defined";

  int position() default 0;
}
