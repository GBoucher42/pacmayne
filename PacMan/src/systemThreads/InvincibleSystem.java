package systemThreads;

import java.util.List;
import java.util.Timer;

import components.AudioComponent;
import components.GraphicsComponent;
import components.InvincibleComponent;
import components.MoveComponent;
import entities.Entity;
import entities.EntityManager;
import utils.SyncTimerTask;

public class InvincibleSystem extends SystemBase {

	private Entity pacman;
	private Entity inky;
	private Entity blinky;
	private Entity pinky;
	private Entity clyde;
	private SyncTimerTask repeatedTask;
	private Timer timer = new Timer();
	public InvincibleSystem(EntityManager entityManager, Entity pacman, Entity inky, Entity blinky, Entity pinky, Entity clyde) {
		super(entityManager);
		this.pacman = pacman;
		this.inky = inky;
		this.blinky = blinky;
		this.pinky = pinky;
		this.clyde = clyde;
		repeatedTask = new SyncTimerTask(() -> addIncincibleEndMessages());
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(InvincibleComponent.class.getName());
		for(Entity entity: entities) {
			InvincibleComponent invincible = (InvincibleComponent) entityManager.getComponentOfClass(InvincibleComponent.class.getName(), entity);
			
			if (invincible == null)
				continue;

			MessageEnum message = MessageQueue.consumeEntityMessages(entity, InvincibleComponent.class.getName());
			if(message != null && message.equals(MessageEnum.INVINCIBLE_START)) {
				MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.INVINCIBLE_START); 
				startInvincibleTimer(invincible.getImmunityTime());
			}
		}			
	}
	
	private void addIncincibleEndMessages() {
		MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.INVINCIBLE_END);
		MessageQueue.addMessage(pacman, MoveComponent.class.getName(), MessageEnum.INVINCIBLE_END);					
		MessageQueue.addMessage(inky, GraphicsComponent.class.getName(), MessageEnum.INVINCIBLE_END);
		MessageQueue.addMessage(blinky, GraphicsComponent.class.getName(), MessageEnum.INVINCIBLE_END);
		MessageQueue.addMessage(pinky, GraphicsComponent.class.getName(), MessageEnum.INVINCIBLE_END);
		MessageQueue.addMessage(clyde, GraphicsComponent.class.getName(), MessageEnum.INVINCIBLE_END);
	}
	
	private void startInvincibleTimer(double timeInSeconds) {
		if(repeatedTask.hasRunStarted()) {
			timer.cancel();
		}
		timer = new Timer();
		repeatedTask = new SyncTimerTask(() -> addIncincibleEndMessages());
		repeatedTask.startRunning();
		timer.schedule(repeatedTask, (long) (timeInSeconds * 1000));		
	}
}
