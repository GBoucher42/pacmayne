package systemThreads;

import java.util.List;

import components.AudioComponent;
import components.GraphicsComponent;
import components.LifeComponent;
import components.PhysicsComponent;
import components.ScoreComponent;
import entities.Entity;
import entities.EntityManager;
import threads.MessageEnum;
import threads.MessageQueue;

public class PhysicsSystem extends SystemBase implements Runnable {
	private Entity pacman;
	private volatile boolean isRunning = true;
	
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
				
				if (physic.getCollisionType() == "Gum") {
					MessageQueue.addMessage(entity, GraphicsComponent.class.getName(), MessageEnum.EATEN);
					MessageQueue.addMessage(pacman, ScoreComponent.class.getName(), MessageEnum.GUMPOINTS);
					MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.EATEN);
				} else if (physic.getCollisionType() == "SuperGum"){
					MessageQueue.addMessage(entity, GraphicsComponent.class.getName(), MessageEnum.EATEN);
					MessageQueue.addMessage(pacman, ScoreComponent.class.getName(), MessageEnum.SUPERGUMPOINTS);
					MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.EATEN);
				} else if(physic.getCollisionType() == "Ghost") {
					MessageQueue.addMessage(pacman, LifeComponent.class.getName(), MessageEnum.KILLED);
//					MessageQueue.addMessage(pacman, GraphicsComponent.class.getName(), MessageEnum.EATEN);
				}			
			}
		}		
	}
	
	public void stopThread() {
		isRunning = false;		
	}


	@Override
	public void run() {
		System.out.println("Start Physics Thread");
		while(isRunning) {
			update();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Stop Physics Thread!");	
	}
}