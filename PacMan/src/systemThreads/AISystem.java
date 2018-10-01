package systemThreads;

import java.util.List;
import java.util.Random;

import components.AIComponent;
import components.MoveComponent;
import entities.Direction;
import entities.Entity;
import entities.EntityManager;

public class AISystem extends SystemBase {
	
	private int moveIndex = 0;
	private Random r = new Random();
	private Direction[] directions = {Direction.RIGHT, Direction.LEFT, Direction.UP, Direction.DOWN};
	
	public AISystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		if(moveIndex >= 30) {
			moveIndex = 0;
			List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(AIComponent.class.getName());
			for(Entity entity: entities) {
				AIComponent ai = (AIComponent) entityManager.getComponentOfClass(AIComponent.class.getName(), entity);
				MoveComponent move = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), entity);
				
				if(move == null) {
					continue;
				}
				
				switch(ai.getStrategy()){
				case RANDOM:
					move.setAwaitingDirection(directions[r.nextInt(4)]);
					break;
				default:
					break;
					
				}
	
			
			}
		}
		++moveIndex;
	}

}
