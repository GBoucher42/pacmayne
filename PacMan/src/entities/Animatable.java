package entities;

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
			// TODO:
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
