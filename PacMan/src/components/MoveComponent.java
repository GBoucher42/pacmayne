package components;

import entities.Direction;
import static configs.GameConfig.TILE_SIZE;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;

public class MoveComponent implements IComponent {
	private int tileX, tileY, spawnX, spawnY;
	private double x, y;
	private Direction direction, awaitingDirection, spawnDirection;
	private final double moveIncrementer = TILE_SIZE/5;
	private boolean canTurn = false;
	private boolean inTunnel = false;
	private final boolean canPassTunnel;
	
	public MoveComponent(double x, double y, Direction direction, boolean canPassTunnel ) {
		this.tileX = (int)x;
		this.tileY = (int)y;
		this.spawnX = (int)x;
		this.spawnY = (int)y;
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
		this.direction = direction;
		this.spawnDirection = direction;
		this.awaitingDirection = Direction.NONE;
		this.canPassTunnel = canPassTunnel;
	}
	
	public void resetPosition() {
		tileX = spawnX;
		tileY = spawnY;
		x = spawnX * TILE_SIZE;
		y = spawnY * TILE_SIZE;
		direction = spawnDirection;
	}
	
	public void passTunnel() {
		switch(direction)
		{
		case DOWN:
			y = 0;
			tileY = 0;
			break;
		case LEFT:
			tileX = GAME_TILE_WIDTH_COUNT;
			x = GAME_TILE_WIDTH_COUNT * TILE_SIZE;
			break;
		case RIGHT:
			x = 0;
			tileX = 0;
			break;
		case UP:
			tileY = GAME_TILE_HEIGHT_COUNT;
			y = GAME_TILE_HEIGHT_COUNT * TILE_SIZE;
			break;
		default:
			break;
		}
	}
	
	public void moveOneFrameBySpeed()
	{
		canTurn = false;
		switch(direction)
		{
		case DOWN:
			y+=moveIncrementer;
			if(isNewTile(y)) {
				++tileY;
				canTurn = true;
			}
			break;
		case LEFT:
			x-=moveIncrementer;
			if(isNewTile(x)) {
				--tileX;
				canTurn = true;
			}
			break;
		case RIGHT:
			x+=moveIncrementer;
			if(isNewTile(x)) {
				++tileX;
				canTurn = true;
			}
			break;
		case UP:
			y-=moveIncrementer;
			if(isNewTile(y)) {
				--tileY;
				canTurn = true;
			}
			break;
		default:
			break;
		}
	}

	private boolean isNewTile(double position) {
		return position % TILE_SIZE == 0;
	}
	
	
	public int getTileX() {
		return tileX;
	}


	public void setTileX(int tileX) {
		this.tileX = tileX;
	}


	public int getTileY() {
		return tileY;
	}

	

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void setTileY(int tileY) {
		this.tileY = tileY;
	}


	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public Direction getAwaitingDirection() {
		return awaitingDirection;
	}

	public void setAwaitingDirection(Direction awaitingDirection) {
		this.awaitingDirection = awaitingDirection;
	}

	public double getMoveIncrementer() {
		return moveIncrementer;
	}	
	
	public void updateDirection() {
		direction = awaitingDirection;
		awaitingDirection = Direction.NONE;
	}
	
	public boolean isInTunnel() {
		return inTunnel;
	}

	public void setInTunnel(boolean inTunnel) {
		this.inTunnel = inTunnel;
	}

	public boolean canTurn() {
		return canTurn;
	}
	
	public boolean canPassTunnel() {
		return canPassTunnel;
	}
	
}
