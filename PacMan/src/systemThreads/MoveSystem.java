package systemThreads;

import java.util.List;

import javax.swing.plaf.synth.SynthSeparatorUI;

import components.GraphicsComponent;
import components.MoveComponent;
import entities.CollisionType;
import entities.Direction;
import entities.Entity;
import entities.EntityManager;
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
			
			CollisionType awaitingCollisionType = maze.validateMove(move, move.getAwaitingDirection());
			
			if(!move.isInTunnel() && move.canTurn() &&!move.getAwaitingDirection().equals(Direction.NONE) && 
					( awaitingCollisionType == CollisionType.NONE ||  awaitingCollisionType == CollisionType.TUNNEL && move.canPassTunnel())) {
				move.updateDirection();
			} 
			
			CollisionType collisionType = maze.validateMove(move, move.getDirection());
			if(collisionType == CollisionType.NONE) {
				move.moveOneFrameBySpeed();
				move.setInTunnel(false);
				graphic.updatePosition(move.getX(), move.getY(), move.getDirection());
			} else if(collisionType == CollisionType.TUNNEL && move.canPassTunnel()) {
				move.setInTunnel(true);
				move.moveOneFrameBySpeed();
				graphic.updatePosition(move.getX(), move.getY(), move.getDirection());
			} else if (collisionType == CollisionType.OVERBOUND) {
				move.passTunnel();
			}
		}		
	}

}
