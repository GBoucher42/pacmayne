package systems;

import java.util.List;

import components.GraphicsComponent;
import entities.Entity;
import entities.EntityManager;
import threads.MessageEnum;
import threads.MessageQueue;

public class GraphicsSystem extends SystemBase{
	
	public GraphicsSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(GraphicsComponent.class.getName());
		for(Entity entity: entities) {
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			MessageEnum message = MessageQueue.consumeEntityMessages(entity, GraphicsComponent.class.getName());
			if(message != null && message.equals(MessageEnum.EATEN)) {
				graphic.removeImage();
				entityManager.removeEntity(entity);
			} else {				
				graphic.updateImage();				
			}
		}		
	}

}
