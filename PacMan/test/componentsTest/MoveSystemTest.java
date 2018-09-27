package componentsTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import components.EntityFactory;
import components.EntityManager;
import components.MoveSystem;
import entities.Direction;
import entities.GameEntity;
import entities.Maze;
import entities.Tile;
import entities.TileType;

@TestInstance(Lifecycle.PER_CLASS)
class MoveSystemTest {
	Maze maze;
	MoveSystem system;
	EntityManager entityManager;
	EntityFactory factory;
	Tile tile1;
	Tile tile2; 
	Tile tile3; 
	Tile tile4;
	Tile tile5; 
	Tile tile6; 
	Tile tile7; 
	Tile tile8; 
	Tile tile9;
	
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
		maze.addTile(tile1, 0, 0);
		maze.addTile(tile2, 0, 1);
		maze.addTile(tile3, 0, 2);
		maze.addTile(tile4, 1, 0);
		maze.addTile(tile5, 1, 1);
		maze.addTile(tile6, 1, 2);
		maze.addTile(tile7, 2, 0);
		maze.addTile(tile8, 2, 1);
		maze.addTile(tile9, 2, 1);
	}

	
	@BeforeAll
	void setup() {
		maze = new Maze();
		initTiles();
		entityManager = new EntityManager();
		system = new MoveSystem(entityManager, maze);
		factory = new EntityFactory(entityManager);
		
	}
	
	@Test
	public void willNotCollide() {
		factory.createPacMan(2, 0, Direction.LEFT);
		for(int i = 0; i<= 10; i++) {
			system.update();
		}
		
	}

}
