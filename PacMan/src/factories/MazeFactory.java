package factories;

import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

import entities.Collectable;
import entities.GameEntityType;
import entities.Gum;
import entities.Maze;
import entities.SuperGum;
import entities.Tile;
import entities.TileType;
import entities.TileWall;

public class MazeFactory {
	private static enum TileCode {
		WALL, GUM, WALL2, WALL3, WALL4, WALL5, WALL6, WALL7, WALL8, WALL9, WALL10, WALL11, WALL12, 
		WALL13, WALL14, WALL15, WALL16, WALL17, WALL18, WALL19, WALL20, WALL21, WALL22,
		WALL23, WALL24, WALL25, WALL26, WALL27, WALL28, WALL29, WALL30, WALL31, WALL32,
		WALL33, WALL34, WALL35, WALL36, WALL37, SUPERGUM, FRUIT;
	}
	
	private static TileCode[] tileCodes = TileCode.values();

	// TODO: this should be in a file that we can load from the resources dict
	private final static int levelOneTileGrid[][] = {
			{11, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16,  5,  2, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 12},
			{18,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  38, 17},
			{18,  1, 23, 28, 28, 24,  1, 23, 28, 28, 28, 24,  1, 29, 30,  1, 23, 28, 28, 28, 24,  1, 23, 28, 28, 24,  1, 17},
			{18,  1, 30,  0,  0, 29,  1, 30,  0,  0,  0, 29,  1, 29, 30,  1, 30,  0,  0,  0, 29,  1, 30,  0,  0, 29,  1, 17},
			{18,  1, 25, 27, 27, 26,  1, 25, 27, 27, 27, 26,  1, 33, 34,  1, 25, 27, 27, 27, 26,  1, 25, 27, 27, 26,  1, 17},
			{18,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 17},
			{18,  1, 31, 27, 27, 32,  1, 31, 32,  1, 31, 27, 27, 27, 27, 27, 27, 32,  1, 31, 32,  1, 31, 27, 27, 32,  1, 17},
			{18,  1, 33, 28, 28, 34,  1, 29, 30,  1, 33, 28, 28, 24, 23, 28, 28, 34,  1, 29, 30,  1, 33, 28, 28, 34,  1, 17},
			{18,  1,  1,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1,  1,  1, 17},
			{13, 15, 15, 15, 15, 20,  1, 29, 25, 27, 27, 32,  1, 29, 30,  1, 31, 27, 27, 26, 30,  1, 19, 15, 15, 15, 15, 14},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 23, 28, 28, 34,  1, 33, 34,  1, 33, 28, 28, 24, 30,  1, 17,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 30,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 29, 30,  1, 17,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 30,  1, 19, 15, 36,  0,  0, 35, 15, 20,  1, 29, 30,  1, 17,  0,  0,  0,  0,  0},
			{16, 16, 16, 16, 16, 22,  1, 33, 34,  1, 17,  0,  0,  0,  0,  0,  0, 18,  1, 33, 34,  1, 21, 16, 16, 16, 16, 16},
			{ 1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 17,  0,  0,  0,  0,  0,  0, 18,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1},
			{15, 15, 15, 15, 15, 20,  1, 31, 32,  1, 17,  0,  0,  0,  0,  0,  0, 18,  1, 31, 32,  1, 19, 15, 15, 15, 15, 15},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 30,  1, 21, 16, 16, 16, 16, 16, 16, 22,  1, 29, 30,  1, 17,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 30,  1,  1,  1,  1,  38,  1,  1,  1,  1,  1, 29, 30,  1, 17,  0,  0,  0,  0,  0},
			{ 0,  0,  0,  0,  0, 18,  1, 29, 30,  1, 31, 27, 27, 27, 27, 27, 27, 32,  1, 29, 30,  1, 17,  0,  0,  0,  0,  0},
			{11, 16, 16, 16, 16, 22,  1, 33, 34,  1, 33, 28, 28, 24, 23, 28, 28, 34,  1, 33, 34,  1, 21, 16, 16, 16, 16, 12},
			{18,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 17},
			{18,  1, 31, 27, 27, 32,  1, 31, 27, 27, 27, 32,  1, 29, 30,  1, 31, 27, 27, 27, 32,  1, 31, 27, 27, 32,  1, 17},
			{18,  1, 33, 28, 24, 30,  1, 33, 28, 28, 28, 34,  1, 33, 34,  1, 33, 28, 28, 28, 34,  1, 29, 23, 28, 34,  1, 17},
			{18,  1,  1,  1, 29, 30,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 29, 30,  1,  1,  1, 17},
			{ 8, 27, 32,  1, 29, 30,  1, 31, 32,  1, 31, 27, 27, 27, 27, 27, 27, 32,  1, 31, 32,  1, 29, 30,  1, 31, 27, 10},
			{ 3, 28, 34,  1, 33, 34,  1, 29, 30,  1, 33, 28, 28, 24, 23, 28, 28, 34,  1, 29, 30,  1, 33, 34,  1, 33, 28,  6},
			{18, 38,  1,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1, 29, 30,  1,  1,  1,  1,  1,  1, 17},
			{18,  1, 31, 27, 27, 27, 27, 26, 25, 27, 27, 32,  1, 29, 30,  1, 31, 27, 27, 26, 25, 27, 27, 27, 27, 32,  1, 17},
			{18,  1, 33, 28, 28, 28, 28, 28, 28, 28, 28, 34,  1, 33, 34,  1, 33, 28, 28, 28, 28, 28, 28, 28, 28, 34,  1, 17},
			{18,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 38,  1,  1, 17},
			{13, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 15, 14}
	};
	
	public static Maze BuildMaze() throws Exception
	{
		Maze maze = new Maze();
		
		if (levelOneTileGrid.length != GAME_TILE_HEIGHT_COUNT || levelOneTileGrid[0].length != GAME_TILE_WIDTH_COUNT)
		{
			// TODO: custom exception
			throw new Exception("Map data has invalid size (height/width)");
		}

		for (int i = 0; i < levelOneTileGrid.length; ++i) // rows
		{
			for (int j = 0; j < levelOneTileGrid[0].length; ++j) // columns
			{
				if (levelOneTileGrid[i][j] > tileCodes.length || levelOneTileGrid[i][j] < 0) {
					throw new IndexOutOfBoundsException("The tile code '" + levelOneTileGrid[i][j] + "' in the map data is invalid ");
				}
				
				TileCode tileCode = tileCodes[levelOneTileGrid[i][j]];
				TileType tileType = tileCode == TileCode.GUM || tileCode == TileCode.SUPERGUM || tileCode == TileCode.FRUIT ? TileType.CORRIDOR : TileType.WALL;
				
				Tile newTile = new Tile(j, i, tileType);
				
				if (tileCode == TileCode.GUM) {
					newTile.setCollectable((Collectable) GameEntityFactory.createGameEntity(GameEntityType.GUM, j, i));
				}
				else if (tileCode == TileCode.SUPERGUM) {
					newTile.setCollectable((Collectable) GameEntityFactory.createGameEntity(GameEntityType.SUPERGUM, j, i));
				}
				else if (tileCode == TileCode.FRUIT) {
					newTile.setCollectable((Collectable) GameEntityFactory.createGameEntity(GameEntityType.FRUIT, j, i));
				}
				else if (levelOneTileGrid[i][j] != 0){
					GameEntityFactory.setWallIndex(levelOneTileGrid[i][j]); // TODO: remove this hack
					newTile.setGameEntity(GameEntityFactory.createGameEntity(GameEntityType.WALL, j, i));
				}				
			
				maze.addTile(newTile, i, j);
			}
		}
		
		return maze;
	}
}
