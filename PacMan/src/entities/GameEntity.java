package entities;

public class GameEntity {

	protected Velocity velocity;
	protected SpawnPoint spawnPoint;
	protected Animatable animatable;
	protected int tileIndex;
	
	public GameEntity()
	{
		// TODO: init game entity
	}
	
	public int getTileIndex()
	{
		return tileIndex;
	}
	
	public void show()
	{
		// TODO:
	}
	
	public void hide()
	{
		// TODO:
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
	
}
