package strategies;

import entities.Direction;
import entities.Entity;
import entities.Strategy;
import systemThreads.MessageEnum;

//DESIGN PATTERN : Strategy
public class GhostAIAmbusher extends GhostAIStrategy{

	@Override
	public Direction getPursueDirection(Direction direction, MessageEnum message) {
		if(message != null && message.equals(MessageEnum.PACMAN_PARALLELE)) {
			super.removeOppositeDirection(direction);
		}
		return super.getPursueDirection(direction, message);
	}

	@Override
	public Strategy getStragtyEnum() {
		return Strategy.AMBUSH;
	}

}
