package entities;

public class PacMan extends Animatable{
	
	public final static int MAX_LIFE = 3;
	private int lives = MAX_LIFE;
	private boolean hasPower = false;
	
	public PacMan(double x, double y, double initSpeed, Direction initDirection)
	{
		super("pacman", x, y,  initSpeed, initDirection);
		tileIndex = 30;
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
