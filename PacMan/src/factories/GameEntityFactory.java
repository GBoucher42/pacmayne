package factories;

import java.util.ArrayList;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.GameEntityType;
import entities.Gum;
import entities.IGameEntity;
import entities.PacMan;
import entities.SuperGum;

public class GameEntityFactory {
	
	public static IGameEntity createGameEntity(GameEntityType type, int x, int y)
	{
		GameEntity entity = null;
		Animatable anim;
		
		switch(type)
		{
		case PACMAN:
			// TODO: relay the fetching of images to the ImageRepository instead
			anim = new Animatable("ressource/sprites/pacman-r1.png");
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
			anim = new Animatable("ressource/sprites/gum-1.png");
			entity = new Gum(10, x, y, anim);
			break;
		case SUPERGUM:
			anim = new Animatable("ressource/sprites/gum-1.png");
			anim.addAnimation(Direction.NONE, new ArrayList<String>() {{ add("ressource/sprites/gum-1.png"); add("ressource/sprites/gum-2.png"); add("ressource/sprites/gum-3.png"); }});
			entity = new SuperGum(50, x, y, anim);
			break;
		case FRUIT:
			break;
		}
		
		return entity;
	}
}
