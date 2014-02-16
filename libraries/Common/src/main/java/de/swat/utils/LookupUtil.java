package de.swat.utils;

import org.jetbrains.annotations.Nullable;
import org.reflections.Reflections;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;

/**
 * @author W. Glanzer, 05.12.13
 */
public class LookupUtil
{
  /**
   * Durchsucht das komplette Projekt nach der angegebenen
   * Annotation und gibt die gefundenen Klassen als Set
   * zurück.
   * Diese Methode sollte jedoch nicht verwendet werden,
   * das sie extrem langsam ist.
   *
   * @param pAnnotation Bestimmte Annotation (Klasse), die
   *                    die gesuchten Klassen haben sollen
   * @param pLookInPath Der Pfad, in dem gesucht wird.
   *                    <code>null</code> für den root-Pfad
   */
  public static Set<Class<?>> getClassByAnnotation(Class pAnnotation, @Nullable String pLookInPath)
  {
    Reflections reflections;
    if (pLookInPath != null)
      reflections = new Reflections(pLookInPath);
    else
      reflections = new Reflections();
    //noinspection unchecked
    return reflections.getTypesAnnotatedWith(pAnnotation);
  }

  /**
   * Liefert ein Set aus Methoden, die mit einer
   * bestimmten Annotation markiert wurden.
   *
   * @param pAnnotation Annotation, die gesucht werden soll
   * @param pLookInPath Pfad, in dem gesucht wird. <code>null</code>
   *                    für den root-Pfad
   * @return Set aus Methoden, die mit Annotation
   * markiert wurden
   */
  public static Set<Method> getMethodByAnnotation(Class pAnnotation, @Nullable String pLookInPath)
  {
    Reflections reflections;
    if (pLookInPath != null)
      reflections = new Reflections(pLookInPath);
    else
      reflections = new Reflections();
    //noinspection unchecked
    return reflections.getMethodsAnnotatedWith(pAnnotation);
  }

  /**
   * Liefert ein Set aus Feldern, die mit einer
   * bestimmten Annotation markiert wurden.
   *
   * @param pAnnotation    Annotation, die gesucht werden soll
   * @param pLookInClasses Collection aus Klassen, in denen ein Feld mit
   *                       dieser bestimmten Annotation gesucht werden soll
   * @return Set aus Feldern, die mit Annotation
   * markiert wurden (NotNull)
   */
  public static Set<Field> getFieldByAnnotation(Class pAnnotation, Collection<Class<?>> pLookInClasses)
  {
    Set<Field> returnField = new HashSet<>();
    for (Class<?> currClass : pLookInClasses)
    {
      Field[] fs = currClass.getDeclaredFields();
      for (Field f : fs)
      {
        Annotation a = f.getAnnotation(pAnnotation);

        if (a != null)
          returnField.add(f);
      }
    }
    return returnField;
  }

  /**
   * Liefert ein Set aus Feldern, die mit einer
   * bestimmten Annotation markiert wurden.
   *
   * @param pClassAnnotation Annotation, die die Parent-Klasse besitzt
   * @param pAnnotation      Annotation, die gesucht werden soll
   * @param pLookInPath      Pfad, in dem gesucht wird. <code>null</code>
   *                         für den root-Pfad
   * @return Set aus Feldern, die mit Annotation
   * markiert wurden (NotNull)
   */
  public static Set<Field> getFieldByAnnotation(Class pClassAnnotation, Class pAnnotation, @Nullable String pLookInPath)
  {
    Set<Class<?>> classes = getClassByAnnotation(pClassAnnotation, pLookInPath);
    return getFieldByAnnotation(pAnnotation, classes);
  }
}
