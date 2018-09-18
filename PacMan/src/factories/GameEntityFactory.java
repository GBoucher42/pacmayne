package factories;

import entities.Animatable;
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
			// TODO: fetch image and assign them to the anim
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
