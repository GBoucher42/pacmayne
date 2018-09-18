package entities;

import strategies.IGhostAIStrategy;

public class Ghost extends Animatable{

	private IGhostAIStrategy aiStrategy;
	
	public Ghost(String name, double x, double y, double initSpeed, Direction initDirection)
	{
		super(name, x, y, initSpeed, initDirection);
	}
	
	public void setAIStrategy(IGhostAIStrategy strategy)
	{
		aiStrategy = strategy;	
	}
	
	public void setDead(boolean isDead)
	{
		// TODO:
	}
	
	public void startPursuing()
	{
		// TODO:
	}
	
	public void startEvading()
	{
		// TODO:
	}
	
	public void setStandBy()
	{
		// TODO:
	}
}
