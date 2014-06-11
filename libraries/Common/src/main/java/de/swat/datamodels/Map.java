package de.swat.datamodels;

import de.swat.datamodels.accesses.MapModelAccess;
import de.swat.datamodels.map.AbstractCollisionObjectDataModel;
import de.swat.datamodels.map.Structure;
import de.swat.datamodels.map.StructureCollisionObjectDataModel;
import de.swat.datamodels.models.MapDataModel;
import de.swat.math.Vector2D;
import de.swat.observableList2.ObservableList2;
import org.jetbrains.annotations.Nullable;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author W. Glanzer, 16.02.14
 */
public class Map extends AbstractFieldChangeListener implements Serializable
{

  protected MapModelAccess modelAccess;
  protected ObservableList2<AbstractCollisionObjectDataModel> structureRectangles = new ObservableList2<>();

  public Map(@Nullable MapModelAccess pModelAccess)
  {
    if (pModelAccess != null)
    {
      modelAccess = pModelAccess;
      structureRectangles = pModelAccess.getCollisionObjects();
    }
  }

  /**
   * Fügt dem aktuell im Fokus stehenden Strukturobjekt
   * einen neuen Punkt hinzu.
   *
   * @param pPoint Die Punkte, die hinzugefügt werden sollen
   */
  public void addPoints(ObservableList2<Point> pPoint)
  {
    if (modelAccess.getCurrentStructure() == null)
    {
      newStructure();
    }
    modelAccess.getCurrentStructure().getPointList().addAll(pPoint);  //Hinzufügen des Punktes zum Structure
    fireChange(this, "currentStructure", MapDataModel.class, modelAccess.getCurrentStructure());
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
   * Liefert die StructureRectangles zurück
   *
   * @return StructureRectangles
   */
  public ObservableList2<AbstractCollisionObjectDataModel> getStructureRectangles()
  {
    return modelAccess.getCollisionObjects();
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
  @Nullable
  public StructureCollisionObjectDataModel finishStructure()
  {
    Structure currentStructure = modelAccess.getCurrentStructure();
    StructureCollisionObjectDataModel currentStructureObject = modelAccess.getCurrentStructureObject();

    if (currentStructureObject.getBoundingBox() != null)
    {
      if (currentStructure.getPointList().size() > 1)
      {
        setBoundingBox(currentStructureObject);
        checkDirection(currentStructure);
      }
      modelAccess.getRaster().addToRaster(currentStructureObject.getBoundingBox(), modelAccess.getCollisionObjects().indexOf(currentStructureObject));
      closeStructure();
      return currentStructureObject;
    }

    return null;
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
  public ObservableList2<Point> checkAllCollsions(Vector2D pVector)
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
  public ObservableList2<Point> findPath(Point pStartPoint, Point pEndPoint, int pRadius)
  {
    ObservableList2<Point> pointList = new ObservableList2<>();
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

  public MapModelAccess getModelAccess()
  {
    return modelAccess;
  }

  private void _fireChange(String pFieldName, Object pNewValue)
  {
    fireChange(this, pFieldName, MapDataModel.class, pNewValue);
  }

  public BufferedImage getBackgroundImage()
  {
    return modelAccess.getBackgroundImage();
  }

  public void setBackgroundImage(BufferedImage pImage)
  {
    modelAccess.setBackgroundImage(pImage);
  }
}
