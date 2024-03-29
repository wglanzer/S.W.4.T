package de.swat.mapCreator.gui.components.drawContainer;

import de.swat.common.map.Map;
import de.swat.mapCreator.GlobalKeyListenerManager;
import de.swat.mapCreator.brushes.IBrush;
import de.swat.mapCreator.brushes.PointBrush;
import de.swat.ObservableList2;
import de.swat.utils.MathUtil;
import de.swat.utils.PointUtil;
import de.swat.utils.PredefinedParameterUtil;

import javax.swing.*;
import javax.swing.plaf.basic.BasicPanelUI;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

/**
 * Dieser Container kann DrawableImages darstellen
 * und auf Mausklicks reagieren.
 *
 * @author W. Glanzer, 28.11.2013
 */
public class DrawContainer extends JPanel
{
  public int xOff = 0;
  public int yOff = 0;
  private Map map;
  private Point actualMousePoint = new Point(0, 0);
  private IBrush actualBrush = new PointBrush();
  private EDrawState state = EDrawState.MOUSE;

  /**
   * Hier kommen Daten, die nicht unbedingt ins Datenmodell
   * mitaufgenommen werden müssen, jedoch im DrawContainer
   * eine wichtige Rolle spielen
   */
  private ObservableList2<Point> clickedPoints = new ObservableList2<>();
  private ObservableList2<Point> collisionPoints = new ObservableList2<>();
  private float zoomFactor = 1.0f;

  public DrawContainer(Map pMap)
  {
    map = pMap;
    setOpaque(true);
    setUI(new BasicPanelUI());
    setBackground(Color.BLACK);
    Mouse mouse = new Mouse();
    addMouseListener(mouse);
    addMouseMotionListener(mouse);
    addMouseWheelListener(mouse);
    _setListeners();
  }

  public void mapChanged(Map pNewMap)
  {
    map = pNewMap;
    reloadFromDataModel();
    SwingUtilities.invokeLater(new Runnable()
    {
      @Override
      public void run()
      {
        repaint();
      }
    });
  }

  private void _setListeners()
  {
//    map.addFieldChangeListener(new IFieldChangeListener()
//    {
//      @Override
//      public void fieldChanged(FieldChangeObject pFieldObject)
//      {
//        repaint();
//      }
//    });

    GlobalKeyListenerManager.getDefault().addKeyEventDispatcher(new KeyEventDispatcher()
    {
      @Override
      public boolean dispatchKeyEvent(KeyEvent e)
      {
        if (e.getKeyCode() == KeyEvent.VK_CONTROL)
          state = e.getID() == KeyEvent.KEY_PRESSED ? EDrawState.SETPOINTS : EDrawState.MOUSE;

        SwingUtilities.invokeLater(new Runnable()
        {
          @Override
          public void run()
          {
            repaint();
          }
        });
        return false;
      }
    });
  }

  @Override
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);

//    BufferedImage bgImage = map.getBackgroundImage();
//    if (bgImage != null)
//      g.drawImage(bgImage, -xOff, -yOff, (int) (bgImage.getWidth() * zoomFactor), (int) (bgImage.getHeight() * zoomFactor), null);

    //Hier werden evtl. DebugAusgaben am linken, oberen Eck gerendert
    if (PredefinedParameterUtil.isDebugMode())
    {
      g.setColor(Color.WHITE);
      g.drawString("ZoomFactor: " + zoomFactor, 5, g.getFontMetrics().getHeight() + 5);
    }

    /*Zeichnet die Punkte, die bereits schon geklickt wurden. Ebenso eine Linie, die die letzen beiden Punkte verbindet*/
    for (int i = 0; i < clickedPoints.size(); i++)
    {
      if (i == 0)
        g.setColor(Color.RED);
      else
        g.setColor(Color.WHITE);

      Point currPoint = clickedPoints.get(i);
      g.drawRoundRect((int) ((currPoint.x - 4 - xOff) * zoomFactor), (int) ((currPoint.y - 4 - yOff) * zoomFactor), 8, 8, 8, 8);
      if (i > 0)
      {
        Point lastPoint = clickedPoints.get(i - 1);

        /*Wählt die Farbe aus, die die Verbindungslinie haben soll und zeichnet diese anschließend*/
        g.setColor(Color.YELLOW);
        Graphics2D g2 = (Graphics2D) g;
        Stroke oldStroke = g2.getStroke();
        if (!(state == EDrawState.SETPOINTS))
        {
          Color lightGray = Color.LIGHT_GRAY;
          g2.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{5}, 0));
          g2.setColor(new Color(lightGray.getRed(), lightGray.getGreen(), lightGray.getBlue(), 100));
        }
        g2.drawLine((int) ((lastPoint.x - xOff) * zoomFactor), (int) ((lastPoint.y - yOff) * zoomFactor), (int) ((currPoint.x - xOff) * zoomFactor), (int) ((currPoint.y - yOff) * zoomFactor));
        g2.setStroke(oldStroke);
      }
    }

    /*Zeichnet die Kollisionspunkte*/
    g.setColor(Color.BLUE);
    for (Point currPoint : collisionPoints)
      g.drawRoundRect((int) ((currPoint.x - 4 - xOff) * zoomFactor), (int) ((currPoint.y - 4 - yOff) * zoomFactor), 8, 8, 8, 8);

    /*Zeichnet die structureRectangles, die durch addStructureRectangle gesetzt werden*/
//    for (AbstractCollisionObjectDataModel objectDataModel : map.getStructureRectangles())
//    {
//      if (objectDataModel instanceof StructureCollisionObjectDataModel)
//      {
//        StructureCollisionObjectDataModel currObj = (StructureCollisionObjectDataModel) objectDataModel;
//        if (PredefinedParameterUtil.isDebugMode())
//        {
//          g.setColor(Color.PINK);
//          Rectangle currRect = currObj.getBoundingBox();
//          if (currRect != null)
//            g.drawRect((int) ((currRect.x - xOff) * zoomFactor), (int) ((currRect.y - yOff) * zoomFactor), (int) ((currRect.width) * zoomFactor), (int) ((currRect.height) * zoomFactor));
//        }
//
//        g.setColor(Color.WHITE);
//        Polygon poly = currObj.getStructure().getPolygon();
//        Polygon secondPoly = new Polygon(poly.xpoints, poly.ypoints, poly.npoints);
//        secondPoly.translate(-xOff, -yOff);
//        g.drawPolygon(MathUtil.zoomPolygon(secondPoly, zoomFactor));
//      }
//    }

    if (state == EDrawState.SETPOINTS)
    {
      /*Aktuelle MousePosition*/
      g.setColor(Color.GREEN);
      g.drawRoundRect(actualMousePoint.x - 4, actualMousePoint.y - 4, 8, 8, 8, 8);

      /*Verbindungslinie*/
      if (clickedPoints.size() > 0)
      {
        g.setColor(Color.CYAN);
        Point lastPoint = clickedPoints.get(clickedPoints.size() - 1);
        g.drawLine((int) ((lastPoint.x - xOff) * zoomFactor), (int) ((lastPoint.y - yOff) * zoomFactor), actualMousePoint.x, actualMousePoint.y);
      }
    }
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
//  public void addStructureObject(@Nullable StructureCollisionObjectDataModel pStructureObject)
//  {
//    if (pStructureObject != null)
//      map.getStructureRectangles().add(pStructureObject);
//  }

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
   * @see de.swat.mapCreator.gui.components.drawContainer.DrawContainer#setYOff(int, boolean)
   */
  public void setXOff(int pXOff, boolean pAdd)
  {
    if (pAdd)
      xOff += xOff + pXOff >= 0 ? pXOff : 0;
    else
      xOff = xOff + pXOff >= 0 ? pXOff : 0;
    repaint();
  }

  public ObservableList2<Point> getClickedPoints()
  {
    return clickedPoints;
  }

  public void reloadFromDataModel()
  {
    clickedPoints.clear();
    collisionPoints.clear();
    xOff = 0;
    yOff = 0;
  }

  /**
   * MouseAdapter, der hier verwendet wird. Dieser
   * dient nur zur Übersichtlichkeit.
   */
  private class Mouse extends MouseAdapter
  {
    private static final float SCROLLFACTOR = 0.05f;
    private Point oldDragPoint;

    @Override
    public void mousePressed(MouseEvent e)
    {
      oldDragPoint = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
      oldDragPoint = null;

      Point clickPoint = new Point((int) ((e.getX() + xOff) / zoomFactor), (int) ((e.getY() + yOff) / zoomFactor));
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
            if (state == EDrawState.SETPOINTS)
            {
              if (clickedPoints.size() > 1)
              {
                if (PointUtil.checkProximity(clickedPoints.get(0), clickPoint, proxRadius))
                {
//                  map.addPoints(clickedPoints);
//                  clearClickedPoints();
//                  StructureCollisionObjectDataModel newObject = map.finishStructure();
//                  addStructureObject(newObject);
//                  repaint();
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

//              if (clickedPoints.size() == 10)
//                collisionPoints = map.findPath(new Point(100, 100), new Point(800, 800), 10);
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

    @Override
    public void mouseWheelMoved(MouseWheelEvent e)
    {
      double zoomOut = e.getPreciseWheelRotation() * -1;
      zoomFactor += zoomOut * SCROLLFACTOR != 0 ? zoomOut * SCROLLFACTOR : 0;  //Kann zwar nicht vorkommen, dass zoomOut * SCROLLFACTOR 0 ist, aber zur vorosorge die Abfrage
      zoomFactor = MathUtil.round2Decimals(zoomFactor);
      repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
      if (state == EDrawState.MOUSE)
      {
        //Point delta = new Point((int) ((oldDragPoint.x - e.getPoint().x) * zoomFactor), (int) ((oldDragPoint.y - e.getPoint().y) * zoomFactor));
        Point delta = new Point(oldDragPoint.x - e.getPoint().x, oldDragPoint.y - e.getPoint().y);
        xOff += xOff + delta.x > 0 ? delta.x : 0;
        yOff += yOff + delta.y > 0 ? delta.y : 0;
        oldDragPoint = e.getPoint();
        repaint();
      }
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
      actualMousePoint = e.getPoint();
      repaint();
    }
  }
}
