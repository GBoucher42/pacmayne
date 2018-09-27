package components;

import java.util.List;

import entities.CollisionType;
import entities.Maze;

public class PhysicsSystem extends SystemBase {
	Entity pacman;
	
	public PhysicsSystem(EntityManager entityManager, Entity pacman) {
		super(entityManager);
		this.pacman = pacman;
	}
	

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(PhysicsComponent.class.getName());
		GraphicComponent pacmanGraphic = (GraphicComponent) entityManager.getComponentOfClass(GraphicComponent.class.getName(), pacman);
		for(Entity entity: entities) {
			PhysicsComponent physic = (PhysicsComponent) entityManager.getComponentOfClass(PhysicsComponent.class.getName(), entity);
			GraphicComponent graphic = (GraphicComponent) entityManager.getComponentOfClass(GraphicComponent.class.getName(), entity);
			if (graphic == null || entity == pacman)
				continue;
			if(pacmanGraphic.getBounds().intersects(graphic.getBounds())) {
				System.out.println("Collision " +  physic.getCollisionType() + " with " + entity.toString() + 
						" at position: pacman: x:" + pacmanGraphic.getBounds().getMinX() + " " + pacmanGraphic.getBounds().getMaxX() + ", y:" + pacmanGraphic.getBounds().getMinY() + " " + pacmanGraphic.getBounds().getMaxY() +
						 "other: x:" + graphic.getBounds().getMinX() + " " + graphic.getBounds().getMaxX() + ", y:" + graphic.getBounds().getMinY() + " " + graphic.getBounds().getMaxY());
			}
			
		}		
	}

}