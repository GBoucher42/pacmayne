package rendering;


import entities.GameEntity;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import static configs.GameConfig.TILE_SIZE;

// Javafx wrapper around the game entity object
public class Sprite extends StackPane{
	
	private final int id;
	private GameEntity entity;
	private ImageView image;
	private ImageView[][] images;
	
	public Sprite(GameEntity entity, int id)
	{
		this.id = id;
		this.entity = entity;
		
		Image img = this.updateAvatar(this.getImageName());
		
		image = new ImageView(img);
		
		// Scale image 
		image.fitWidthProperty().bind(this.widthProperty());
		image.fitHeightProperty().bind(this.heightProperty());
		
		image.translateXProperty().bind(this.widthProperty().subtract(image.getFitWidth()).divide(4));
		image.translateYProperty().bind(this.heightProperty().subtract(image.getFitHeight()).divide(4));

		// TODO: fetch image using entity name as key: GraphicRepository.GetImage(entity.getName()); 
		this.getChildren().add(image);		
		updatePosition();
	}

	// TODO: use bounds to detect collision with other sprites and walls (?)
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
	
	public String getImageName() {
		if(entity != null) {
			String imgName = entity.getName().toString() + "-1" + "-right";
			return imgName;
		}
		else return null;
	}
	
	public Image updateAvatar(String imgName) {
		String fileName = "";
		switch(imgName) {
		case "pacman-1-right":
			fileName = "file:ressource/sprites/pacman-r1.png";
			break;
		default: return null;
		}
		return new Image(fileName);
	}
	
	public GameEntity getEntity()
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
