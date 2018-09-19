package entities;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;

public abstract class GameEntity implements IGameEntity {

	protected int tileIndex;	
	protected final String name;
	protected Velocity velocity;
	protected final int startX, startY;
	protected boolean isMoving;
	protected int currentX, currentY;
	protected Animatable animatable;
	
	public GameEntity(String name, int x, int y, Animatable animatable) {
		this(name, x, y, animatable, new Velocity());
	}
	
	public GameEntity(String name, int x, int y, Animatable animatable, Velocity velocity)
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
	
	public void passTunnel() {
		switch(velocity.getDirection())
		{
		case DOWN:
			setCurrentY(getCurrentY() - GAME_TILE_HEIGHT_COUNT);
			tileIndex -= GAME_TILE_HEIGHT_COUNT;
			break;
		case LEFT:
			setCurrentX(getCurrentX() + GAME_TILE_WIDTH_COUNT);
			tileIndex += GAME_TILE_WIDTH_COUNT;
			break;
		case RIGHT:
			setCurrentX(getCurrentX() - GAME_TILE_WIDTH_COUNT);
			tileIndex -= GAME_TILE_WIDTH_COUNT;
			break;
		case UP:
			setCurrentY(getCurrentY() -+ GAME_TILE_HEIGHT_COUNT);
			tileIndex += GAME_TILE_HEIGHT_COUNT;
			break;
		case NONE:
			isMoving = false;
			break;
		default:
			break;
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
	
	public int getStartX()
	{
		return startX;
	}
	
	public int getStartY()
	{
		return startY;
	}
	
	public int getCurrentX()
	{
		return currentX;
	}
	
	public int getCurrentY()
	{
		return currentY;
	}
	
	public void setCurrentX(int x)
	{
		this.currentX = x;
	}
	
	public void setCurrentY(int y)
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
