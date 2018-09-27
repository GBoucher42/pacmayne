package components;

import entities.Direction;
import static configs.GameConfig.TILE_SIZE;

public class MoveComponent implements IComponent {
	private int tileX, tileY;
	private double x, y;
	private Direction direction;
	private final double moveIncrementer = TILE_SIZE/5;
	
	public MoveComponent(double x, double y, Direction direction ) {
		this.tileX = (int)x;
		this.tileY = (int)y;
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
		this.direction = direction;
	}
	
	public void moveOneFrameBySpeed()
	{
		switch(direction)
		{
		case DOWN:
			y+=moveIncrementer;
			if(isNewTile(y))
				++tileY;
			break;
		case LEFT:
			x-=moveIncrementer;
			if(isNewTile(x))
				--tileX;
			break;
		case RIGHT:
			x+=moveIncrementer;
			if(isNewTile(x))
				++tileX;
			break;
		case UP:
			y-=moveIncrementer;
			if(isNewTile(y))
				--tileY;
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
	
}
