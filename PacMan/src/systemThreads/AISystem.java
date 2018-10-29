package systemThreads;

import java.util.List;
import java.util.Random;

import components.AIComponent;
import components.MoveComponent;
import entities.Direction;
import entities.Entity;
import entities.EntityManager;
import entities.Maze;

public class AISystem extends SystemBase {
	private Entity pacman;
	private Maze maze;
	Random random = new Random();
	public AISystem(EntityManager entityManager, Entity pacman, Maze maze) {
		super(entityManager);
		this.pacman = pacman;
		this.maze = maze;
	}

	@Override
	public void update() {
		MoveComponent pacmanMove = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), pacman);
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(AIComponent.class.getName());
		for(Entity entity: entities) {
			AIComponent ai = (AIComponent) entityManager.getComponentOfClass(AIComponent.class.getName(), entity);
			MoveComponent move = (MoveComponent) entityManager.getComponentOfClass(MoveComponent.class.getName(), entity);
			
			
			if(move == null) {
				continue;
			}
			
			switch(ai.getStrategy().getStragtyEnum()) {
			case RANDOM:
				handleRandom(move, ai, entity);
				break;
			case AMBUSH:
				handleAmbush(move, pacmanMove, ai, entity);
				break;
			case CHASE:
				handleChase(move, pacmanMove, ai, entity);
				break;
			case STUPID:
				handleStupid(move, pacmanMove, ai, entity);
				break;
			default:
				break;
			}
			
			
			
			
		}
	}
	
	private void handleAmbush(MoveComponent move, MoveComponent pacmanMove,  AIComponent ai, Entity entity) {
		if(Math.abs(move.getX() - pacmanMove.getX()) < 4) {
			if(move.getY() < pacmanMove.getY()) {
				move.setAwaitingDirection(ai.getDirection(Direction.DOWN, MessageEnum.PACMAN_PARALLELE));
			} else {
				move.setAwaitingDirection(ai.getDirection(Direction.UP, MessageEnum.PACMAN_PARALLELE));
			}
		} else if(Math.abs(move.getY() - pacmanMove.getY()) < 4) {
			if(move.getX() < pacmanMove.getX()) {
				move.setAwaitingDirection(ai.getDirection(Direction.RIGHT, MessageEnum.PACMAN_PARALLELE));
			} else {
				move.setAwaitingDirection(ai.getDirection(Direction.LEFT, MessageEnum.PACMAN_PARALLELE));
			}
		} else {
			handleRandom(move, ai, entity);
		}
	}
	
	private void handleStupid(MoveComponent move, MoveComponent pacmanMove,  AIComponent ai, Entity entity) {
	    if(random.nextBoolean()) {
	    	handleChase(move, pacmanMove, ai, entity);
	    } else {
	    	handleRandom(move, ai, entity);
	    }
	}
	
	private void handleChase(MoveComponent move, MoveComponent pacmanMove,  AIComponent ai, Entity entity) {
		boolean chasing = false;
		if(move.getTileX() == pacmanMove.getTileX()) {
			if(move.getY() < pacmanMove.getY()) {
				if(maze.isInSameCorridor(move.getTileY(), pacmanMove.getTileY(), move.getTileX(), pacmanMove.getTileX(), Direction.DOWN)) {
					chasing = true;
					move.setAwaitingDirection(ai.getDirection(Direction.DOWN, MessageEnum.PACMAN_SAW));
				}
			} else {
				if(maze.isInSameCorridor(pacmanMove.getTileY(), move.getTileY(), pacmanMove.getTileX(), move.getTileX(), Direction.UP)) {
					chasing = true;
					move.setAwaitingDirection(ai.getDirection(Direction.UP, MessageEnum.PACMAN_SAW));
				}
			}
		} else if(Math.abs(move.getY() - pacmanMove.getY()) < 4) {
			if(move.getX() < pacmanMove.getX()) {
				if(maze.isInSameCorridor(move.getTileY(), pacmanMove.getTileY(), move.getTileX(), pacmanMove.getTileX(), Direction.RIGHT)) {
					chasing = true;
					move.setAwaitingDirection(ai.getDirection(Direction.RIGHT, MessageEnum.PACMAN_SAW));
				}
			} else {
				if(maze.isInSameCorridor(pacmanMove.getTileY(), move.getTileY(), pacmanMove.getTileX(), move.getTileX(), Direction.LEFT)) {
					chasing = true;
					move.setAwaitingDirection(ai.getDirection(Direction.LEFT, MessageEnum.PACMAN_SAW));
				}
			}
		} 
		if(!chasing) {
			handleRandom(move, ai, entity);
		}
	}
	
	private void handleRandom(MoveComponent move,  AIComponent ai, Entity entity) {
		move.setAwaitingDirection(ai.getDirection(move.getDirection(), entity));
	}

}
