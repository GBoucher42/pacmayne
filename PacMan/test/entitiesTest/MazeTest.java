package entitiesTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import entities.CollisionType;
import entities.Direction;
import entities.GameEntity;
import entities.Maze;
import entities.PacMan;
import entities.Tile;
import entities.TileType;

import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;

@TestInstance(Lifecycle.PER_CLASS)
class MazeTest {
	Maze maze;
	Tile tile1;
	Tile tile2; 
	Tile tile3; 
	Tile tile4;
	Tile tile5; 
	Tile tile6; 
	Tile tile7; 
	Tile tile8; 
	Tile tile9;
	@Mock
	GameEntity gameEntity;
	
	@BeforeAll
	void setup() {
		MockitoAnnotations.initMocks(this);
		maze = new Maze();
		initTiles();
	}
	
	void initTiles() {
		tile1 = new Tile(0, 0, TileType.WALL);
		tile2 = new Tile(0, 1, TileType.CORRIDOR);
		tile3 = new Tile(0, 2, TileType.WALL);
		tile4 = new Tile(1, 0, TileType.CORRIDOR);
		tile5 = new Tile(1, 1, TileType.CORRIDOR);
		tile6 = new Tile(1, 2, TileType.WALL);
		tile7 = new Tile(2, 0, TileType.WALL);
		tile8 = new Tile(2, 1, TileType.CORRIDOR);
		tile9 = new Tile(2, 2, TileType.WALL);
		//w-c-w
		//c-c-w
		//w-c-w
	}

	
	@Nested
	class addTiles{
		@Test
		void willAddTile() {
			maze.addTile(tile1, 0, 0);
			assertEquals(tile1, maze.getTile(0, 0));
		}
		
		@Test
		void willNotAddNullTile() {
			maze.addTile(null, 1, 0);
			assertNull(maze.getTile(1, 0));
		}
		
		@Test
		void willNotAddTilOutsideRowRange() {
			maze.addTile(tile1, -1, 0);
			maze.addTile(tile1, GAME_TILE_HEIGHT_COUNT + 1, 0);
			assertNull(maze.getTile(-1, 0));
			assertNull(maze.getTile(GAME_TILE_HEIGHT_COUNT + 1, 0));
		}
		
		@Test
		void willNotAddTilOutsideColumnRange() {
			maze.addTile(tile1, 0, -1);
			maze.addTile(tile1, 0, GAME_TILE_WIDTH_COUNT + 1);
			assertNull(maze.getTile(0, -1));
			assertNull(maze.getTile(0, GAME_TILE_WIDTH_COUNT + 1));
		}
	}
	
	@Nested
	@TestInstance(Lifecycle.PER_CLASS)
	class detectCollision{
		@BeforeAll
		void setup() {
			maze.addTile(tile1, 0, 0);
			maze.addTile(tile2, 0, 1);
			maze.addTile(tile3, 0, 2);
			maze.addTile(tile4, 1, 0);
			maze.addTile(tile5, 1, 1);
			maze.addTile(tile6, 1, 2);
			maze.addTile(tile7, 2, 0);
			maze.addTile(tile8, 2, 1);
			maze.addTile(tile9, 2, 2);
			
			when(gameEntity.getCurrentX()).thenReturn(0); //init for no NPE
			when(gameEntity.getCurrentY()).thenReturn(0);
		}
		
		@Test
		void willNotValidateWithNoDirection() {
			assertEquals(CollisionType.NONE, maze.validateMove(gameEntity, Direction.NONE));
		}
		
		@Nested
		class detectCollisionMovingRight{
			
			@Test
			void willNotCollide() {
				when(gameEntity.getCurrentX()).thenReturn(0);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.NONE, maze.validateMove(gameEntity, Direction.RIGHT));
			}
			
			@Test
			void willCollideWithWall() {
				when(gameEntity.getCurrentX()).thenReturn(1);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.COLLIDEWALL, maze.validateMove(gameEntity, Direction.RIGHT));
			}
			
			@Test
			void willCollideWithOverbound() {
				when(gameEntity.getCurrentX()).thenReturn(GAME_TILE_WIDTH_COUNT);
				assertEquals(CollisionType.OVERBOUND, maze.validateMove(gameEntity, Direction.RIGHT));
			}
		}
		
		@Nested
		class detectCollisionMovingLeft{
			
			@Test
			void willNotCollide() {
				when(gameEntity.getCurrentX()).thenReturn(1);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.NONE, maze.validateMove(gameEntity, Direction.LEFT));
			}
			
			@Test
			void willCollideWithWall() {
				when(gameEntity.getCurrentX()).thenReturn(1);
				when(gameEntity.getCurrentY()).thenReturn(0);
				assertEquals(CollisionType.COLLIDEWALL, maze.validateMove(gameEntity, Direction.LEFT));
			}
			
			@Test
			void willCollideWithOverbound() {
				when(gameEntity.getCurrentX()).thenReturn(0);
				assertEquals(CollisionType.OVERBOUND, maze.validateMove(gameEntity, Direction.LEFT));
			}
		}
		
		@Nested
		class detectCollisionMovingUp{
			
			@Test
			void willNotCollide() {
				when(gameEntity.getCurrentX()).thenReturn(1);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.NONE, maze.validateMove(gameEntity, Direction.UP));
			}
			
			@Test
			void willCollideWithWall() {
				when(gameEntity.getCurrentX()).thenReturn(0);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.COLLIDEWALL, maze.validateMove(gameEntity, Direction.UP));
			}
			
			@Test
			void willCollideWithOverbound() {
				when(gameEntity.getCurrentY()).thenReturn(0);
				assertEquals(CollisionType.OVERBOUND, maze.validateMove(gameEntity, Direction.UP));
			}
		}
		
		@Nested
		class detectCollisionMovingDown{
			
			@Test
			void willNotCollide() {
				when(gameEntity.getCurrentX()).thenReturn(1);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.NONE, maze.validateMove(gameEntity, Direction.DOWN));
			}
			
			@Test
			void willCollideWithWall() {
				when(gameEntity.getCurrentX()).thenReturn(0);
				when(gameEntity.getCurrentY()).thenReturn(1);
				assertEquals(CollisionType.COLLIDEWALL, maze.validateMove(gameEntity, Direction.DOWN));
			}
			
			@Test
			void willCollideWithOverbound() {
				when(gameEntity.getCurrentY()).thenReturn(GAME_TILE_HEIGHT_COUNT);
				assertEquals(CollisionType.OVERBOUND, maze.validateMove(gameEntity, Direction.DOWN));
			}
		}
		
	}
	
	

}
