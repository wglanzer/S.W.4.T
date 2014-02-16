package de.swat;

import de.swat.accesses.MapModelAccess;
import de.swat.dataModels.Map.*;
import de.swat.datamodels.MapDataModel;
import de.swat.math.Vector2D;
import de.swat.util.DataModelHandler;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author W. Glanzer, 16.02.14
 */
public class Map
{

  private MapModelAccess modelAccess = (MapModelAccess) DataModelHandler.newModelAccess(MapModelAccess.class);

  /**
   * Fügt dem aktuell im Fokus stehenden Strukturobjekt
   * einen neuen Punkt hinzu.
   *
   * @param pPoint Die Punkte, die hinzugefügt werden sollen
   */
  public void addPoints(ArrayList<Point> pPoint)
  {
    if (modelAccess.getCurrentStructure() == null)
    {
      newStructure();
    }
    modelAccess.getCurrentStructure().getPointList().addAll(pPoint);  //Hinzufügen des Punktes zum Structure
  }

  /**
   * Erstellen eines neuen Kollision Objektes
   */
  private void newStructure()
  {
    Structure newStructure = new Structure();
    StructureCollisionObjectDataModel newStructureObject = new StructureCollisionObjectDataModel();
    modelAccess.setCurrentStructure(newStructure);
    modelAccess.setCurrentStructureObject(newStructureObject);
    newStructureObject.setStructure(newStructure);
    modelAccess.getCollisionObjects().add(newStructureObject);
  }

  /**
   * Schließt eine Struktur
   */
  private void closeStructure()
  {
    //Schließen eines Structures
    modelAccess.setCurrentStructureObject(null);
    modelAccess.setCurrentStructure(null);
  }

  /**
   * lässt beim angegebenen Objekt die Bounding-Box berechnen.
   *
   * @param pObject Das Kollisionsobjekt, von dem die Boundingbox berechnet werden soll
   */
  private void setBoundingBox(AbstractCollisionObjectDataModel pObject)
  {
    pObject.setBoundingBox();
  }

  /**
   * Überprüft die Drehrichtung einer Struktur und dreht diese ggf um.
   *
   * @param pStructure Zu überprüfende Struktur
   */
  private void checkDirection(Structure pStructure)
  {
    pStructure.checkDirection();
  }

  /**
   * Der interne Befehl zum Beendigen einer Struktur.
   */
  public StructureCollisionObjectDataModel finishStructure()
  {
    Structure currentStructure = modelAccess.getCurrentStructure();
    StructureCollisionObjectDataModel currentStructureObject = modelAccess.getCurrentStructureObject();

    if (currentStructure.getPointList().size() > 1)
    {
      setBoundingBox(currentStructureObject);
      checkDirection(currentStructure);
    }
    modelAccess.getRaster().addToRaster(currentStructureObject.getBoundingBox(), modelAccess.getCollisionObjects().indexOf(currentStructureObject));
    closeStructure();
    return currentStructureObject;
  }

  /**
   * Überprüft die erste Kollision eines Vektors mit der Welt und gib diese als Punkt zurück
   *
   * @param pVector Kollisionsvektor
   * @return Kollisionspunkt
   */
  public Point checkFirstCollision(Vector2D pVector)
  {
    return modelAccess.getRaster().checkFirstCollision(pVector);
  }

  /**
   * Überprüft alle Kollisiinen eines Vektors mit der Welt und gib diese als Arraylist von Punkten zurück
   *
   * @param pVector Kollisionsvektor
   * @return Kollisionspunkte
   */
  public ArrayList<Point> checkAllCollsions(Vector2D pVector)
  {
    return modelAccess.getRaster().checkAllCollisions(pVector);
  }

  /**
   * Überprüft den kürzesten Weg zwischen 2 Punkten und gibt den Pfad als Arraylist mit Punkten zurück
   *
   * @param pStartPoint Startposition
   * @param pEndPoint   Der Zielpunkt
   * @param pRadius     der Radius, der um Kurven eingehalten werden soll
   * @return Der Pfad als Arraylist
   */
  public ArrayList<Point> findPath(Point pStartPoint, Point pEndPoint, int pRadius)
  {
    ArrayList<Point> pointList = new ArrayList<>();
    pointList.add(pStartPoint);
    return modelAccess.getRaster().findPath(pStartPoint, pEndPoint, pRadius, new ArrayList<Integer>(), pointList);
  }

  private ArrayList<Point> simplify(ArrayList<Point> pList)
  {
    //TODO fertigstellen
    return null;
  }

  /**
   * Gibt ein interaktionsfähiges Objekt an der Mausposition zurück
   *
   * @param pMouseX x-Position der Maus
   * @param pMouseY y-Position der Maus
   * @return referenz auf das Interaktionsobjekt
   */
  public AbstractCollisionObjectDataModel getInteractableFromMousePosition(int pMouseX, int pMouseY)
  {
    return modelAccess.getRaster().getInteractableFromMousePosition(pMouseX, pMouseY);
  }

  /**
   * Berechnet alle BoundingBoxen neu
   */
  public void recalculateBoundingBoxes()
  {
    for (AbstractCollisionObjectDataModel currCollisionObject : modelAccess.getCollisionObjects())
      currCollisionObject.setBoundingBox();
  }
}
