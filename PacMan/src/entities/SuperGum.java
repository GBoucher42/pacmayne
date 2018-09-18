package entities;

public class SuperGum extends Gum{

	public SuperGum(int scoreValue, int x, int y, Animatable animatable) {
		super(scoreValue, x, y, animatable, SuperGum.class.getName());
	}

	@Override
	public boolean isTileSuperGum()
	{
		return true;
	}
}
