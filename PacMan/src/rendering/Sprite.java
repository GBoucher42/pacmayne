package rendering;


import entities.IGameEntity;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static configs.GameConfig.TILE_SIZE;

// Javafx wrapper around the game entity object
public class Sprite extends StackPane{
	
	private final int id;
	private IGameEntity entity;
	private ImageView image;
	private ImageView[][] images;
	
	public Sprite(IGameEntity entity, int id)
	{
		this.setWidth(TILE_SIZE);
		this.setHeight(TILE_SIZE);
		this.id = id;
		this.entity = entity;
		String imgPath = entity.getAnimatable().getNextImage();
		
		Image img = new Image("file:" + imgPath);
		image = new ImageView(img);
		
		// Scale image 
		image.fitWidthProperty().bind(this.widthProperty());
		image.fitHeightProperty().bind(this.heightProperty());		
		image.translateXProperty().bind(this.widthProperty().subtract(image.getFitWidth()).divide(4));
		image.translateYProperty().bind(this.heightProperty().subtract(image.getFitHeight()).divide(4));
		
		this.getChildren().add(image);		
		updatePosition();
	}
	
	public void updateAvatar()
	{
		entity.getAnimatable().setCurrentAnimation(entity.getDirection());
		String imgPath = entity.getAnimatable().getNextImage();
		
		 // TODO: store all images in a map and access them instead of creating a new Image every time
		image.setImage(new Image("file:" + imgPath));
	}

	// TODO: use bounds to detect collision with other ghosts
	public Bounds getBounds(){
		BoundingBox box = new BoundingBox(this.getLayoutX() + 4, this.getLayoutY() + 4, 8, 8);
		return (Bounds)box;
	}
	
	public void updatePosition()
	{
		setLayoutX(entity.getCurrentX() * TILE_SIZE);
		setLayoutY(entity.getCurrentY() * TILE_SIZE);
	}
	
	
	public void resetPosition()
	{
		setLayoutX(entity.getStartX());
		setLayoutY(entity.getStartY());
	}  
	
	public IGameEntity getEntity()
	{
		return entity;
	}
	
	public void show()
	{
		this.setVisible(true);
	}
	
	public void hide()
	{
		this.setVisible(false);
	}
	
	
}
