package componentsTest;

import static java.time.Duration.ofMillis;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTimeout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import components.AudioComponent;
import components.GraphicsComponent;
import components.PhysicsComponent;
import entities.Entity;
import entities.EntityManager;
import factories.EntityFactory;
import systemThreads.GameAudioSystem;
import systemThreads.GraphicsSystem;
import systemThreads.MessageEnum;
import systemThreads.MessageQueue;

@TestInstance(Lifecycle.PER_CLASS)
public class GraphicSystemTest {
	
	private GraphicsSystem graphicsSystem;
	private EntityManager entityManager;
	private EntityFactory factory;
	private Thread tGraphicsSystem;
	private Entity entity;
	
	private void addMessageQueue() {
		
		MessageQueue.addMessage(entity, GraphicsComponent.class.getName() , MessageEnum.EATEN);
	}
	
	@BeforeAll
	void setupBeforeClass() {
		entityManager = new EntityManager();
		factory = new EntityFactory(entityManager);
		entity = factory.createSuperGum(2, 5);
	}


	@BeforeEach
	void setUpBeforeEach() throws InterruptedException {
		graphicsSystem = new GraphicsSystem(entityManager);
		tGraphicsSystem = new Thread(graphicsSystem);
		tGraphicsSystem.start();
		tGraphicsSystem.join(100);
		assertTrue("thread dead", tGraphicsSystem.isAlive());
	}

	@AfterEach
	void tearDownAfterEach() throws Exception {
		boolean expectedStop = true;
		boolean actualStop = tGraphicsSystem.isAlive();
		if(actualStop) {
			graphicsSystem.stopThread();
			try {
				tGraphicsSystem.join(1500);
				actualStop = !tGraphicsSystem.isAlive(); //check if thread is dead
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals("thread not stopped" , expectedStop, actualStop);
		}
	}

	@Test
	void entityEaten() {
		assertTimeout(ofMillis(10000), () -> {
			addMessageQueue();
			Thread.sleep(100);
			List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(GraphicsComponent.class.getName());
			assertEquals(0, entities.size());
		});
	}
	
	@Test
	void updatedGraphic() {
		assertTimeout(ofMillis(10000), () -> {
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			String currentAnimation = graphic.getCurrentImage();
			Thread.sleep(200);
			assertNotEquals(currentAnimation, graphic.getCurrentImage());
		});
	}

}
