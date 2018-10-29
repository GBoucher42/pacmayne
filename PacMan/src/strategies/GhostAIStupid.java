package strategies;

import java.util.Random;
import java.util.Timer;

import entities.Direction;
import entities.Strategy;
import systemThreads.MessageEnum;
import utils.SyncTimerTask;

//DESIGN PATTERN : Strategy
public class GhostAIStupid extends GhostAIStrategy{
	
	private Random random = new Random();
	private boolean canMakeDecision = true;
	
	@Override
	public Direction getPursueDirection(Direction direction, MessageEnum message) {
		if(message != null && message.equals(MessageEnum.PACMAN_SAW) && canMakeDecision) {
			chaseTimer();
			if(random.nextBoolean()) {
				return direction;
			}
		}
		return super.getPursueDirection(direction, message);
	}
	
	private void chaseTimer() {
		canMakeDecision = false;
		Timer timer = new Timer();
		SyncTimerTask repeatedTask = new SyncTimerTask(() -> canMakeDecision = true);
		repeatedTask.startRunning();
		timer.schedule(repeatedTask, (long) (1000));
	}

	@Override
	public Strategy getStragtyEnum() {
		return Strategy.STUPID;
	}

}
