package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Animatable{
	private Animation currentAnimation;
	private String defaultProp;
	private Map<Direction, Animation> animationGroup = new HashMap<Direction, Animation>();
	
	public Animatable(String defaultProp)
	{
		this.defaultProp = defaultProp;
	}
	
	public void startCurrentAnimation()
	{
		// TODO:
	}
	
	public void stopCurrentAnimation()
	{
		// TODO:
	}
	
	public boolean hasAnimation()
	{
		return animationGroup.size() > 0;
	}
	
	public void setCurrentAnimation(Direction direction)
	{
		if (animationGroup.containsKey(direction)) {
			currentAnimation = animationGroup.get(direction);
		}		
	}
	
	public void addAnimation(Direction direction, ArrayList<String> images)
	{
		if (!animationGroup.containsKey(direction)) {
			Animation anim = new Animation(images);
			animationGroup.put(direction, anim);
			
			if (currentAnimation == null) {
				currentAnimation = anim;
			}
		}
	}
	
	public String getNextImage()
	{		
		return hasAnimation() ? currentAnimation.getNextImage() : defaultProp;
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
			if(imageIndex == images.size()) {
				imageIndex = 0;
			}
			
			return images.get(index);
		}
	}
}


