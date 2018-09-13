package entities;

import static configs.GameConfig.TILE_SIZE;

import javafx.scene.shape.Rectangle;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

public class PacMan extends GameEntity{
	private Rectangle shape;
	
	public PacMan()
	{
		tileIndex = 30;
	}
	
	public void startMoving()
	{
		switch(velocity.getDirection()){
		case UP:			
			tileIndex -= GAME_TILE_WIDTH_COUNT;		
			shape.setY(shape.getY() - TILE_SIZE * velocity.getSpeed());
			break;
		case DOWN:
			tileIndex += GAME_TILE_WIDTH_COUNT;		
			shape.setY(shape.getY() + TILE_SIZE * velocity.getSpeed());
			break;
		case LEFT:
			--tileIndex;		
			shape.setX(shape.getX() - TILE_SIZE * velocity.getSpeed());
			break;
		case RIGHT:
			++tileIndex;
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
	
	
}
