package de.swat.common.stages;

import com.badlogic.gdx.scenes.scene2d.Stage;
import de.swat.exceptions.SwatRuntimeException;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

/**
 * StageHandler, der alle Stages des Games verwaltet.
 * Struktur:
 * <p/>
 * - Background
 * -- Child1
 * -- Child2
 * -- Child3
 * -- ChildN
 * <p/>
 * - Midground
 * -- Child1
 * -- Child2
 * -- Child3
 * -- ChildN
 * <p/>
 * - Controlstage
 * <p/>
 * - Foreground
 * -- Child1
 * -- Child2
 * -- Child3
 * -- ChildN
 *
 * @author W.Glanzer, 31.05.2014.
 */
public class StageHandler
{

  private static final Set<IStageChangeListener> listeners = new HashSet<>();
  public static StageGroup BACKGROUND = new StageGroup();
  public static StageGroup MIDGROUND = new StageGroup();
  public static StageGroup FOREGROUND = new StageGroup();
  public static AbstractStage CONTROLSTAGE = null;

  /**
   * Fügt eine Stage der übergebenen Kategorie hinzu.
   *
   * @param pType  Kategorie, in der die Stage übernommen werden soll
   * @param pStage Stage, die hinzugefügt werden soll, <tt>null</tt> zum Löschen der Gruppe
   * @deprecated AbstractStage#addToHandler() benutzen
   * @see de.swat.common.stages.AbstractStage#addToHandler(de.swat.common.stages.StageHandler.StageType, int)
   */
  @Deprecated
  public static void addStage(StageType pType, AbstractStage pStage)
  {
    StageGroup group = null;

    // Falls die Stage null ist, dann wird dieser Typ von Stage gelöscht
    if(pStage == null)
    {
      if(pType.equals(StageType.CONTROLSTAGE))
        CONTROLSTAGE = null;
      else
        convertTypeToStage(pType).clear();

      return;
    }

    if(!(pStage instanceof StageGroup) && !(pType.equals(StageType.CONTROLSTAGE)))
      throw new SwatRuntimeException("A normal Stage cannot be set to " + pType, null);
    else if(pStage instanceof StageGroup)
      group = (StageGroup) pStage;

    switch(pType)
    {
      case BACKGROUND:
        BACKGROUND = group;
        break;

      case MIDGROUND:
        MIDGROUND = group;
        break;

      case FOREGROUND:
        FOREGROUND = group;
        break;

      case CONTROLSTAGE:
        CONTROLSTAGE = pStage;
        break;
    }

    _fireStageChanged(pType);
  }

  /**
   * Fügt einen Listener hinzu, der auf Veränderungen im StageHandler horcht
   *
   * @param pListener Listener, der hinzugefügt wird
   */
  public static void addStageChangeListener(@NotNull IStageChangeListener pListener)
  {
    synchronized(listeners)
    {
      listeners.add(pListener);
    }
  }

  /**
   * Gibt die Gruppe von Stages zurück, dessen Kategorie übergeben wurde
   *
   * @param pType Stagekategorie
   * @return Entweder StageGroup, oder AbstractStage
   */
  public static AbstractStage convertTypeToStage(StageType pType)
  {
    switch(pType)
    {
      case BACKGROUND:
        return BACKGROUND;

      default:
      case MIDGROUND:
        return MIDGROUND;

      case FOREGROUND:
        return FOREGROUND;

      case CONTROLSTAGE:
        return CONTROLSTAGE;
    }
  }

  private static void _fireStageChanged(StageType pStage)
  {
    synchronized(listeners)
    {
      Stage stage = convertTypeToStage(pStage);
      for(IStageChangeListener currListener : listeners)
        currListener.stageChanged(pStage, stage);
    }
  }

  /**
   * Typen, die Stages annehmen können
   */
  public static enum StageType
  {
    BACKGROUND,
    MIDGROUND,
    FOREGROUND,
    CONTROLSTAGE
  }
}
