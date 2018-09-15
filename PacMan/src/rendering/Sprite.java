package rendering;


import entities.GameEntity;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

// Javafx wrapper around the game entity object
public class Sprite extends StackPane{
	
	private final int id;
	private GameEntity entity;
	private ImageView image;
	
	public Sprite(GameEntity entity, int id)
	{
		this.id = id;
		this.entity = entity;
		image = null;
		// TODO: fetch image using entity name as key: GraphicRepository.GetImage(entity.getName()); 
		
		Rectangle rect = new Rectangle(entity.getCurrentX(), entity.getCurrentY(), 25, 25);
		rect.setFill(Color.YELLOW);
		this.getChildren().add(rect);
		
		updatePosition();
	}

	// TODO: use bounds to detect collision with other sprites and walls (?)
	public Bounds getBounds(){
		BoundingBox box = new BoundingBox(this.getLayoutX() + 4, this.getLayoutY() + 4, 8, 8);
		return (Bounds)box;
	}
	
	public void updatePosition()
	{
		setLayoutX(entity.getCurrentX());
		setLayoutY(entity.getCurrentY());
	}
	
	public void resetPosition()
	{
		setLayoutX(entity.getStartX());
		setLayoutY(entity.getStartY());
	}  
	
	public GameEntity getEntity()
	{
		return entity;
	}
	
	public void show()
	{
		this.show();
	}
	
	public void hide()
	{
		this.hide();
	}
}
