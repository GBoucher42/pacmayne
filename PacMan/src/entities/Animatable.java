package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Animatable{
	private Animation currentAnimation;
	private Map<Direction, Animation> animationGroup = new HashMap<Direction, Animation>();
	
	public void startCurrentAnimation()
	{
		// TODO:
	}
	
	public void stopCurrentAnimation()
	{
		// TODO:
	}
	
	public void setCurrentAnimation(Direction direction)
	{
		currentAnimation = animationGroup.get(direction);
	}
	
	public void addAnimation(Direction direction, ArrayList<String> images)
	{
		if (!animationGroup.containsKey(direction)) {
			Animation anim = new Animation(images);
			animationGroup.put(direction, anim);
		}
	}
	
	public String getNextImage()
	{
		return currentAnimation.getNextImage();
	}
	
	private class Animation {
		private ArrayList<String> images;
		private int imageIndex = 0;
		
		public Animation(ArrayList<String> images)
		{
			this.images = images;
		}
		
		public String getNextImage()
		{
			int index = (imageIndex++) % images.size();
			return images.get(index);
		}
	}
}


