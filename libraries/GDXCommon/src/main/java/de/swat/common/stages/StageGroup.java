package de.swat.common.stages;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Gruppe aus Stages
 *
 * @author W.Glanzer, 31.05.2014.
 */
public class StageGroup extends AbstractStage
{

  /**
   * Kinder der Gruppe. Alle Stages, die die Gruppe enthält
   */
  private final List<AbstractStage> children = new ArrayList<>();

  public StageGroup(AbstractStage... pStage)
  {
    children.addAll(Arrays.asList(pStage));
  }

  /**
   * Fügt eine Stage an einer bestimmten Position dieser Gruppe hinzu
   *
   * @param pStage  Stage, die hinzugefügt werden soll
   * @param pIndex  Position, an der die Stage hinzugefügt werden soll, <tt>-1</tt> ignoriert die Position
   */
  public void addStage(@NotNull AbstractStage pStage, int pIndex)
  {
    if(pIndex > -1)
      children.add(pIndex, pStage);
    else
      children.add(pStage);
  }

  /**
   * Löscht eine Stage aus der Gruppe
   *
   * @param pStage  Stage, die gelöscht werden soll
   */
  public void removeStage(@NotNull AbstractStage pStage)
  {
    children.remove(pStage);
  }

  /**
   * Entfernt alle Stages
   */
  public void clear()
  {
    children.clear();
  }

  @Override
  public void resize(int pWidth, int pHeight)
  {
    for(AbstractStage currStage : children)
      currStage.resize(pWidth, pHeight);
  }

  @Override
  public void render()
  {
    for(AbstractStage currStage : children)
      currStage.render();
  }

  @Override
  public void update(float pDelta)
  {
    for(AbstractStage currStage : children)
      currStage.update(pDelta);
  }
}
