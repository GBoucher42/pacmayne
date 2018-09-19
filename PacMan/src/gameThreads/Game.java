package gameThreads;

import static configs.GameConfig.PACMAN_SPAWN_POINT_X;
import static configs.GameConfig.PACMAN_SPAWN_POINT_Y;

import audio.AudioRepository;
import entities.EntityManager;
import entities.GameEntityType;
import entities.IGameEntity;
import entities.Maze;
import factories.GameEntityFactory;
import factories.MazeFactory;
import javafx.animation.AnimationTimer;
import rendering.IBoardRenderer;

public class Game {

	private IBoardRenderer board;
	private AudioRepository audioRepository = new AudioRepository();
	private EntityManager entityManager = new EntityManager();
	private IGameEntity pacman;
	Maze map;
	
	public Game(IBoardRenderer board)
	{
		this.board = board;
		this.init();
	}

	private void init()
	{
		createEntities();
		
		try {
			map = MazeFactory.BuildMaze();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		board.drawMaze(map);
		board.loadSounds();
		board.spawnAnimatables(entityManager);
		board.spawnStaticEntities(entityManager);		
	}

	private void createEntities()
	{
		pacman = GameEntityFactory.createGameEntity(GameEntityType.PACMAN, PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y);
		entityManager.addEntity(pacman);
		//entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.INKY, PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y));
		//entityManager.addEntity(new Ghost("Pinky", 124, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Clyde", 106, 134, 1.0, Direction.UP));
		//entityManager.addEntity(new Ghost("Blinky", 115, 134, 1.0, Direction.UP));
	}

	public void run()
	{
		new AnimationTimer()
        {
			long lastUpdate = 0 ;
            public void handle(long now)
            {            
            	if (now - lastUpdate >= 30_000_000) {
            		update();
                	render();
                	lastUpdate = System.nanoTime();
            	}
            }
        }.start();
	}
	
	private void update() {
		board.animate();
	}
	
	private void render() {
		board.refreshView();
	}
}
