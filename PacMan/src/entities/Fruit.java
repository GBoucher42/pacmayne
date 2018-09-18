package entities;

public class Fruit extends Collectable{

	public Fruit(int scoreValue, int x, int y, Animatable animatable)
	{
		super(scoreValue, x, y, animatable, Fruit.class.getName());
	}
	
	@Override
	public void consume() {
		// TODO Auto-generated method stub
		
	}

}
