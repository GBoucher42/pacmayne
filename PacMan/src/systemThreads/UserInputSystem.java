package systemThreads;

import java.util.List;

import components.MoveComponent;
import components.UserInputComponent;
import entities.Direction;
import entities.Entity;
import entities.EntityManager;

public class UserInputSystem extends SystemBase {

	public UserInputSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(UserInputComponent.class.getName());
		for(Entity entity: entities) {
			UserInputComponent input = (UserInputComponent) entityManager.getComponentOfClass(UserInputComponent.class.getName(), entity);
			Direction direction = input.getInputMessage(entity);
			
			if (direction != Direction.NONE) {
				MoveComponent move = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), entity);
				
				if (move != null) {
					move.setAwaitingDirection(direction);
				}
			}
		}		
	}
}
