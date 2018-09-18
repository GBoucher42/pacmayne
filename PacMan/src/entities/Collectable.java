package entities;

public abstract class Collectable extends GameEntity {
	private int value;
	
	public Collectable(int scoreValue, int x, int y, Animatable animatable, String name)
	{
		super(name, x, y, animatable);		
		value = scoreValue;
	}
	
	public int getScoreValue()
	{
		return value;
	}

	public boolean isTileSuperGum() {
		return false;
	}

	public boolean isTileGum() {
		return false;
	}	
}
