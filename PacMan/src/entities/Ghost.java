package entities;

import strategies.IGhostAIStrategy;

public class Ghost extends GameEntity{

	private IGhostAIStrategy aiStrategy;
	
	public Ghost(String name, int x, int y, Animatable animatable)
	{
		super(name, x, y, animatable);
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
