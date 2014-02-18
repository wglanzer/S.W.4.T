package de.swat.annotations;

import java.lang.annotation.*;

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
