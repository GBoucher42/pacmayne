package componentsTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entities.Direction;
import entities.EntityManager;
import entities.Maze;
import entities.Tile;
import entities.TileType;
import factories.EntityFactory;
import systemThreads.MoveSystem;
import systemThreads.PhysicsSystem;

@TestInstance(Lifecycle.PER_CLASS)
class PhysicsSystemTest {

	PhysicsSystem system;
	EntityManager entityManager;
	EntityFactory factory;

	
	@BeforeAll
	void setup() {
		entityManager = new EntityManager();
		factory = new EntityFactory(entityManager);
		system = new PhysicsSystem(entityManager, factory.createPacMan(1, 1 , Direction.RIGHT));
		
		
	}
	
	@Test
	public void willNotCollide() {
		factory.createGum(1,3);
		system.update();
	}

}
