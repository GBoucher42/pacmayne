package entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when; 

import entities.EntityManager;
import entities.GameEntity;
import entities.IGameEntity;

@TestInstance(Lifecycle.PER_CLASS)
class EntityManagerTest {
	EntityManager entityManager;
	@Mock
	GameEntity gameEntity;
	
	@BeforeAll
	void setup() {
		MockitoAnnotations.initMocks(this);
		entityManager = new EntityManager();
		
		when(gameEntity.getName()).thenReturn("random");
		
		entityManager.addEntity(gameEntity);
	}
	
	@Test
	void willOnlyAddIfNotExists() {
		entityManager.addEntity(gameEntity);
		assertEquals(1, entityManager.count()); //Has not been added, still 1
	}

	
	@Test
	void willFindGameEntityWithName() {
		IGameEntity foundEntity = entityManager.getEntity("random");
		assertEquals(gameEntity, foundEntity);
		foundEntity = entityManager.getEntity("not found");
		assertNull(foundEntity);
	}
}
