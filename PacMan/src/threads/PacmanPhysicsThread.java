package threads;

import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

import java.util.HashMap;
import java.util.Map;

import components.GameObject;
import entities.CollisionType;
import entities.Direction;
import entities.IGameEntity;
import entities.Type;

public class PacmanPhysicsThread implements Runnable {
	
	private volatile boolean isRunning;
	GameObject pacman;
	Map<Integer, GameObject> tiles = new HashMap<Integer, GameObject>();
	
	public PacmanPhysicsThread(GameObject pacman, Map<Integer, GameObject> tiles) {
		this.pacman = pacman;
		this.tiles.putAll(tiles);
	}
	
	public void stopThread() {
		isRunning = false;
	}

	@Override
	public void run() {
		isRunning = true;
		while(isRunning) {
			//Check Collsions
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void detectCollision(Direction direction)
	{
		CollisionType collisionType = CollisionType.NONE;
		int entityX = pacman.getCurrentX();
		int entityY = pacman.getCurrentY();
		int nextTile = 0;
		
		switch(direction)
		{
		case DOWN:
			nextTile = entityX+(entityY+1)*GAME_TILE_WIDTH_COUNT;
			GameObject collider = tiles.get(entityX+(entityY+1)*GAME_TILE_WIDTH_COUNT);
			if (collider.getType() == Type.WALL) {
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
		
		GameObject collider = tiles.get(nextTile);
		if (collider.getType() == Type.WALL) {
			//raise wall flag
		}

	}

}
