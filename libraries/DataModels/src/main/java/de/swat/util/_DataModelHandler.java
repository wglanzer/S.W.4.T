//package de.swat.util;
//
//import de.swat.annotations.DataModel;
//import de.swat.annotations.FormProperty;
//import de.swat.datamodels.IDataModel;
//import de.swat.utils.LookupUtil;
//
//import java.lang.reflect.Field;
//import java.util.*;
//
///**
// * //TODO
// */
//public class DataModelHandler
//{
//
//  private static Map<String, IDataModel> allDataModels;
//
//  static{
//    allDataModels = _getAllDataModels();
//  }
//
//  private DataModelHandler()
//  {
//  }
//
//  /**
//   * Liefert das IDataModel mit einem bestimmten Namen.
//   * <tt>null</tt>, falls nicht vorhanden
//   *
//   * @param pName Name des Datenmodells
//   * @return IDataModel
//   */
//  public static IDataModel getSpecificDataModel(String pName)
//  {
//    IDataModel gotDataModel = allDataModels.get(pName);
//    if(gotDataModel == null)
//      throw new RuntimeException();  //TODO Model null
//
//    return gotDataModel;
//  }
//
//  /**
//   * Liefert alle DatenModelle anhand der @DataModel-Annotation,
//   * die im "de.swat.datamodels"-Package liegen.
//   *
//   * @return Map 'String,IDataModel'
//   */
//  private static Map<String, IDataModel> _getAllDataModels()
//  {
//    Set<Class<?>> classes = LookupUtil.getClassByAnnotation (DataModel.class, "de.swat.datamodels");
//    Map<String, IDataModel> returnMap = new HashMap<> ();
//    for (Class<?> currClass : classes)
//    {
//      try
//      {
//        String dataModelName = currClass.getSimpleName();
//        Object classAsObject = currClass.newInstance();
//        IDataModel dataModel = (IDataModel) classAsObject;
//        returnMap.put(dataModelName, dataModel);
//      }
//      catch (InstantiationException | IllegalAccessException e)
//      {
//        e.printStackTrace();      //TODO errorhandling
//      }
//      catch (ClassCastException e)
//      {
//        System.out.println("Is not type of IDataModel"); //TODO errorhandling
//        e.printStackTrace();
//      }
//    }
//    return returnMap;
//  }
//
//  public static Set<Field> getPropertySheetFields ()
//  {
//    List<Class<?>> classes = new ArrayList<>();
//    for (Map.Entry<String, IDataModel> currEntry : allDataModels.entrySet())
//    {
//       classes.add(currEntry.getValue().getClass());
//    }
//
//    return LookupUtil.getFieldByAnnotation(FormProperty.class, classes);
//  }
//}
