package entities;

import static configs.GameConfig.GAME_SIZE;
import static configs.GameConfig.TILE_SIZE;

import javafx.scene.shape.Rectangle;

public class PacMan extends GameEntity{
	private Rectangle shape;
	
	public void startMoving()
	{
		switch(velocity.getDirection()){
		case UP:
			if(shape.getY() >= TILE_SIZE * velocity.getSpeed())
				shape.setY(shape.getY() - TILE_SIZE * velocity.getSpeed());
			break;
		case DOWN:
			if(shape.getY() < GAME_SIZE - TILE_SIZE * velocity.getSpeed())
				shape.setY(shape.getY() + TILE_SIZE * velocity.getSpeed());
			break;
		case LEFT:
			if(shape.getX() >= TILE_SIZE * velocity.getSpeed())
				shape.setX(shape.getX() - TILE_SIZE * velocity.getSpeed());
			break;
		case RIGHT:
			if(shape.getX() < GAME_SIZE - TILE_SIZE * velocity.getSpeed())
				shape.setX(shape.getX() + TILE_SIZE * velocity.getSpeed());
			break;
		default:
			break;
			
		}

	}
	
	public void stopMoving()
	{
		// TODO:
	}

	public void setDead(boolean dead)
	{
		// TODO:
	}
	
	public void setDirectionWithAnimation()
	{
		// TODO:
	}

	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
		this.shape = shape;
	}
	
	
}
