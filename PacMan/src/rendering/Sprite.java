package rendering;

import entities.GameEntity;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

// Javafx wrapper around the game entity object
public class Sprite extends StackPane{
	
	private GameEntity entity;
	private final ImageView image;
	
	public Sprite(GameEntity entity)
	{
		this.entity = entity;
		image = null;
		// TODO: fetch image using entity name as key: GraphicRepository.GetImage(entity.getName()); 
	}

	public Bounds getBounds(){
		BoundingBox box = new BoundingBox(this.getLayoutX() + 4, this.getLayoutY() + 4, 8, 8);
		return (Bounds)box;
	}
	
	public void resetPosition()
	{
		setLayoutX(entity.getStartX());
		setLayoutY(entity.getStartY());
	}
	
	public void show()
	{
		// TODO:
	}
	
	public void hide()
	{
		// TODO:
	}
}
