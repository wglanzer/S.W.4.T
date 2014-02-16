package de.swat.core.Entity;

import de.swat.core.*;
import de.swat.core.Utility.*;
//import org.newdawn.slick.*;
//import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Image;

import java.awt.*;

/**
 * @author Werner Glanzer, 02.10.13
 */
public class PlayerEntity extends Entity
{
  //public Animation spriteFeet;
  //public Animation spriteBody;
  private int size;
  private float bodyRotation = 0;
  private float feetRotation = 0;

  private int startFrame = 0;
  private int endFrame = 0;

  public PlayerEntity(int pStartPosX, int pStartPosY, int pSize)
  {
    //super(DefaultSettingsCallback.SPRITESHEET_PATH + "PlayerSheet.png", pSize, pSize);
    posX = pStartPosX;
    posY = pStartPosY;
    size = pSize;
    _initSprites();
  }

  private void _initSprites()
  {
    //spriteFeet = new Animation(ConvertUtils.combineImageArrays(SWATUtilities.getImageRow(spriteSheet.getSubImages(), 0), SWATUtilities.getImageRow(spriteSheet.getSubImages(), 1)), 150);
    //Image[][] sprites = spriteSheet.getSubImages();
    //Image[] row1 = SWATUtilities.getImageRow(sprites, 0);
    //Image[] row2 = SWATUtilities.getImageRow(sprites, 1);
    //Image[] row3 = SWATUtilities.getImageRow(sprites, 2);
    //Image[] row4 = SWATUtilities.getImageRow(sprites, 3);
    //Image[] row5 = SWATUtilities.getImageRow(sprites, 4);
    //spriteFeet = new Animation(ConvertUtils.combineImageArrays(row1, row2, row3, row4, row5), 150);
    //spriteFeet = new Animation(ConvertUtils.combineImageArrays(SWATUtilities.getImageRow(spriteSheet.getSubImages(), 0), SWATUtilities.getImageRow(spriteSheet.getSubImages(), 1)), 150);
    //spriteBody = new Animation(new Image[]{spriteSheet.getSubImage(0, 5)}, 1);
  }

  public void setRunspeed(int pRunspeed)
  {
    super.setRunspeed(pRunspeed);
  }

  public void move(int dx, int dy, int tpf)
  {
    if (dx != 0 && dy != 0)
    {
      posY += (renderRunspeed * (float) tpf) * (float) dy * 0.7;
      posX += (renderRunspeed * (float) tpf) * (float) dx * 0.7;
    }
    else
    {
      posY += (renderRunspeed * (float) tpf) * (float) dy;
      posX += (renderRunspeed * (float) tpf) * (float) dx;
    }
  }

  public void setFeetAnimation()
  {
    double deltaRotation = bodyRotation + feetRotation - 180;
    while (deltaRotation > 180)
      deltaRotation -= 360;

    //TODO: wird immer ausgeführt!
    if (315 < deltaRotation || (-45 < deltaRotation && deltaRotation < 45))
    {
      if (startFrame != DefaultSettingsCallback.PLAYER_SPRITES[0])
      {
        //spriteFeet.stop();
        startFrame = DefaultSettingsCallback.PLAYER_SPRITES[0];
        endFrame = DefaultSettingsCallback.PLAYER_SPRITES[1];
      }
    }
    else if (45 < deltaRotation && deltaRotation < 135)
    {
      if (startFrame != DefaultSettingsCallback.PLAYER_SPRITES[4])
      {
        //spriteFeet.stop();
        startFrame = DefaultSettingsCallback.PLAYER_SPRITES[4];
        endFrame = DefaultSettingsCallback.PLAYER_SPRITES[5];
      }
    }
    else if ((135 < deltaRotation || deltaRotation < -135))
    {
      if (startFrame != DefaultSettingsCallback.PLAYER_SPRITES[2])
      {
        //spriteFeet.stop();
        startFrame = DefaultSettingsCallback.PLAYER_SPRITES[2];
        endFrame = DefaultSettingsCallback.PLAYER_SPRITES[3];
      }
    }
    else if ((-135 < deltaRotation && deltaRotation < -45))
    {
      if (startFrame != DefaultSettingsCallback.PLAYER_SPRITES[6])
      {
        //spriteFeet.stop();
        startFrame = DefaultSettingsCallback.PLAYER_SPRITES[6];
        endFrame = DefaultSettingsCallback.PLAYER_SPRITES[7];
      }
    }

    //if (spriteFeet.getFrame() == endFrame)
    //  spriteFeet.stop();

    //if (spriteFeet.isStopped())
    {
      //spriteFeet.setCurrentFrame(startFrame);
      //spriteFeet.start();
    }
  }

  //TODO: auslagern
  public void rotateBodyToPoint(Point pPoint)
  {
    if (DefaultSettingsCallback.WINDOW_WIDTH / 2 - pPoint.x < 0)
      bodyRotation = 180 + (float) Math.toDegrees(Math.atan((double) (DefaultSettingsCallback.WINDOW_HEIGHT / 2 - pPoint.y) / (double) (pPoint.x - DefaultSettingsCallback.WINDOW_WIDTH / 2)));

    else if (DefaultSettingsCallback.WINDOW_WIDTH / 2 - pPoint.x > 0)
      bodyRotation = (float) Math.toDegrees(Math.atan((double) (pPoint.y - DefaultSettingsCallback.WINDOW_HEIGHT / 2) / (double) (DefaultSettingsCallback.WINDOW_WIDTH / 2 - pPoint.x)));

    else
    {
      if (DefaultSettingsCallback.WINDOW_HEIGHT / 2 - pPoint.y < 0)
        bodyRotation = 90F;

      else if (DefaultSettingsCallback.WINDOW_HEIGHT / 2 - pPoint.y > 0)
        bodyRotation = -90F;

    }

    if (bodyRotation < 0)
      bodyRotation += 360;

  }

  public void rotateFeetToMovement(int pDX, int pDY)
  {
    if (pDX == 0 && pDY == 0)
    {
      return;
    }
    else if (pDX == 0)
    {
      if (pDY == -1)
        feetRotation = 270;   //oben
      else
        feetRotation = 90;    //unten
    }
    else if (pDY == 0)
    {
      if (pDX == -1)
        feetRotation = 180;   //links
      else
        feetRotation = 0;     //rechts
    }

    else if (pDX == -1)
    {
      if (pDY == 1)
        feetRotation = 135;   //rechts-unten
      else
        feetRotation = 225;   //rechts-oben
    }
    else
    {
      if (pDY == 1)
        feetRotation = 45;    //links-unten
      else
        feetRotation = 315;   //links-oben
    }

  }

  public void update(int pDX, int pDY, int pMouseX, int pMouseY, int pTPF)
  {
    rotateBodyToPoint(new Point(pMouseX, pMouseY));

    if (pDX == 0 && pDY == 0)
    {
      //spriteFeet.stop();
      return;
    }
    setFeetAnimation();
    move(pDX, pDY, pTPF);
    rotateFeetToMovement(pDX, pDY);
  }

  public void render(Graphics pGraphics)
  {
    double xOff = Camera.xOff;
    double yOff = Camera.yOff;

    //pGraphics.pushTransform();
    //pGraphics.rotate((float) ((-xOff + posX) + (DefaultSettingsCallback.WINDOW_WIDTH / 2)), (float) (-yOff + posY) + (DefaultSettingsCallback.WINDOW_HEIGHT / 2), feetRotation);
    //spriteFeet.draw((float) ((-xOff + posX) + (DefaultSettingsCallback.WINDOW_WIDTH / 2) - (size / 2)), (float) (-yOff + posY) + (DefaultSettingsCallback.WINDOW_HEIGHT / 2) - (size / 2));
    //pGraphics.popTransform();
    //pGraphics.pushTransform();
    //pGraphics.rotate((float) ((-xOff + posX) + (DefaultSettingsCallback.WINDOW_WIDTH / 2)), (float) (-yOff + posY) + (DefaultSettingsCallback.WINDOW_HEIGHT / 2), -bodyRotation - 180);
    //spriteBody.draw((float) ((-xOff + posX) + (DefaultSettingsCallback.WINDOW_WIDTH / 2) - (size / 2)), (float) (-yOff + posY) + (DefaultSettingsCallback.WINDOW_HEIGHT / 2) - (size / 2));
    //pGraphics.popTransform();
  }

}
