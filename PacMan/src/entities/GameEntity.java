package entities;

public abstract class GameEntity {

	protected SpawnPoint spawnPoint;
	protected int tileIndex;	
	protected final String name;
	final double startX, startY;
	double currentX, currentY;

	
	public GameEntity(String name, double x, double y)
	{
		this.name = name;
		startX = x;
		currentX = x;
		startY = y;
		currentY = y;
	}
	
	public abstract void update();
	
	public int getTileIndex()
	{
		return tileIndex;
	}	

	public void setSpawnPoint(SpawnPoint spawnPoint) {
		this.spawnPoint = spawnPoint;
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
}
