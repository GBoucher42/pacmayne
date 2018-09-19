package entities;

public class Velocity {

	private double speed;
	private Direction direction;
	
	public Velocity() {
		this(Direction.NONE, 0.0);
	}
	
	public Velocity(Direction direction, double speed) {
		this.direction = direction;
		this.speed = speed;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	public Direction getDirection()
	{
		return direction;
	}
	
	public void setSpeed(double newSpeed)
	{
		speed = newSpeed;
	}
	
	public void setDirection(Direction newDirection)
	{
		direction = newDirection;
	}
}
