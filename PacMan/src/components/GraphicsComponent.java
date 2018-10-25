package components;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import entities.Direction;
import javafx.geometry.Bounds;
import rendering.Sprite;

public class GraphicsComponent implements IComponent{
	private Sprite sprite;
	private Direction direction;
	private Animation currentAnimation;
	private String currentImage;
	boolean isAnimated = false;
	private Map<Direction, Animation> animationGroup = new HashMap<Direction, Animation>();
	
	public GraphicsComponent(Direction direction, List<String> images, double x, double y, boolean oversize) {
		addAnimation(direction, images);
		this.sprite = new Sprite(currentImage, x, y, oversize);
		isAnimated = true;
	}
	
	public GraphicsComponent(String image, double x, double y, boolean oversize) {
		this.currentImage = image;
		this.sprite = new Sprite(image, x, y, oversize);
		this.direction = Direction.NONE;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public String getCurrentImage() {
		return currentImage;
	}
	
	public void updateImage() {
		if(isAnimated) {
			currentImage = currentAnimation.getNextImage();
			updateSpriteImage();
		}
	}
	
	private void updateSpriteImage() {
		sprite.setImage(currentImage);
	}
	
	public void addAnimation(Direction direction, List<String> images)
	{
		
		if (!animationGroup.containsKey(direction)) {
			Animation anim = new Animation(images);
			animationGroup.put(direction, anim);
		}
		if(currentImage == null || currentImage.isEmpty()) {
			currentImage = currentAnimation.getNextImage();
		}
	}

	public Bounds getBounds() {
		return sprite.getBounds();
	}
	
	public void removeImage() {
		sprite.removeImage();
	}
	
	public void updatePosition(double x, double y, Direction direction) {
		if(!direction.equals(this.direction)) {
			this.direction = direction;
			this.currentAnimation = animationGroup.get(direction);
		}
		
		sprite.updatePosition(x, y);
	}
	
	private class Animation {
		private List<String> images;
		private int imageIndex = 0;
		
		public Animation(List<String> images)
		{
			this.images = images;
			if (currentAnimation != this) {
				currentAnimation = this;
			}
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
