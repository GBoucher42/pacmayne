package entities;

public class Velocity {

	private double speed;
	
	private Direction direction;
	
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
