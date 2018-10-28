package systemThreads;

import java.util.List;

import components.GraphicsComponent;
import components.MoveComponent;
import entities.Entity;
import entities.EntityManager;
import entities.SpritesEnum;

public class GraphicsSystem extends SystemBase implements Runnable{
	
	private volatile boolean isRunning = true;
	private int counter = 0;
	
	public GraphicsSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(GraphicsComponent.class.getName());
		for(Entity entity: entities) {
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			MessageEnum message = MessageQueue.consumeEntityMessages(entity, GraphicsComponent.class.getName());
			if(message != null) {
				if(message.equals(MessageEnum.EATEN)) {
					graphic.removeImage();
					entityManager.removeEntity(entity);
				} else if(message.equals(MessageEnum.KILLED)) {
					graphic.setSpriteEnum(SpritesEnum.DEATH);
				} else if(message.equals(MessageEnum.INVINCIBLE_START)) {
					graphic.setSpriteEnum(SpritesEnum.AFRAID);
				} else if(message.equals(MessageEnum.INVINCIBLE_END)) {
					System.out.println("RETURN TO N0RMAL");
					MoveComponent move = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), entity);
					graphic.setSpriteEnum(move.getDirection());
				}
			} 
			
			if(counter == 3) {
				graphic.updateImage();	
			} 
		}	
		counter = counter == 3 ? 0 : counter + 1;
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
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stop graphic Thread!");	
		
	}

}
