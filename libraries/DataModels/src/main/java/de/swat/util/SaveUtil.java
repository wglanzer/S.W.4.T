package de.swat.util;

import de.ruedigermoeller.serialization.FSTObjectInput;
import de.ruedigermoeller.serialization.util.FSTOutputStream;
import de.swat.datamodels.MapDataModel;
import de.swat.exceptions.SwatRuntimeException;

import java.io.*;

/**
 * Diese Klasse dient dazu, das MapDataModel zu speichern und
 * wieder zu laden. Dies passiert durch Serialisierung.
 * Hierzu wird allerdings nicht der Standard-Java-Mechanismus
 * genutzt, sondern ein 3rd-Party-Code, der einen gewaltigen
 * Perfomance-Vorteil bedeutet.
 *
 * @author W. Glanzer, 07.12.13
 */
public class SaveUtil
{

  //public static void save(MapDataModel pMapDataModel, String pSaveToPath)
  //{
    //FSTOutputStream outputStream = null;
    //try
    //{
    //  // Byteorientierten Ausgabekanal Öffnen
    //  outputStream = new FSTOutputStream(new FileOutputStream(pSaveToPath));
    //
    //  // Objektausgabekanal für Serialisierung Öffnen
    //  ObjectOutputStream objectOutput = new ObjectOutputStream(outputStream);
    //
    //  // Objekte serialisiert in Datei ausgeben
    //  objectOutput.writeObject(pMapDataModel);
    //
    //  // Ausgabekanal schließen
    //  objectOutput.close();
    //}
    //catch (IOException e)
    //{
    //  e.printStackTrace();
    //}
    //finally
    //{
    //  try
    //  {
    //    if (outputStream != null)
    //    {
    //      outputStream.close();
    //    }
    //  }
    //  catch (Exception e)
    //  {
    //    //noinspection ThrowFromFinallyBlock
    //    throw new SwatRuntimeException("Failed to close outputStream at SaveUtil", e);
    //  }
    //}
  //}

  //public static MapDataModel load(String pLoadFromPath)
  //{
    //MapDataModel returnModel = new MapDataModel();
    //FSTObjectInput inputStream = null;
    //try
    //{
    //  // Serialisiertes Objekt
    //  inputStream = new FSTObjectInput(new FileInputStream(pLoadFromPath));
    //  // Deserialisierung
    //  ObjectInputStream objectInput = new ObjectInputStream(inputStream);
    //  // Auslesen
    //  returnModel = (MapDataModel) objectInput.readObject();
    //  returnModel.recalculateBoundingBoxes();
    //}
    //catch (IOException | ClassNotFoundException e)
    //{
    //  throw new SwatRuntimeException("Failed to open inputStream at SaveUtil", e);
    //}
    //finally
    //{
    //  try
    //  {
    //    if (inputStream != null)
    //    {
    //      inputStream.close();
    //    }
    //  }
    //  catch (Exception e)
    //  {
    //    //noinspection ThrowFromFinallyBlock
    //    throw new SwatRuntimeException("Failed to close the inputStream at SaveUtil", e);
    //  }
    //}
    //
    //return returnModel;
  //}
}
