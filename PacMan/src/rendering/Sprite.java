package rendering;

import static configs.GameConfig.TILE_SIZE;

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

		image = new ImageView();
		
		//scale image
		image.fitWidthProperty().bind(this.widthProperty());
		image.fitHeightProperty().bind(this.heightProperty());
		image.translateXProperty().bind(this.widthProperty().subtract(image.getFitWidth()).divide(4));
		image.translateYProperty().bind(this.heightProperty().subtract(image.getFitHeight()).divide(4));
		setImage(imgPath);
		
		this.getChildren().add(image);		
	}
	
	public void removeImage() {
		image.setImage(null);
	}
	
	public void setImage(String imgPath) {
		try {
			Image img = new Image("file:" + imgPath);
			image.setImage(img);
		} catch (Exception e)
		{
			// temp fix
		}
		
	}
	
	public Bounds getBounds(){
		return bounds;
	}
	
	public void updatePosition(double x, double y)
	{
		setLayoutX(x);
		setLayoutY(y);
		this.bounds = new BoundingBox(this.getLayoutX(), this.getLayoutY(), this.getWidth() -5, this.getHeight() -5);
	}

}
