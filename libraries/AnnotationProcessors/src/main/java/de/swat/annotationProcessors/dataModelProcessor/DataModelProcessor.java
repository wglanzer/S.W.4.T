package de.swat.annotationProcessors.dataModelProcessor;

import de.swat.annotations.*;
import de.swat.constants.IVersion;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.*;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic;
import java.io.PrintWriter;
import java.util.Set;

@SupportedAnnotationTypes("de.swat.annotations.DataModel")
@SupportedSourceVersion(SourceVersion.RELEASE_7)
public class DataModelProcessor extends AbstractProcessor
{
  private static final String implementedClasses = "IModelAccess, Serializable";

  @Override
  public boolean process(Set<? extends TypeElement> arg0, RoundEnvironment roundEnv)
  {
    for (Element elem : roundEnv.getElementsAnnotatedWith(DataModel.class))
    {
      //Prüfen, ob die Annotation DoNotProcess verwendet wurde
      if (elem.getAnnotation(DoNotProcess.class) == null)
      {
        String oldClassName = elem.getSimpleName().toString();
        String newClassName = oldClassName.replace("DataModel", "ModelAccess");

        try (PrintWriter out = new PrintWriter(processingEnv.getFiler().createSourceFile(newClassName).openWriter()))
        {
          out.write("package de.swat.accesses;\r\n");
          out.write("\r\n");
          out.write("import de.swat.datamodels." + oldClassName + ";\r\n");
          out.write("import de.swat.AbstractModelAccess;\r\n");
          out.write("/**\r\n");
          out.write(" * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!\r\n");
          out.write(" * @see " + this.getClass().getName() + "\r\n");
          out.write(" */\r\n");
          out.write("public class " + newClassName + " extends AbstractModelAccess\r\n");
          out.write("{\r\n");
          out.write("\r\n");
          out.write("\tprivate static final long serialVersionUID = " + IVersion.DataModelVersion + "L;\r\n");
          out.write("\tprivate static final " + oldClassName + " INSTANCE = new " + oldClassName + "();\r\n");
          out.write("\r\n");

          for (VariableElement currElement : ElementFilter.fieldsIn(elem.getEnclosedElements()))
          {
            String type = currElement.asType().toString();

            //Getter
            out.write("\tpublic " + type + " get" + capitalizeFirstLetter(currElement.getSimpleName()) + "()\r\n");
            out.write("\t{\r\n");
            out.write("\t\treturn INSTANCE.get" + capitalizeFirstLetter(currElement.getSimpleName()) + "();\r\n");
            out.write("\t}\r\n");
            out.write("\r\n");

            //Setter
            out.write("\tpublic void set" + capitalizeFirstLetter(currElement.getSimpleName()) + "(" + type + " pParam)\r\n");
            out.write("\t{\r\n");
            out.write("\t\tINSTANCE.set" + capitalizeFirstLetter(currElement.getSimpleName()) + "(pParam);\r\n");
            out.write("\t\t_fireFieldChanged(this, getFieldByName(\"" + currElement.getSimpleName() + "\", " + oldClassName + ".class), pParam);\r\n");
            out.write("\t}\r\n");
            out.write("\r\n");
          }

          out.write("}\r\n");
        }
        catch (Exception e)
        {
          processingEnv.getMessager().printMessage(Diagnostic.Kind.ERROR, e.toString());
        }
      }
    }
    return true;
  }

  /**
   * Schreibt den ersten Buchstaben des Strings "pObject.toString()" groß
   *
   * @param pObject Object, das verwendet werden soll
   * @return String mit Anfangsbuchstaben groß
   */
  private String capitalizeFirstLetter(Object pObject)
  {
    String objectString = pObject.toString();
    return Character.toString(objectString.charAt(0)).toUpperCase() + objectString.substring(1);
  }

}
