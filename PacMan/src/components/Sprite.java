package components;

import static configs.GameConfig.TILE_SIZE;

import entities.IGameEntity;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class Sprite extends StackPane {
	
	private ImageView image;
	private Bounds bounds;
	
	public Sprite(String imgPath, double x, double y)
	{
		this.setWidth(TILE_SIZE);
		this.setHeight(TILE_SIZE);
		updatePosition(x,y);	

//		image = new ImageView();
//		setImage(imgPath);
		
		
	}
	
	public void setImage(String imgPath) {
		Image img = new Image("file:" + imgPath);
		image.setImage(img);
	}
	
	public Bounds getBounds(){
		return bounds;
	}
	
	public void updatePosition(double x, double y)
	{
		setLayoutX(x);
		setLayoutY(y);
		this.bounds = new BoundingBox(this.getLayoutX(), this.getLayoutY(), this.getWidth(), this.getHeight());
	}

}
