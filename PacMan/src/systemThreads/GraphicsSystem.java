package systemThreads;

import java.util.List;

import components.GraphicsComponent;
import entities.Entity;
import entities.EntityManager;

public class GraphicsSystem extends SystemBase implements Runnable{
	
	private volatile boolean isRunning = true;
	
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

	public void stopThread() {
		isRunning = false;		
	}

	@Override
	public void run() {
		System.out.println("Start graphic Thread");
		while(isRunning) {
			update();
			try {
				Thread.sleep(99);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stop graphic Thread!");	
		
	}

}
