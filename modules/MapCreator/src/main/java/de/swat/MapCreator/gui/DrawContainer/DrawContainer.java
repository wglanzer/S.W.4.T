package de.swat.MapCreator.gui.DrawContainer;

import de.swat.Map;
import de.swat.MapCreator.brushes.*;
import de.swat.dataModels.Map.*;
import de.swat.exceptions.SwatRuntimeException;
import de.swat.utils.PointUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Dieser Container kann DrawableImages darstellen
 * und auf Mausklicks reagieren.
 *
 * @author W. Glanzer, 28.11.2013
 */
public class DrawContainer extends JPanel
{
  private final Map map;
  private ArrayList<DrawableImage> pImagesToDraw = new ArrayList<>();
  private ArrayList<Point> clickedPoints = new ArrayList<>();
  private ArrayList<AbstractCollisionObjectDataModel> structureRectangles = new ArrayList<>();
  private BufferedImage backgroundimage = null;
  private ArrayList<Point> collisionPoints = new ArrayList<>();
  public int xOff = 0;
  public int yOff = 0;
  private boolean isInitialised = false;
  private boolean isBlocked = false;
  private Point actualMousePoint = new Point(0, 0);
  private IBrush actualBrush = new PointBrush();

  public DrawContainer(Map pMap)
  {
    map = pMap;
    setOpaque(true);
    setUI(new BasicPanelUI());
    setBackground(Color.BLACK);
    addMouseListener(new Mouse());
    addMouseMotionListener(new MouseMove());
    addComponentListener(new ComponentAdapter()
    {
      @Override
      public void componentResized(ComponentEvent e)
      {
        if (getHeight() > 0 && getWidth() > 0)
        {
          removeComponentListener(this);
          isInitialised = true;
        }
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    if (backgroundimage != null)
      g.drawImage(backgroundimage, -xOff, -yOff, null);

    /*Geht die Images durch, die gezeichnet werden sollen*/
    for (DrawableImage currImage : pImagesToDraw)
      g.drawImage(currImage.image, currImage.x - xOff, currImage.y - yOff, null);

    /*Zeichnet die Punkte, die bereits schon geklickt wurden. Ebenso eine Linie, die die letzen beiden Punkte verbindet*/
    for (int i = 0; i < clickedPoints.size(); i++)
    {
      if (i == 0)
        g.setColor(Color.RED);
      else
        g.setColor(Color.WHITE);

      Point currPoint = clickedPoints.get(i);
      g.drawRoundRect(currPoint.x - 4 - xOff, currPoint.y - 4 - yOff, 8, 8, 8, 8);
      if (i > 0)
      {
        Point lastPoint = clickedPoints.get(i - 1);
        g.setColor(Color.YELLOW);
        g.drawLine(lastPoint.x - xOff, lastPoint.y - yOff, currPoint.x - xOff, currPoint.y - yOff);
      }
    }

    /*Zeichnet die Kollisionspunkte*/
    g.setColor(Color.BLUE);
    for (Point currPoint : collisionPoints)
    {
      g.drawRoundRect(currPoint.x - 4 - xOff, currPoint.y - 4 - yOff, 8, 8, 8, 8);
    }

    /*Zeichnet die structureRectangles, die durch addStructureRectangle gesetzt werden*/
    for (AbstractCollisionObjectDataModel objectDataModel : structureRectangles)
    {
      if (objectDataModel instanceof StructureCollisionObjectDataModel)
      {
        StructureCollisionObjectDataModel currObj = (StructureCollisionObjectDataModel) objectDataModel;
        g.setColor(Color.PINK);
        Rectangle currRect = currObj.getBoundingBox();
        g.drawRect(currRect.x - xOff, currRect.y - yOff, currRect.width, currRect.height);

        g.setColor(Color.WHITE);
        Polygon poly = currObj.getStructure().getPolygon();
        Polygon secondPoly = new Polygon(poly.xpoints, poly.ypoints, poly.npoints);
        secondPoly.translate(-xOff, -yOff);
        g.drawPolygon(secondPoly);
      }
    }

    /*Aktuelle MousePosition*/
    g.setColor(Color.GREEN);
    g.drawRoundRect(actualMousePoint.x - 4, actualMousePoint.y - 4, 8, 8, 8, 8);

    /*Verbindungslinie*/
    if (clickedPoints.size() > 0)
    {
      g.setColor(Color.CYAN);
      Point lastPoint = clickedPoints.get(clickedPoints.size() - 1);
      g.drawLine(lastPoint.x - xOff, lastPoint.y - yOff, actualMousePoint.x, actualMousePoint.y);
    }
  }

  /**
   * Setzt dem DrawContainer ein Hintergrundbild, das
   * wahlweise auf die width und height des Containers
   * skaliert wird.
   *
   * @param pImage           Hintergrundbild als BufferedImage
   * @param pShouldBeResized Soll das Bild resized werden?
   */
  public void setBackgroundimage(@Nullable BufferedImage pImage, boolean pShouldBeResized)
  {
    if (pImage == null)
      return;

    if (pShouldBeResized)
      try
      {
        backgroundimage = Thumbnails.of(pImage).size(getWidth(), getHeight()).asBufferedImage();
      }
      catch (IOException e)
      {
        throw new SwatRuntimeException("Background image could not be set! Error in module 'Thumbnails'", e);
      }
    else
      backgroundimage = pImage;

    repaint();
  }

  /**
   * Zeichnet ein Bild an eine bestimmte Stelle im Container.
   * Die Koordinaten müssen relativ zum Panel angegeben werden.
   *
   * @param pX X-Koordinate
   * @param pY Y-Koordinate
   */
  public void drawPicAt(@Nullable BufferedImage pImage, int pID, int pX, int pY)
  {
    if (pImage == null)
      return;

    DrawableImage picToAdd = new DrawableImage(pImage, pID, pX, pY);
    pImagesToDraw.add(picToAdd);
    repaint();
  }

  /**
   * Liefert die aktuellen Punkte, die gedrückt wurden und
   * löscht danach die Liste der Punkte, sodass wieder
   * neue Punkte hinzugefügt werden können und keine
   * Überreste überbleiben
   *
   * @return ClickedPoints
   */
  public ArrayList<Point> getClickedPoints()
  {
    //TODO sollte evtl. bei Zeiten schöner gemacht werden
    ArrayList<Point> returnList = new ArrayList<>();
    returnList.addAll(clickedPoints);
    clickedPoints.clear();
    return returnList;
  }

  /**
   * Leert die clickedPoints
   */
  public void clearClickedPoints()
  {
    clickedPoints.clear();
    repaint();
  }

  /**
   * Fügt ein Rectangle in die structureRectangle-Liste ein,
   * damit es beim nächsten Repaint gezeichnet wird.
   * Diese sind für fertige Structures gedacht, damit diese
   * angezeigt werden.
   *
   * @param pStructureObject StructureCollisionObjectDataModel, das
   *                         die BoundingBox und das Polygon enthält,
   *                         die gezeichent werden sollen
   */
  public void addStructureObject(@Nullable StructureCollisionObjectDataModel pStructureObject)
  {
    if (pStructureObject != null)
      structureRectangles.add(pStructureObject);
  }

  private class MouseMove extends MouseMotionAdapter
  {
    @Override
    public void mouseMoved(MouseEvent e)
    {
      actualMousePoint = e.getPoint();
      repaint();
    }
  }


  /**
   * MouseAdapter, der hier verwendet wird. Dieser
   * dient nur zur Übersichtlichkeit.
   */
  private class Mouse extends MouseAdapter
  {
    @Override
    public void mouseReleased(MouseEvent e)
    {
      Point clickPoint = new Point(e.getX() + xOff, e.getY() + yOff);
      int mouseX = e.getX();
      int mouseY = e.getY();
      int proxRadius = 100; //TODO

      //Prüft, ob der Mausklick wirklich auf dem DrawContaienr stattgefunden hat, und nicht daneben
      if (mouseX > 0 && mouseY > 0 && mouseX <= getWidth() && mouseY <= getHeight())
      {
        switch (e.getButton())
        {
          /**LINKE MAUSTASTE*/
          case MouseEvent.BUTTON1:
            if (clickedPoints.size() > 1)
            {
              if (PointUtil.checkProximity(clickedPoints.get(0), clickPoint, proxRadius))
              {
                map.addPoints(clickedPoints);
                clearClickedPoints();
                StructureCollisionObjectDataModel newObject = map.finishStructure();
                addStructureObject(newObject);
                repaint();
              }
              else
              {
                actualBrush.drawBrush(clickedPoints, clickPoint);
              }
            }
            else
            {
              actualBrush.drawBrush(clickedPoints, clickPoint);
            }
            if (clickedPoints.size() > 1)
            {
              //  //Point possPoint = dataModel.checkFirstCollision(new Vector2D(new Point2D(clickedPoints.get(clickedPoints.size() - 2).x, clickedPoints.get(clickedPoints.size() - 2).y), new Point2D(clickedPoints.get(clickedPoints.size() - 1).x, clickedPoints.get(clickedPoints.size() - 1).y)));
              //  //if (possPoint != null)
              //  //  collisionPoints.add(possPoint);
              //

              //ArrayList<Point> possPoints = dataModel.checkAllCollsions(new Vector2D(new Point2D(clickedPoints.get(clickedPoints.size() - 2).x, clickedPoints.get(clickedPoints.size() - 2).y), new Point2D(clickedPoints.get(clickedPoints.size() - 1).x, clickedPoints.get(clickedPoints.size() - 1).y)));
              //for (int i = 0; i < possPoints.size(); i++)
              //{
              //  if (possPoints.get(i) != null)
              //    collisionPoints.add(possPoints.get(i));
              //}

              //if (clickedPoints.size() >= 3)
              //{
              //  Point edgePoint = PointUtil.getEdgePoint(clickedPoints.get(clickedPoints.size() - 1), clickedPoints.get(clickedPoints.size() - 2), clickedPoints.get(clickedPoints.size() - 3), 10);
              //  collisionPoints.add(edgePoint);
              //}

              if (clickedPoints.size() == 10)
              {
                collisionPoints = (map.findPath(new Point(100, 100), new Point(800, 800), 10));
              }
            }
            repaint();
            break;

          /**RECHTE MAUSTASTE*/
          case MouseEvent.BUTTON3:

            for (Point currPoint : clickedPoints)
            {
              if (PointUtil.checkProximity(currPoint, clickPoint, proxRadius))
              {
                clickedPoints.remove(currPoint);
                repaint();
                break;
              }
            }

            break;
        }
      }
    }
  }

  /**
   * Leert die clickedPoints und setzt das MapDataModel
   * auf ein neues Model, sodass alle Änderungen verworfen
   * werden.
   */
  public void clearAll()
  {
    ////TODO Sicherheitsabfrage
    //dataModel.setNewModel(new MapDataModel());
    //reloadFromDataModel();
    throw new RuntimeException("Not implemented yet!");
  }

  /**
   * Setzt das YOffset. Man kann variieren,
   * jenachdem ob man als 2.Parameter <code>true</code>
   * oder <code>false</code> angibt, fügt man den
   * angegebenen Wert dem yOffset hinzu oder man
   * überschreibt es ganz
   *
   * @param pYOff Wert des (hinzugefügten) yOffsets
   * @param pAdd  <code>true</code>, wenn dem yOffset die Zahl
   *              hinzugefügt werden soll.
   */
  public void setYOff(int pYOff, boolean pAdd)
  {
    if (pAdd)
      yOff += yOff + pYOff >= 0 ? pYOff : 0;
    else
      yOff = yOff + pYOff >= 0 ? pYOff : 0;
    repaint();
  }

  /**
   * Selbes wie <code>setYOff</code>, nur für das
   * xOffset
   *
   * @see de.swat.MapCreator.gui.DrawContainer.DrawContainer#setYOff(int, boolean)
   */
  public void setXOff(int pXOff, boolean pAdd)
  {
    if (pAdd)
      xOff += xOff + pXOff >= 0 ? pXOff : 0;
    else
      xOff = xOff + pXOff >= 0 ? pXOff : 0;
    repaint();
  }

  /**
   * @return ob das Objekt schon initialisiert wurde. Dies passiert, wenn
   *         die Komponente das erste mal vom LayoutManager angefasst wurde, und somit
   *         der ComponentListener sich zu Wort meldet. Dieser wird danach entfernt, und
   *         isInitialised auf <code>true</code> gesetzt.
   */
  public boolean isInitialised()
  {
    return isInitialised;
  }

  /**
   * @return Liefert zurück, ob das Objekt durch eine andere Aktion
   *         blockiert werden soll. Somit wird bspw. der MouseMoveOffsetThread
   *         geblockt.
   */
  public boolean isBlocked()
  {
    return isBlocked;
  }

  public void setBlocked(boolean pIsBlocked)
  {
    isBlocked = pIsBlocked;
  }

  //public void reloadFromDataModel()
  //{
  //  pImagesToDraw.clear();
  //  clickedPoints.clear();
  //  structureRectangles = map.getCollisionObjects();
  //  backgroundimage = null;
  //  collisionPoints.clear();
  //  xOff = 0;
  //  yOff = 0;
  //}
}
