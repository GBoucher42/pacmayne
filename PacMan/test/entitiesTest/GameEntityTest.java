package entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entities.Animatable;
import entities.Direction;
import entities.GameEntity;
import entities.PacMan;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;

@TestInstance(Lifecycle.PER_CLASS)
class GameEntityTest {
	GameEntity gameEntity;
	@Mock Animatable animatable;
	
	@BeforeAll
	void setup() {
		MockitoAnnotations.initMocks(this);
		gameEntity = new PacMan(1, 1, animatable);
		gameEntity.setIsMoving(true);
	}
	
	
	@BeforeEach
	void reset() {
		gameEntity.setCurrentX(1); //reinit position before each test
		gameEntity.setCurrentY(1);
	}
	
	@Nested
	class willMove{
		@Test
		void willMoveDown() {
			gameEntity.setDirection(Direction.DOWN);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(2, gameEntity.getCurrentY());
		}
		
		@Test
		void willMoveUp() {
			gameEntity.setDirection(Direction.UP);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(0, gameEntity.getCurrentY());
		}
		
		@Test
		void willMoveRight() {
			gameEntity.setDirection(Direction.RIGHT);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(2, gameEntity.getCurrentX());
		}
		
		@Test
		void willMoveLeft() {
			gameEntity.setDirection(Direction.LEFT);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(0, gameEntity.getCurrentX());
		}
	}
	
	@Nested
	class willNotMove {
		@Test
		void isNotMoving() {
			gameEntity.setIsMoving(false);
			gameEntity.setDirection(Direction.LEFT);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(1, gameEntity.getCurrentX());
		}
		
		@Test
		void willNotMoveIfNoDirections() {
			gameEntity.setDirection(Direction.NONE);
			gameEntity.moveOneFrameBySpeed();
			assertEquals(1, gameEntity.getCurrentX());
			assertEquals(1, gameEntity.getCurrentY());
		}
	}
	
	@Nested
	class willPassTunnels {
		@Test
		void willPassLowerBoundTunnel() {
			gameEntity.setDirection(Direction.DOWN);
			gameEntity.passTunnel();
			assertEquals(1 - GAME_TILE_HEIGHT_COUNT, gameEntity.getCurrentY());
		}
		
		@Test
		void willPassUpperBoundTunnel() {
			gameEntity.setDirection(Direction.UP);
			gameEntity.passTunnel();
			assertEquals(1 + GAME_TILE_HEIGHT_COUNT, gameEntity.getCurrentY());
		}
		
		@Test
		void willPassRightBoundTunnel() {
			gameEntity.setDirection(Direction.RIGHT);
			gameEntity.passTunnel();
			assertEquals(1 - GAME_TILE_WIDTH_COUNT, gameEntity.getCurrentX());
		}
		
		@Test
		void willPassLeftBoundTunnel() {
			gameEntity.setDirection(Direction.LEFT);
			gameEntity.passTunnel();
			assertEquals(1 + GAME_TILE_WIDTH_COUNT, gameEntity.getCurrentX());
		}
	}
	
	

}
