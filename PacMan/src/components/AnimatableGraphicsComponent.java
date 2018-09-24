package components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Direction;


public class AnimatableGraphicsComponent implements IGraphicsComponent{
	private Animation currentAnimation;
	private Map<Direction, Animation> animationGroup = new HashMap<Direction, Animation>();
	
	public AnimatableGraphicsComponent() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(GameObject object) {
		// TODO Auto-generated method stub
		
	}
	
	public void addAnimation(Direction direction, List<String> images)
	{
		if (!animationGroup.containsKey(direction)) {
			Animation anim = new Animation(images);
			animationGroup.put(direction, anim);
		}
	}
	
	private class Animation {
		private List<String> images;
		private int imageIndex = 0;
		
		public Animation(List<String> images)
		{
			this.images = images;
		}
		
		public String getNextImage()
		{
			if (currentAnimation != this) {
				currentAnimation = this;
				this.imageIndex = 0;
			}
			
			int index = (imageIndex++) % images.size();
			if(imageIndex == images.size()) {
				imageIndex = 0;
			}

			return images.get(index);
		}
	}

}
