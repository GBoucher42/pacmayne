package entities;

public abstract class Collectable {
	private int value;
	private int x;
	private int y;
	
	public Collectable(int scoreValue, int x, int y)
	{
		this.x = x;
		this.y = y;
		value = scoreValue;
	}
	
	public abstract void consume();
	
	public int getScoreValue()
	{
		return value;
	}

}
