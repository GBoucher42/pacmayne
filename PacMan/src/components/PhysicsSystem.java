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
		GraphicsComponent pacmanGraphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), pacman);
		for(Entity entity: entities) {
			PhysicsComponent physic = (PhysicsComponent) entityManager.getComponentOfClass(PhysicsComponent.class.getName(), entity);
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			if (graphic == null || entity == pacman)
				continue;
			if(pacmanGraphic.getBounds().intersects(graphic.getBounds())) {
				MessageQueue.addMessage(entity, GraphicsComponent.class.getName(), MessageEnum.EATEN);
			}
			
		}		
	}

}