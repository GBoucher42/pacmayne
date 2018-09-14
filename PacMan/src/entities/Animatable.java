package entities;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.TILE_SIZE;

public abstract class Animatable extends GameEntity{
	protected Velocity velocity = new Velocity();
	
	public Animatable(String name, double x, double y, double speed, Direction direction) {
		super(name, x, y);
		setSpeed(speed);
		setDirection(direction);
	}

	private boolean isMoving;
	
	// Change on collision
	public void setIsMoving(boolean isMoving)
	{
		this.isMoving = isMoving;
	}

	public void moveOneFrameBySpeed()
	{
		if (isMoving)
		{
			switch(velocity.getDirection())
			{
			case DOWN:
				setCurrentY(getCurrentY() + TILE_SIZE);
				tileIndex += GAME_TILE_WIDTH_COUNT;
				break;
			case LEFT:
				setCurrentX(getCurrentX() - TILE_SIZE);
				--tileIndex;
				break;
			case RIGHT:
				setCurrentX(getCurrentX() + TILE_SIZE);
				++tileIndex;
				break;
			case UP:
				setCurrentY(getCurrentY() - TILE_SIZE);
				tileIndex -= GAME_TILE_WIDTH_COUNT;
				break;
			default:
				break;
			}
		}
	}
	
	public void startCurrentAnimation()
	{
		// TODO:
	}
	
	public void stopCurrentAnimation()
	{
		// TODO:
	}
	
	public void setSpeed(double speed) {
		velocity.setSpeed(speed);
	}
	
	public void setDirection(Direction direction)
	{
		velocity.setDirection(direction);
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
	
	public Velocity getVelocity()
	{
		return this.velocity;
	}
}
