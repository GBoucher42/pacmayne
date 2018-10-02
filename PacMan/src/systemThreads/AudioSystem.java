package systemThreads;

import java.util.List;

import components.AudioComponent;
import entities.Entity;
import entities.EntityManager;
import threads.MessageEnum;
import threads.MessageQueue;

public class AudioSystem extends SystemBase implements Runnable{
	
	private volatile boolean isRunning = true;

	public AudioSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(AudioComponent.class.getName());
		for(Entity entity: entities) {
			AudioComponent audio = (AudioComponent) entityManager.getComponentOfClass(AudioComponent.class.getName(), entity);
			MessageEnum message = MessageQueue.consumeEntityMessages(entity, AudioComponent.class.getName());
			
			if (message != null) {
				audio.play();
			}
		}
	}
	
	public void stopThread() {
		isRunning = false;		
	}

	@Override
	public void run() {
		System.out.println("Start audio Thread");
		while(isRunning) {
			update();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stop audio Thread!");	
		
	}

}
