package systemThreads;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import components.AudioComponent;
import components.InvincibleComponent;
import entities.Entity;
import entities.EntityManager;
import utils.SyncTimerTask;

public class InvincibleSystem extends SystemBase {

	private Entity pacman;
	private SyncTimerTask repeatedTask;
	private Timer timer = new Timer();
	public InvincibleSystem(EntityManager entityManager, Entity pacman) {
		super(entityManager);
		this.pacman = pacman;
		repeatedTask = new SyncTimerTask(() -> { MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.INVINCIBLE_END); });
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
				startInvincibleTimer(invincible.getImmunityTime());
			}
		}			
	}
	
	private void startInvincibleTimer(double timeInSeconds) {
		if(repeatedTask.hasRunStarted()) {
			timer.cancel();
			timer = new Timer();
			repeatedTask = new SyncTimerTask(() -> { MessageQueue.addMessage(pacman, AudioComponent.class.getName(), MessageEnum.INVINCIBLE_END); });
		}
		repeatedTask.startRunning();
		timer.schedule(repeatedTask, (long) (timeInSeconds * 1000));		
	}
}
