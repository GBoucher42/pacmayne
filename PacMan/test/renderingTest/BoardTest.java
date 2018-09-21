package renderingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import entities.Collectable;
import entities.GameEntity;
import entities.Maze;
import entities.Tile;
import entities.TileType;
import javafx.scene.layout.Pane;
import rendering.Board;

@TestInstance(Lifecycle.PER_CLASS)
class BoardTest {
	Maze maze;
	Board board;
	
	@Mock
	GameEntity entity;
	
	@Mock
	Collectable collectable;
	
	//@Mock
	Tile tile1;
	//@Mock
	Tile tile2; 
	//@Mock
	Tile tile3;
	
	@Rule public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@BeforeAll
	public void setup() {
	MockitoAnnotations.initMocks(this);	
	maze = new Maze();
	board = new Board();
	initGameEntities();
	initTiles();
	initMaze();
	}
	
	void initGameEntities() {

	}
	
	void initTiles() {
		tile1 = new Tile(0, 0, TileType.WALL);
		tile2 = new Tile(0, 1, TileType.CORRIDOR);
		tile3 = new Tile(0, 2, TileType.VOID);
		//w-c-v
		
		when(tile1.getGameEntity()).thenReturn(entity);
		when(tile1.getCollectable()).thenReturn(collectable);
		when(tile2.getGameEntity()).thenReturn(entity);
		when(tile2.getCollectable()).thenReturn(collectable);
		when(tile3.getGameEntity()).thenReturn(entity);
		when(tile3.getCollectable()).thenReturn(collectable);
	}
	
	void initMaze() {
		maze.addTile(tile1, 0, 0);
		maze.addTile(tile2, 0, 1);
		maze.addTile(tile3, 0, 2);
	}
	
	@Nested
	class drawMaze {
		
		@Test
		public void drawMazeTest() {
			board.drawMaze(maze);
			assertEquals(((Pane)board.getCenter()).getChildren().size(), 8);
		}
		
	}
}
