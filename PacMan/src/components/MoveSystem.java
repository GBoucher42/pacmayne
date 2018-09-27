package components;

import java.util.List;

import entities.CollisionType;
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
			GraphicComponent graphic = (GraphicComponent) entityManager.getComponentOfClass(GraphicComponent.class.getName(), entity);
			if(graphic == null) 
				continue;
			
			if(maze.validateMove(move, move.getDirection()) == CollisionType.NONE) {
				System.out.println("go ahead from position: " + move.getX() + ",  " + move.getY() + " - on tile: " + move.getTileX() + " , " + move.getTileY());
				move.moveOneFrameBySpeed();
				graphic.updatePosition(move.getX(), move.getY(), move.getDirection());
			} else if(maze.validateMove(move, move.getDirection()) == CollisionType.COLLIDEWALL) {
				System.out.println("stop, theres a wall at position: " + move.getX() + ",  " + move.getY() + " - on tile: " + move.getTileX() + " , " + move.getTileY());
			}

			
			
		}		
	}

}
