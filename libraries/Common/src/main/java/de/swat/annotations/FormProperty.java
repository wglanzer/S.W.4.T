package de.swat.annotations;

import java.lang.annotation.*;

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
