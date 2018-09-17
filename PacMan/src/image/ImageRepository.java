package image;

import entities.Direction;
import entities.GameEntity;
import entities.Velocity;

public class ImageRepository {
	public String nameFile = "";
	protected Velocity velocity = new Velocity();
	
	
	public String getImageName(GameEntity gameEntity) {
		Direction direction = velocity.getDirection();
		
		//Récupération première partie du nom
		if(gameEntity != null) {
			nameFile += gameEntity.getName().toString();
		} else {
			return null;
		}
		
		//Récupération de la deuxième partie du nom
		nameFile += "-1";
		
		
		//récupération 3ème partie du nom
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


