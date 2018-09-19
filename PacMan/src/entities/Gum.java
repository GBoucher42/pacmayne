package entities;

public class Gum extends Collectable{

	public Gum(int scoreValue, int x, int y, Animatable animatable)
	{
		super(scoreValue, x, y, animatable, Gum.class.getName());
	}
	
	protected Gum(int scoreValue, int x, int y, Animatable animatable, String name) 
	{
		super(scoreValue, x, y, animatable, name);

	}
	
	@Override
	public boolean isTileGum()
	{
		return true;
	}
}
