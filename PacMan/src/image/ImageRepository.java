package image;

import entities.Direction;
import entities.GameEntity;
import entities.Velocity;

public class ImageRepository {
	public String nameFile = "";
	protected Velocity velocity = new Velocity();
	
	
	public String getImageName(GameEntity gameEntity) {
		Direction direction = velocity.getDirection();
		
		//R�cup�ration premi�re partie du nom
		if(gameEntity != null) {
			nameFile += gameEntity.getName().toString();
		} else {
			return null;
		}
		
		//R�cup�ration de la deuxi�me partie du nom
		nameFile += "-1";
		
		
		//r�cup�ration 3�me partie du nom
		switch(direction) {
			case UP:
				nameFile += "-up";
				System.out.println(nameFile);
				break;
			case DOWN: 
				nameFile += "-down";
				System.out.println(nameFile);
				break;
			case RIGHT: 
				nameFile += "-right";
				System.out.println(nameFile);
				break;
			case LEFT: 
				nameFile += "-left";
				System.out.println(nameFile);
				break;
			default:
				nameFile = "";
				break;
		}
		return nameFile;
	}
	

}


