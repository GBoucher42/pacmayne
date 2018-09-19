package factories;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.GameEntityType;
import entities.Ghost;
import entities.Gum;
import entities.IGameEntity;
import entities.PacMan;
import entities.SuperGum;
import entities.Velocity;
import entities.Wall;
import image.ImageRepository;

public class GameEntityFactory {
	
	private static int wallIndex;
	
	public static void setWallIndex(int index) {
		wallIndex = index;	
	}
	public static IGameEntity createGameEntity(GameEntityType type, int x, int y)
	{
		GameEntity entity = null;
		Animatable anim;
		
		switch(type)
		{
		case PACMAN:
			anim = new Animatable(ImageRepository.getImages("pacman", Direction.RIGHT).get(0));			
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("pacman", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("pacman", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("pacman", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("pacman", Direction.DOWN));
			entity = new PacMan(x, y, anim, new Velocity(Direction.RIGHT, 1.0));
			break;
		case BLINKY:
			anim = new Animatable(ImageRepository.getImages("blinky", Direction.DOWN).get(0));
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("blinky", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("blinky", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("blinky", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("blinky", Direction.DOWN));
			entity = new Ghost("blinky", x, y, anim, new Velocity(Direction.DOWN, 0.0));
			break;
		case CLYDE:
			anim = new Animatable(ImageRepository.getImages("clyde", Direction.DOWN).get(0));
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("clyde", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("clyde", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("clyde", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("clyde", Direction.DOWN));
			entity = new Ghost("clyde", x, y, anim, new Velocity(Direction.DOWN, 0.0));
			break;
		case PINKY:
			anim = new Animatable(ImageRepository.getImages("pinky", Direction.DOWN).get(0));
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("pinky", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("pinky", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("pinky", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("pinky", Direction.DOWN));
			entity = new Ghost("pinky", x, y, anim, new Velocity(Direction.DOWN, 0.0));
			break;
		case INKY:
			anim = new Animatable(ImageRepository.getImages("inky", Direction.DOWN).get(0));
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("inky", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("inky", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("inky", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("inky", Direction.DOWN));
			entity = new Ghost("inky", x, y, anim, new Velocity(Direction.DOWN, 0.0));
			break;
		case GUM:
			anim = new Animatable(ImageRepository.getImages("gum", Direction.NONE).get(0));
			entity = new Gum(10, x, y, anim);
			break;
		case SUPERGUM:
			anim = new Animatable(ImageRepository.getImages("gum", Direction.NONE).get(0));
			anim.addAnimation(Direction.NONE, ImageRepository.getImages("gum", Direction.NONE));
			entity = new SuperGum(50, x, y, anim);
			break;
		case FRUIT:
			break;
		case WALL:			
			anim = new Animatable(ImageRepository.getImages("wall-" + wallIndex , Direction.NONE).get(0));
			entity = new Wall(x, y, anim);
			break;
		}
		
		return entity;
	}
}
