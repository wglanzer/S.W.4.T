package de.swat;

import de.swat.accesses.MapCreatorModelAccess;
import de.swat.dataModels.Map.AbstractCollisionObjectDataModel;
import de.swat.observableList2.ObservableList2;
import javafx.collections.*;
import org.jetbrains.annotations.NotNull;

import javax.swing.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Stellt dem MapCreator eine Map zur Verfügung.
 * Diese beinhaltet mehr Funktionen, als die normale Map.
 * Funktionen, die aber im Game nicht gebraucht werden.
 *
 * @author W. Glanzer, 23.02.14
 */
public class MapCreatorMap extends Map
{
  @SuppressWarnings({"UnusedDeclaration", "FieldCanBeLocal"})
  private final MapCreatorModelAccess modelAccess;
  private ObservableList2<Point> clickedPoints = new ObservableList2<>();
  private ObservableList2<AbstractCollisionObjectDataModel> structureRectangles = new ObservableList2<>();
  private BufferedImage backgroundimage = null;
  private ObservableList2<Point> collisionPoints = new ObservableList2<>();

  public MapCreatorMap(MapCreatorModelAccess pModelAccess)
  {
    this(pModelAccess, new Map());
  }

  public MapCreatorMap(MapCreatorModelAccess pModelAccess, @NotNull Map pMap)
  {
    super(pMap.getModelAccess());
    modelAccess = pModelAccess;

    //Wenn sich in den Listen intern was ändert, wird hier benachrichtigt
    clickedPoints.addListDataListener(new _Listener("clickedPoints"));
    structureRectangles.addListDataListener(new _Listener("structureRectangles"));
    collisionPoints.addListDataListener(new _Listener("collisionPoints"));
  }

  /**
   * Gibt das Hintergrundbild der Map zurück
   *
   * @return Hintergrundbild
   */
  public BufferedImage getBackgroundimage()
  {
    return backgroundimage;
  }

  /**
   * Setzt das Hintergrundbild der Map, und benachrichtigt
   * über die Änderungen per ChangeListener
   *
   * @param pBackgroundimage Neues Hintergrundbild
   */
  public void setBackgroundimage(BufferedImage pBackgroundimage)
  {
    backgroundimage = pBackgroundimage;
    _fireChangeEvent("backgroundimage", backgroundimage);
  }

  /**
   * Gibt die CollisionPoints zurück
   *
   * @return CollisionPoints
   */
  public ObservableList2<Point> getCollisionPoints()
  {
    return collisionPoints;
  }

  /**
   * Setzt die CollisionPoints und benachrichtigt per Listener
   * über die Änderung
   *
   * @param pCollisionPoints CollisionPoints
   */
  public void setCollisionPoints(ObservableList2<Point> pCollisionPoints)
  {
    collisionPoints = pCollisionPoints;
    _fireChangeEvent("collisionPoints", backgroundimage);
  }

  /**
   * Liefert die StructureRectangles zurück
   *
   * @return StructureRectangles
   */
  public ObservableList2<AbstractCollisionObjectDataModel> getStructureRectangles()
  {
    return structureRectangles;
  }

  /**
   * Liefert die clickedPoints
   *
   * @return clickedPoints
   */
  public ObservableList2<Point> getClickedPoints()
  {
    return clickedPoints;
  }

  /**
   * Listener, der dafür zuständig ist zu benachrichtigen,
   * wenn sich in den ArrayListen intern irgendwas ändert.
   * D.h. wenn ein .add(), etc. aufgerufen wird.
   * Ansonsten, wenn einfach ein setClickedPoints() o.Ä.
   * aufgerufen wird, wird einfach die _fireChangeEvent aufgerufen
   */
  private class _Listener implements ListDataListener
  {
    private final String fieldName;

    private _Listener(String pFieldName)
    {
      fieldName = pFieldName;
    }

    @Override
    public void intervalAdded(ListDataEvent e)
    {
      _fireChangeEvent(fieldName, e.getSource());
    }

    @Override
    public void intervalRemoved(ListDataEvent e)
    {
      _fireChangeEvent(fieldName, e.getSource());
    }

    @Override
    public void contentsChanged(ListDataEvent e)
    {
      _fireChangeEvent(fieldName, e.getSource());
    }
  }

  private void _fireChangeEvent(String fieldName, Object pNewValue)
  {
    fireChange(MapCreatorMap.this, fieldName, MapCreatorMap.class, pNewValue);
  }
}
