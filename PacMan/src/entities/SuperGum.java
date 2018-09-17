package entities;

public class SuperGum extends Gum{

	public SuperGum(int scoreValue, int x, int y) {
		super(scoreValue, x, y);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isTileSuperGum()
	{
		return true;
	}
	
	@Override
	public void consume() {
		// TODO Auto-generated method stub
		
	}
}
