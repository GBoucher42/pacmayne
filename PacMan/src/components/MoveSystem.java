package components;

import java.util.List;

import entities.CollisionType;
import entities.Direction;
import entities.Maze;

public class MoveSystem extends SystemBase {
	private Maze maze;
	
	public MoveSystem(EntityManager entityManager, Maze maze) {
		super(entityManager);
		this.maze = maze;
	}
	

	@Override
	public void update() {
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(MoveComponent.class.getName());
		for(Entity entity: entities) {	
			MoveComponent move = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), entity);
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			if(graphic == null) 
				continue;
			
			if(move.canTurn() &&!move.getAwaitingDirection().equals(Direction.NONE) && maze.validateMove(move, move.getAwaitingDirection()) == CollisionType.NONE) {
				move.updateDirection();
			}
			
			if(maze.validateMove(move, move.getDirection()) == CollisionType.NONE) {
				move.moveOneFrameBySpeed();
				graphic.updatePosition(move.getX(), move.getY(), move.getDirection());
			}
		}		
	}

}
