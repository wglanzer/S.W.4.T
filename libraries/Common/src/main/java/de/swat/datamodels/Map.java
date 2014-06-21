package de.swat.datamodels;

import de.swat.datamodels.map.AbstractCollisionObjectDataModel;
import de.swat.datamodels.map.Structure;
import de.swat.datamodels.map.StructureCollisionObjectDataModel;
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

  protected ObservableList2<AbstractCollisionObjectDataModel> structureRectangles = new ObservableList2<>();
  /**
   * BufferedImage, das das Hintergrundbild der Map
   * repräsentiert.
   */
  private BufferedImage backgroundImage;
  /**
   * 2-Dimensionales Array, welches über x und y Koordinate
   * eines Rasters auf eine Liste mit den Indices der im
   * jeweiligen Rasterenthaltenen Strukturen zugreifen lässt
   */
  private Raster raster;
  /**
   * Ungeordnete Liste aller Collisions-Objekte. Die Indices
   * kommen von der ArrayList eines Rasters.
   */
  private ObservableList2<AbstractCollisionObjectDataModel> collisionObjects = new ObservableList2<>();
  /**
   * Attribute, die nur für das hinzufügen von Strukturen im MapCreator gebraucht werden
   */
  private StructureCollisionObjectDataModel currentStructureObject;
  private Structure currentStructure;

  public Map()
  {
  }

  /**
   * Fügt dem aktuell im Fokus stehenden Strukturobjekt
   * einen neuen Punkt hinzu.
   *
   * @param pPoint Die Punkte, die hinzugefügt werden sollen
   */
  public void addPoints(ObservableList2<Point> pPoint)
  {
    if (currentStructure == null)
    {
      newStructure();
    }
    currentStructure.getPointList().addAll(pPoint);  //Hinzufügen des Punktes zum Structure
    fireChange(this, "currentStructure", Map.class, currentStructure);
  }

  /**
   * Erstellen eines neuen Kollision Objektes
   */
  private void newStructure()
  {
    Structure newStructure = new Structure();
    StructureCollisionObjectDataModel newStructureObject = new StructureCollisionObjectDataModel();
    currentStructure = newStructure;
    currentStructureObject = newStructureObject;
    newStructureObject.setStructure(newStructure);
    collisionObjects.add(newStructureObject);
  }

  /**
   * Schließt eine Struktur
   */
  private void closeStructure()
  {
    //Schließen eines Structures
    currentStructureObject = null;
    currentStructure = null;
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
    return collisionObjects;
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
    if (currentStructureObject.getBoundingBox() != null)
    {
      if (currentStructure.getPointList().size() > 1)
      {
        setBoundingBox(currentStructureObject);
        checkDirection(currentStructure);
      }
      raster.addToRaster(currentStructureObject.getBoundingBox(), collisionObjects.indexOf(currentStructureObject));
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
    return raster.checkFirstCollision(pVector);
  }

  /**
   * Überprüft alle Kollisiinen eines Vektors mit der Welt und gib diese als Arraylist von Punkten zurück
   *
   * @param pVector Kollisionsvektor
   * @return Kollisionspunkte
   */
  public ObservableList2<Point> checkAllCollsions(Vector2D pVector)
  {
    return raster.checkAllCollisions(pVector);
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
    return raster.findPath(pStartPoint, pEndPoint, pRadius, new ArrayList<Integer>(), pointList);
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
    return raster.getInteractableFromMousePosition(pMouseX, pMouseY);
  }

  /**
   * Berechnet alle BoundingBoxen neu
   */
  public void recalculateBoundingBoxes()
  {
    for (AbstractCollisionObjectDataModel currCollisionObject : collisionObjects)
      currCollisionObject.setBoundingBox();
  }

  private void _fireChange(String pFieldName, Object pNewValue)
  {
    fireChange(this, pFieldName, Map.class, pNewValue);
  }

  public BufferedImage getBackgroundImage()
  {
    return backgroundImage;
  }

  public void setBackgroundImage(BufferedImage pImage)
  {
    backgroundImage = pImage;
  }

  public void setRaster(Raster pRaster)
  {
    raster = pRaster;
  }

  public ObservableList2<AbstractCollisionObjectDataModel> getCollisionObjects()
  {
    return collisionObjects;
  }
}
