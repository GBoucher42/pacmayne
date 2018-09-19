package entities;

public class PacMan extends GameEntity{
	
	public final static int MAX_LIFE = 3;
	private int lives = MAX_LIFE;
	private boolean hasPower = false;
	
	public PacMan(int x, int y, Animatable animatable)
	{
		super("pacman", x, y, animatable);
	}
	
	public PacMan(int x, int y, Animatable animatable, Velocity vel) {
		super("pacman", x, y, animatable, vel);
	}
	
	public void startMoving()
	{
		// TODO:
	}
	
	public void stopMoving()
	{
		// TODO:
	}

	public void setDead(boolean isDead)
	{
		// TODO:
	}
	
	public void setDirectionWithAnimation()
	{
		// TODO:
	}
	
	public boolean hasPower()
	{
		return hasPower;
	}
	
	public void givePower()
	{
		hasPower = true;
	}
	
	public void removePower()
	{
		hasPower = false;
	}

	public int getLives() {
		return lives;
	}
}
