package entities;

import strategies.IGhostAIStrategy;

public class Ghost extends GameEntity{

	private IGhostAIStrategy aiStrategy;
	
	public void setAIStrategy(IGhostAIStrategy strategy)
	{
		aiStrategy = strategy;	
	}
	
	public void setDead(boolean dead)
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
	
	public void update()
	{
		// TODO:
	}
	
	public void setStandBy()
	{
		// TODO:
	}
}
