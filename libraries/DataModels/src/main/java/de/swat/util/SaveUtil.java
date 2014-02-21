package de.swat.util;

import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.util.FSTOutputStream;
import de.swat.IModelAccess;
import de.swat.datamodels.IDataModel;
import de.swat.exceptions.SwatRuntimeException;

import java.io.*;

/**
 * Diese Klasse dient dazu, die IDataModels zu speichern und
 * wieder zu laden. Dies passiert durch Serialisierung.
 * Hierzu wird allerdings nicht der Standard-Java-Mechanismus
 * genutzt, sondern ein 3rd-Party-Code, der einen gewaltigen
 * Perfomance-Vorteil bedeutet.
 *
 * @author W. Glanzer, 07.12.13
 */
public class SaveUtil
{
  public static void save(IModelAccess pModelAccess, File pSaveToFile)
  {
    try (FSTOutputStream outputStream = new FSTOutputStream(new FileOutputStream(pSaveToFile)))
    {
      // Objektausgabekanal für Serialisierung Öffnen
      ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);

      // Objekte serialisiert in Datei ausgeben
      objectOutput.writeObject(pModelAccess);

      // Ausgabekanal schließen
      objectOutput.close();
    }
    catch (IOException e)
    {
      throw new SwatRuntimeException("DataModel could not be saved! (" + pModelAccess.getClass().getName() + ", " + pSaveToFile.exists() + ")", e);
    }
  }

  public static IModelAccess load(File pFile)
  {
    IModelAccess returnModel;

    try (FSTObjectInput inputStream = new FSTObjectInput(new FileInputStream(pFile)))
    {
      // Deserialisierung
      ObjectInputStream objectInput = new ObjectInputStream(inputStream);

      // Auslesen
      returnModel = (IModelAccess) objectInput.readObject();
    }
    catch (IOException | ClassNotFoundException e)
    {
      throw new SwatRuntimeException("File could not be loaded! (" + pFile.getAbsolutePath() + ")", e);
    }

    return returnModel;
  }
}