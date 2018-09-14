package gameThreads;

import rendering.IBoardRenderer;

import audio.AudioRepository;

import entities.Direction;
import entities.EntityManager;
import entities.PacMan;

public class Game {

	IBoardRenderer board;
	AudioRepository audioRepository = new AudioRepository();
	EntityManager entityManager = new EntityManager();

	
	public Game(IBoardRenderer board)
	{
		this.board = board;
		this.init();
	}
	
	private void init()
	{
		createEntities();
		board.drawMaze();
		board.spawnAnimatables(entityManager);
		board.spawnStaticEntities(entityManager);		
	}

	private void createEntities()
	{
		entityManager.addEntity(new PacMan(25, 25, 1.0, Direction.LEFT));
		//entityManager.addEntity(new Ghost("Inky", 133, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Pinky", 124, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Clyde", 106, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Blinky", 115, 134, 1.0, Direction.UP));
	}
	
	public void run()
	{
		// TODO: start thread
	}
}
