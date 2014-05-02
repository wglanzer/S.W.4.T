package de.swat.core;

/**
 * @author Werner Glanzer, 05.10.13
 */
public class DefaultSettingsCallback
{
  public static String ASSET_PATH = "assets/";

  public static String SHADER_PATH = "Shaders/";

  public static String MAP_PATH = "Maps/";

  public static String SPRITESHEET_PATH = "SpriteSheets/";

  public static String TITLE = "DefaultTitle";

  public static int WINDOW_WIDTH = 800;

  public static int WINDOW_HEIGHT = 600;

  public static boolean WINDOW_ISFULLSCREEN = false;

  public static int[] PLAYER_SPRITES = {0, 15,  //Vorwärtsanimation
                                        16, 31,  //Rückwärtsanimation
                                        32, 35,  //Seitwärts nach rechts
                                        36, 39,  //Seitwärts nach links
                                        40};     //Player

  public static int MAXCLUSTERSINRASTERS = 16;
}