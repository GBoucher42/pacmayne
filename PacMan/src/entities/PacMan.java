package entities;

public class PacMan extends Animatable{
	
	public final static int MAX_LIFE = 3;
	private int lives = MAX_LIFE;
	
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

	public int getLives() {
		return lives;
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
