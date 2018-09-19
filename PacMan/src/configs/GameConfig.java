package configs;

public class GameConfig {
	

  public static final int HEIGTH_FOOTER=75;
  public static final int HEIGTH_HEADER=50;
	public static final int TILE_SIZE = 20;
	public static final int GAME_TILE_WIDTH_COUNT = 28;
	public static final int GAME_TILE_HEIGHT_COUNT = 31;
	public static final int GAME_WIDTH = TILE_SIZE * GAME_TILE_WIDTH_COUNT;
	public static final int GAME_HEIGHT = TILE_SIZE * GAME_TILE_HEIGHT_COUNT;
	public static final int GAME_TOTAL_TILE_COUNT =  GAME_TILE_WIDTH_COUNT * GAME_TILE_HEIGHT_COUNT;
	public static final int PACMAN_SPAWN_POINT_X = 1;
	public static final int PACMAN_SPAWN_POINT_Y = 1;
	public static final int HEIGTH_WINDOW = HEIGTH_FOOTER + HEIGTH_HEADER + GAME_HEIGHT;

	//TODO: read config file
	
	// holds resolution, fullscreen params, etc
}
