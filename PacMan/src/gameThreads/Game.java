package gameThreads;

import rendering.IBoardRenderer;

import audio.AudioRepository;

import entities.Direction;
import entities.EntityManager;
import entities.PacMan;
import javafx.animation.AnimationTimer;

public class Game {

	private IBoardRenderer board;
	private AudioRepository audioRepository = new AudioRepository();
	private EntityManager entityManager = new EntityManager();
	private PacMan pacman;
	
	public Game(IBoardRenderer board)
	{
		this.board = board;
		this.init();
		System.out.println("Bonjour, c'est gabriel");
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
		pacman = new PacMan(25, 25, 1.0, Direction.RIGHT);
		entityManager.addEntity(pacman);
		//entityManager.addEntity(new Ghost("Inky", 133, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Pinky", 124, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Clyde", 106, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Blinky", 115, 134, 1.0, Direction.UP));
	}
	
	public void run()
	{
		// TODO: start game thread

		new AnimationTimer()
        {
			long lastUpdate = 0;

            public void handle(long currentNanoTime)
            {
            	if (currentNanoTime - lastUpdate < 100000000) {
                    return;
                }
            	board.animate();
				lastUpdate = currentNanoTime;
				update();
            }
        }.start();
	}
	
	private void update()
	{
		board.refreshView();
	}
}
