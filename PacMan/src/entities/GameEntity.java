package entities;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

public abstract class GameEntity implements IGameEntity {

	protected int tileIndex;	
	protected final String name;
	protected Velocity velocity;
	protected final double startX, startY;
	protected boolean isMoving;
	protected double currentX, currentY;
	protected Animatable animatable;
	
	public GameEntity(String name, double x, double y, Animatable animatable) {
		this(name, x, y, animatable, new Velocity());
	}
	
	public GameEntity(String name, double x, double y, Animatable animatable, Velocity velocity)
	{
		this.name = name;
		startX = x;
		currentX = x;
		startY = y;
		currentY = y;
		this.animatable = animatable;
		this.velocity = velocity;
	}
	
	// Change on collision
	public void setIsMoving(boolean isMoving)
	{
		this.isMoving = isMoving;
	}

	public void moveOneFrameBySpeed()
	{
		if (isMoving)
		{
			// TODO: move per frame instead of jumping tiles (requires game thread to be functionnal)
			switch(velocity.getDirection())
			{
			case DOWN:
				setCurrentY(getCurrentY() + 1);
				tileIndex += GAME_TILE_WIDTH_COUNT;
				break;
			case LEFT:
				setCurrentX(getCurrentX() - 1);
				--tileIndex;
				break;
			case RIGHT:
				setCurrentX(getCurrentX() + 1);
				++tileIndex;
				break;
			case UP:
				setCurrentY(getCurrentY() - 1);
				tileIndex -= GAME_TILE_WIDTH_COUNT;
				break;
			case NONE:
				isMoving = false;
				break;
			default:
				break;
			}
		}
	}
	
	public Animatable getAnimatable()
	{
		return animatable;
	}
	
	public int getTileIndex()
	{
		return tileIndex;
	}	

	public String getName()
	{
		return this.name;
	}
	
	public double getStartX()
	{
		return startX;
	}
	
	public double getStartY()
	{
		return startY;
	}
	
	public double getCurrentX()
	{
		return currentX;
	}
	
	public double getCurrentY()
	{
		return currentY;
	}
	
	public void setCurrentX(double x)
	{
		this.currentX = x;
	}
	
	public void setCurrentY(double y)
	{
		this.currentY = y;
	}
	
	public void setSpeed(double speed) {
		velocity.setSpeed(speed);
	}
	
	public void setDirection(Direction direction)
	{
		velocity.setDirection(direction);
	}
	
	public Direction getDirection()
	{
		return velocity.getDirection();
	}
	
	public double getSpeed()
	{
		return velocity.getSpeed();
	}

	public void setVelocity(Velocity velocity) {
		this.velocity = velocity;
	}
	
	public Velocity getVelocity()
	{
		return this.velocity;
	}
}
