package strategies;

import entities.Direction;
import entities.Strategy;
import systemThreads.MessageEnum;

//DESIGN PATTERN : Strategy
public class GhostAIChaser extends GhostAIStrategy{
	
	@Override
	public Direction getPursueDirection(Direction direction, MessageEnum message) {
		if(message != null && message.equals(MessageEnum.PACMAN_SAW)) {
			return direction;
		}
		return super.getPursueDirection(direction, message);
	}

	@Override
	public Strategy getStragtyEnum() {
		return Strategy.CHASE;
	}

}
