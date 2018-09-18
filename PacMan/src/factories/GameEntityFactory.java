package factories;

import java.util.ArrayList;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.GameEntityType;
import entities.IGameEntity;
import entities.PacMan;

public class GameEntityFactory {
	
	public static IGameEntity createGameEntity(GameEntityType type, int x, int y)
	{
		GameEntity entity = null;
		Animatable anim = new Animatable();
		
		switch(type)
		{
		case PACMAN:
			// TODO: relay the fetching of images to the ImageRepository instead
			anim.addAnimation(Direction.RIGHT, new ArrayList<String>() {{ add("ressource/sprites/pacman-r1.png"); add("ressource/sprites/pacman-r2.png"); }});
			anim.addAnimation(Direction.LEFT, new ArrayList<String>() {{ add("ressource/sprites/pacman-l1.png"); add("ressource/sprites/pacman-l2.png"); }});
			anim.addAnimation(Direction.UP, new ArrayList<String>() {{ add("ressource/sprites/pacman-u1.png"); add("ressource/sprites/pacman-u2.png"); }});
			anim.addAnimation(Direction.DOWN, new ArrayList<String>() {{ add("ressource/sprites/pacman-d1.png"); add("ressource/sprites/pacman-d2.png"); }});
			entity = new PacMan(x, y, anim);
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
			break;
		case SUPERGUM:
			break;
		case FRUIT:
			break;
		}
		
		return entity;
	}
}
