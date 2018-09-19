package image;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.Velocity;
import javafx.scene.image.Image;
import rendering.Sprite;

public class ImageRepository extends Sprite {
	
	public ImageRepository(GameEntity entity, int id) {
		super(entity, id);
		// TODO Auto-generated constructor stub
	}

	
	
	public static String getImageName(GameEntity gameEntity) {
		StringBuilder nameFile = new StringBuilder();
		Animatable anim = (Animatable) gameEntity; 
		
		if(gameEntity == null || anim == null) {
			return null;
		}
		Direction direction = anim.getVelocity().getDirection();
		
		//R�cup�ration premi�re partie du nom
		nameFile.append(gameEntity.getName());
		
		//R�cup�ration de la deuxi�me partie du nom
		nameFile.append("-1-");
		
		
		//r�cup�ration 3�me partie du nom
		nameFile.append(direction.toString().toLowerCase());
		return nameFile.toString();
	}
	
	public static Image updateAvatar(String imgName) {
		try {
			String str = "file:ressource/sprites/" + imgName + ".png";
			return new Image(str);
		} catch (Exception e) {
			System.err.println(e);
			return null;
		}
		
	}
	
	

}


