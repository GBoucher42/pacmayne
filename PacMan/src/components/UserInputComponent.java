package components;

import entities.Direction;

public class UserInputComponent implements IComponent{
	
	public Direction getInputMessage(Entity entity) {
		// Read message Queue
		return Direction.RIGHT;
	}
}
