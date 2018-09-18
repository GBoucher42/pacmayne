package entities;


import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

public class Maze {
	
	private Tile[][] tiles = new Tile[GAME_TILE_HEIGHT_COUNT][GAME_TILE_WIDTH_COUNT];
	
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
	
	public CollisionType validateMove(IGameEntity entity)
	{
		CollisionType collisionType = CollisionType.NONE;
		int entityX = entity.getCurrentX();
		int entityY = entity.getCurrentY();
		
		switch(entity.getDirection())
		{
		case DOWN:
			if (entityY >= GAME_TILE_HEIGHT_COUNT - 1) {
				collisionType = CollisionType.OVERBOUND;
			} else if (tiles[entityY + 1][entityX].isWall()){
				collisionType = CollisionType.COLLIDEWALL;
			}
			break;
		case LEFT:
			if (entityX <= 0) {
				collisionType = CollisionType.OVERBOUND;
			} else if (tiles[entityY][entityX - 1].isWall()){
				collisionType = CollisionType.COLLIDEWALL;
			}
			break;
		case RIGHT:
			if (entityX >= GAME_TILE_WIDTH_COUNT - 1) {
				collisionType = CollisionType.OVERBOUND;
			} else if (tiles[entityY][entityX + 1].isWall()){
				collisionType = CollisionType.COLLIDEWALL;
			}
			break;
		case UP:
			if (entityY <= 0) {
				collisionType = CollisionType.OVERBOUND;
			} else if (tiles[entityY - 1][entityX].isWall()){
				collisionType = CollisionType.COLLIDEWALL;
			}
			break;
		case NONE:
			break;
		default:
			break;
		}
		
		return collisionType;
	}
	
	public Tile[][] getTiles()
	{
		return tiles;
	}
	
	public void resetMap()
	{
		// TODO:
	}
}
