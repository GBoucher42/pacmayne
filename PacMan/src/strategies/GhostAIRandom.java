package strategies;

import entities.Direction;
import entities.Strategy;
import systemThreads.MessageEnum;

//DESIGN PATTERN : Strategy
public class GhostAIRandom extends GhostAIStrategy{
	
	@Override
	public Direction getPursueDirection(Direction currentDirection, MessageEnum message) {
		return super.getPursueDirection(currentDirection, message);
	}
	
	@Override
	public Strategy getStragtyEnum() {
		return Strategy.RANDOM;
	}
	


}
