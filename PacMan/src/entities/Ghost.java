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
