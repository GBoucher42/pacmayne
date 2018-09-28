package components;

import entities.Direction;
import static configs.GameConfig.TILE_SIZE;

public class MoveComponent implements IComponent {
	private int tileX, tileY;
	private double x, y;
	private Direction direction, awaitingDirection;
	private final double moveIncrementer = TILE_SIZE/5;
	private boolean canTurn = false;
	
	public MoveComponent(double x, double y, Direction direction ) {
		this.tileX = (int)x;
		this.tileY = (int)y;
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
		this.direction = direction;
		this.awaitingDirection = Direction.NONE;
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
	
	public boolean canTurn() {
		return canTurn;
	}
	
}
