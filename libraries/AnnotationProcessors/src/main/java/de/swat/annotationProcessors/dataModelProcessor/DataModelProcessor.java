package de.swat.annotationProcessors.dataModelProcessor;

import de.swat.annotations.*;

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
          out.write("package de.swat.accesses;\n");
          out.write("\n");
          out.write("import de.swat.datamodels." + oldClassName + ";\n");
          out.write("import de.swat.IModelAccess;\n");
          out.write("/**\n");
          out.write(" * Klasse automatisch generiert! Nicht veraendern oder ueberschreiben!!\n");
          out.write(" * @see " + this.getClass().getName() + "\n");
          out.write(" */\n");
          out.write("public class " + newClassName + " implements IModelAccess\n");
          out.write("{\n");
          out.write("\n");
          out.write("\tprivate static final " + oldClassName + " INSTANCE = new " + oldClassName + "();\n");
          out.write("\n");

          for (VariableElement currElement : ElementFilter.fieldsIn(elem.getEnclosedElements()))
          {
            String type = currElement.asType().toString();

            //Getter
            out.write("\tpublic " + type + " get" + capitalizeFirstLetter(currElement.getSimpleName()) + "()\n");
            out.write("\t{\n");
            out.write("\t\treturn INSTANCE.get" + capitalizeFirstLetter(currElement.getSimpleName()) + "();\n");
            out.write("\t}\n");
            out.write("\n");

            //Setter
            out.write("\tpublic void set" + capitalizeFirstLetter(currElement.getSimpleName()) + "(" + type + " pParam)\n");
            out.write("\t{\n");
            out.write("\t\tINSTANCE.set" + capitalizeFirstLetter(currElement.getSimpleName()) + "(pParam);\n");
            out.write("\t}\n");
            out.write("\n");
          }

          out.write("}\n");
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
