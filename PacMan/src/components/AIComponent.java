package components;

import entities.Direction;
import entities.Entity;
import strategies.GhostAIStrategy;
import systemThreads.MessageEnum;
import systemThreads.MessageQueue;

public class AIComponent implements IComponent{
	private GhostAIStrategy strategy;
	
	public Direction getDirection(Direction currentDirection, Entity entity) {
		MessageEnum message = MessageQueue.consumeEntityMessages(entity, AIComponent.class.getName());
		return strategy.getPursueDirection(currentDirection, message);
	}
	
	public Direction getDirection(Direction desiredDirection, MessageEnum message) {
		return strategy.getPursueDirection(desiredDirection, message);
	}
	
	public AIComponent(GhostAIStrategy strategy) {
		this.strategy = strategy;
	}

	public GhostAIStrategy getStrategy() {
		return strategy;
	}

	public void setStrategy(GhostAIStrategy strategy) {
		this.strategy = strategy;
	}

}
