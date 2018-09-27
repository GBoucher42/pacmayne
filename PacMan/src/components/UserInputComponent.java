package components;

import entities.Direction;

public class UserInputComponent implements IComponent{
	
	public Direction getInputMessage(Entity entity) {
		// Read message Queue
		MessageEnum message = MessageQueue.consumeEntityMessages(entity, this.getClass().getName());
		if(message == null) {
			return Direction.NONE;
		}
		
		switch(message) {
			case DOWN :
				return Direction.DOWN;
			case UP :
				return Direction.UP;
			case RIGHT :
				return Direction.RIGHT;
			case LEFT :
				return Direction.LEFT;
			default :
				return Direction.NONE;
		
		}
	}
}
