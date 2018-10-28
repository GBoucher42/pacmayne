package systemThreads;

import java.util.List;

import components.LifeComponent;
import components.ScoreComponent;
import entities.Entity;
import entities.EntityManager;

public class LifeSystem extends SystemBase {
	
	public LifeSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(LifeComponent.class.getName());
		for(Entity entity: entities) {
			LifeComponent life = (LifeComponent) entityManager.getComponentOfClass(LifeComponent.class.getName(), entity);
			MessageEnum message = MessageQueue.consumeEntityMessages(entity, LifeComponent.class.getName());
			
			if (message != null ) {
				life.removeLife();
				
			}	
		}
		
	}

}
