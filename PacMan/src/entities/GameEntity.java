package entities;

public class GameEntity {

	protected Velocity velocity;
	protected SpawnPoint spawnPoint;
	protected Animatable animatable;
	
	public GameEntity()
	{
		// TODO: init game entity
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

	public void setSpawnPoint(SpawnPoint spawnPoint) {
		this.spawnPoint = spawnPoint;
	}

	public void setAnimatable(Animatable animatable) {
		this.animatable = animatable;
	}
	
}
