package entities;

public abstract class GameEntity {

	protected Velocity velocity;
	protected SpawnPoint spawnPoint;
	protected Animatable animatable;
	protected int tileIndex;	
	protected final String name;
	double startX, startY, currentX, currentY;
	
	public GameEntity(String name, double x, double y)
	{
		this.name = name;
		startX = x;
		currentX = x;
		startY = y;
		currentY = y;
		velocity = new Velocity();
	}
	
	public abstract void update();
	
	public int getTileIndex()
	{
		return tileIndex;
	}
	
	public void setSpeed(double speed) {
		velocity.setSpeed(speed);
	}
	
	public void moveOneFrameBySpeed()
	{
		// TODO:
	}
	
	public void startCurrentAnimation()
	{
		// TODO:
	}
	
	public void stopCurrentAnimation()
	{
		// TODO:
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

	public void setSpawnPoint(SpawnPoint spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public void setAnimatable(Animatable animatable) {
		this.animatable = animatable;
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
}
