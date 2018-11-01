package componentsTest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.Direction;
import entities.Entity;
import entities.EntityManager;
import entities.Maze;
import entities.Tile;
import entities.TileType;
import factories.EntityFactory;
import systemThreads.MoveSystem;

@TestInstance(Lifecycle.PER_CLASS)
class MoveSystemTest {
	Maze maze;
	MoveSystem system;
	EntityManager entityManager;
	EntityFactory factory;
	Entity pacman;
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
		tile2 = new Tile(0, 1, TileType.VOID);
		tile3 = new Tile(0, 2, TileType.WALL);
		tile4 = new Tile(1, 0, TileType.VOID);
		tile5 = new Tile(1, 1, TileType.VOID);
		tile6 = new Tile(1, 2, TileType.WALL);
		tile7 = new Tile(2, 0, TileType.WALL);
		tile8 = new Tile(2, 1, TileType.VOID);
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
		factory = new EntityFactory(entityManager);
		pacman = factory.createPacMan(1, 1 , Direction.RIGHT);
		system = new MoveSystem(entityManager, maze, pacman);
		
	}
	
	@Test
	public void willNotCollide() {
		factory.createPacMan(2, 0, Direction.LEFT);
		for(int i = 0; i<= 10; i++) {
			system.update();
		}
		
	}

}
