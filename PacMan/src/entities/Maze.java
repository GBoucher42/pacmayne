package entities;


import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

public class Maze {
	
	private Tile[][] tiles = new Tile[GAME_TILE_WIDTH_COUNT][GAME_TILE_HEIGHT_COUNT];
	
	public void addTile(Tile tile, int row, int column)
	{
		if (tile != null && row >= 0 && row <= tiles.length && column >= 0 && column <= tiles[0].length)
		{
			tiles[row][column] = tile;
		}
	}
	
	public Tile getTile(int row, int column) {
		if (row >= 0 && row < tiles.length && column >= 0 && column < tiles[0].length)
		{
			return tiles[row][column];
		}
		return null;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
	
	public void resetMap()
	{
		// TODO:
	}
	
	public boolean isOverBound()
	{
		// TODO:
		return true;
	}
}
