package entities;

public class Gum extends Collectable{

	public Gum(int scoreValue, int x, int y)
	{
		super(scoreValue, x, y);
	}
	
	@Override
	public boolean isTileGum()
	{
		return true;
	}
	
	@Override
	public void consume() {
		// TODO Auto-generated method stub
		
	}
}
