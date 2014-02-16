package de.swat.annotations;

import de.swat.enums.*;

import java.lang.annotation.*;

/**
 * Hiermit werden Buttons gekennzeichnet, die im
 * Ribbon eingebunden werden sollen.
 *
 * @author W. Glanzer, 05.12.13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RibbonComponent
{

  ERibbonCategory category();

  ERibbonSubCategory subcategory() default ERibbonSubCategory.Default;

  int rowspan() default 3;

  int posID() default 0;
}
