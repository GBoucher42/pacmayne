package systems;

import java.util.List;

import components.GraphicsComponent;
import components.PhysicsComponent;
import components.ScoreComponent;
import entities.CollisionType;
import entities.Entity;
import entities.EntityManager;
import entities.Maze;
import threads.MessageEnum;
import threads.MessageQueue;

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
		ScoreComponent pacmanScore = (ScoreComponent) entityManager.getComponentOfClass(ScoreComponent.class.getName(), pacman);
		
		for(Entity entity: entities) {
			PhysicsComponent physic = (PhysicsComponent) entityManager.getComponentOfClass(PhysicsComponent.class.getName(), entity);
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			if (graphic == null || entity == pacman)
				continue;
			
			if(pacmanGraphic.getBounds().intersects(graphic.getBounds())) {
				MessageQueue.addMessage(entity, GraphicsComponent.class.getName(), MessageEnum.EATEN);
				
				if (physic.getCollisionType() == "Gum") {
					MessageQueue.addMessage(pacman, ScoreComponent.class.getName(), MessageEnum.GUMPOINTS);
				} else if (physic.getCollisionType() == "SuperGum"){
					MessageQueue.addMessage(pacman, ScoreComponent.class.getName(), MessageEnum.SUPERGUMPOINTS);
				}				
			}
		}		
	}
}