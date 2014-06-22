package de.swat.mapCreator.gui.ribbon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Hiermit werden Actions gekennzeichnet, die im
 * Ribbon eingebunden werden sollen.
 *
 * @author W. Glanzer, 05.12.13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface RibbonAction
{
}
