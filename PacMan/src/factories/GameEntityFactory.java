package factories;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.GameEntityType;
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
			// TODO: relay the fetching of images to the ImageRepository instead
			anim = new Animatable(ImageRepository.getImages("pacman", Direction.RIGHT).get(0));			
			anim.addAnimation(Direction.RIGHT, ImageRepository.getImages("pacman", Direction.RIGHT));
			anim.addAnimation(Direction.LEFT, ImageRepository.getImages("pacman", Direction.LEFT));
			anim.addAnimation(Direction.UP, ImageRepository.getImages("pacman", Direction.UP));
			anim.addAnimation(Direction.DOWN, ImageRepository.getImages("pacman", Direction.DOWN));
			entity = new PacMan(x, y, anim, new Velocity(Direction.RIGHT, 1.0));
			break;
		case BLINKY:
			break;
		case CLYDE:
			break;
		case PINKY:
			break;
		case INKY:
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
