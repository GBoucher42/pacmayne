package components;

import java.util.List;

public class GraphicsSystem extends SystemBase{

	private int refreshCounter = 1;
	
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
				if (refreshCounter % 3== 0) {
					refreshCounter = 1;	
					graphic.updateImage();
				} else {
					++refreshCounter;
				}
			}
		}		
	}

}
