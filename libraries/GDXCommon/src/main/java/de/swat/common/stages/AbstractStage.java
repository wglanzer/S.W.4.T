package de.swat.common.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.common.gui.assets.IAssets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Abstrakte Stage-Klasse, die die allgemeinen Funktionen der Stage erweitert
 *
 * @author W.Glanzer, 03.05.2014.
 */
public class AbstractStage extends Stage
{

  /**
   * Logger, der in allen Stages gleich verwendet werden kann.
   */
  protected static final Logger logger = LogManager.getLogger();
  /**
   * Einfache Referenz auf die Assets, dass nicht immer über die CorePreferences gegangen werden muss
   */
  protected static final IAssets assets = CorePreferences.getAssets();
  /**
   * Die aktuelle GUI/HUD-Stage, abhängig vom OS (Android/PC)
   */
  private static AbstractStage guiStage = null;
  /**
   * Gibt an, ob diese Stage sichtbar, oder unsichtbar ist.
   */
  private boolean isVisible = true;
  /**
   * Ist diese Stage ein Background, Midground, oder Foreground? <tt>null</tt>, wenn noch nicht hinzugefügt
   */
  private StageHandler.StageType type;

  public AbstractStage()
  {
  }

  /**
   * Liefert die GuiStage des aktuellen OS.
   * Unterscheidet derzeit zwischen Android und PC.
   *
   * @return Stage für die Gui/HUD
   */
  public static AbstractStage getGuiStage()
  {
    return guiStage;
  }

  /**
   * Setzt die GuiStage.
   *
   * @param pGuiStage Stage, die die GuiStage repräsentiert
   */
  public static void setGuiStage(AbstractStage pGuiStage)
  {
    guiStage = pGuiStage;
  }

  /**
   * Zum StageHandler hinzufügen. Falls die Stage schon hinzugefügt wurde,
   * wird sie zuerst vom StageHandler entfernt
   *
   * @param pType Typ, den die Stage haben soll.
   * @see de.swat.common.stages.AbstractStage#addToHandler(de.swat.common.stages.StageHandler.StageType, int)
   */
  public void addToHandler(@NotNull StageHandler.StageType pType)
  {
    type = pType;
    addToHandler(pType, -1);
  }

  /**
   * Zum StageHandler hinzufügen. Falls die Stage schon hinzugefügt wurde,
   * wird sie zuerst vom StageHandler entfernt und danach zum aktuellen Typ
   * wieder hinzugefügt
   *
   * @param pType  Typ, den die Stage haben soll
   * @param pIndex Stelle, an der die Stage eingefügt werden soll
   */
  public void addToHandler(StageHandler.StageType pType, int pIndex)
  {
    if(type != null)
      removeFromHandler();

    type = pType;
    AbstractStage toStage = StageHandler.convertTypeToStage(pType);

    if(pType.equals(StageHandler.StageType.CONTROLSTAGE))
      StageHandler.addStage(StageHandler.StageType.CONTROLSTAGE, this);
    else
      ((StageGroup) toStage).addStage(this, pIndex);
  }

  /**
   * Entfernt die Stage aus dem StageHandler, sodass sie nicht mehr aktualisiert und
   * gerendert wird.
   */
  public void removeFromHandler()
  {
    if(type.equals(StageHandler.StageType.CONTROLSTAGE))
      StageHandler.addStage(StageHandler.StageType.CONTROLSTAGE, null);
    else
      ((StageGroup) StageHandler.convertTypeToStage(type)).removeStage(this);

    type = null;
  }

  /**
   * @return Gibt an, ob diese Stagle zum Handler hinzugefügt ist (wird die Stage
   * bereits gerendert bzw. aktualisiert?). Dazu wird der type verwendet und geprüft,
   * ob dieser <tt>null</tt> ist.
   */
  public boolean isAddedToHandler()
  {
    return type != null;
  }

  /**
   * Wird aufgerufen, wenn sich die Größe der Stage verändern soll
   *
   * @param pWidth  Neue Breite
   * @param pHeight Neue Höhe
   */
  public void resize(int pWidth, int pHeight)
  {
  }

  /**
   * Wird aufgerufen, wenn sich die Stage rendern soll
   */
  public void render()
  {
    if(isVisible)
      draw();
  }

  /**
   * Updated die Stage.
   * Hier sollen alle Berechnungen stattfinden.
   * Es wird <tt>act()</tt> verwendet.
   *
   * @param pDelta Delta-Time
   */
  public void update(float pDelta)
  {
    if(isVisible)
      act(pDelta);
  }

  /**
   * Synonym zu <tt>setVisible(false)</tt>. Setzt die Sichtbarkeit
   * der Stage auf unsichtbar
   *
   * @see de.swat.common.stages.AbstractStage#setVisible(boolean)
   */
  public void hide()
  {
    setVisible(false);
  }

  @Override
  public void dispose()
  {
    removeFromHandler();
    super.dispose();
  }

  /**
   * Gibt an, ob die aktuelle Stage sichtbar ist
   *
   * @return Sichtbarkeit
   */
  public boolean isVisible()
  {
    return isVisible;
  }

  /**
   * Setzt, ob diese Stage sichtbar ist, oder nicht
   *
   * @param pIsVisible Sichtbarkeit
   */
  public void setVisible(boolean pIsVisible)
  {
    isVisible = pIsVisible;
  }
}
