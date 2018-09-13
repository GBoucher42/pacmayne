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
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	public void setStandBy()
	{
		// TODO:
	}
}
