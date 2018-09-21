package gameThreads;

import static configs.GameConfig.PACMAN_SPAWN_POINT_X;
import static configs.GameConfig.PACMAN_SPAWN_POINT_Y;
import static configs.GameConfig.BLINKY_SPAWN_POINT_X;
import static configs.GameConfig.BLINKY_SPAWN_POINT_Y;
import static configs.GameConfig.CLYDE_SPAWN_POINT_X;
import static configs.GameConfig.CLYDE_SPAWN_POINT_Y;
import static configs.GameConfig.INKY_SPAWN_POINT_X;
import static configs.GameConfig.INKY_SPAWN_POINT_Y;
import static configs.GameConfig.PINKY_SPAWN_POINT_X;
import static configs.GameConfig.PINKY_SPAWN_POINT_Y;

import java.util.logging.Level;
import java.util.logging.Logger;

import audio.AudioRepository;
import entities.EntityManager;
import entities.GameEntityType;
import entities.Maze;
import factories.GameEntityFactory;
import factories.MazeFactory;
import javafx.animation.AnimationTimer;
import rendering.IBoardRenderer;

public class Game {

	private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	private IBoardRenderer board;
	private AudioRepository audioRepository = new AudioRepository();
	private EntityManager entityManager = new EntityManager();
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
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		
		board.drawMaze(map);
		board.loadSounds();
		board.spawnAnimatables(entityManager);
		board.spawnStaticEntities(entityManager);		
	}

	private void createEntities()
	{
		entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.PACMAN, PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y));
		entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.INKY, INKY_SPAWN_POINT_X, INKY_SPAWN_POINT_Y));
		entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.PINKY, PINKY_SPAWN_POINT_X, PINKY_SPAWN_POINT_Y));
		entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.BLINKY, BLINKY_SPAWN_POINT_X, BLINKY_SPAWN_POINT_Y));
		entityManager.addEntity(GameEntityFactory.createGameEntity(GameEntityType.CLYDE, CLYDE_SPAWN_POINT_X, CLYDE_SPAWN_POINT_Y));
	}

	public void run()
	{
		new AnimationTimer()
        {
			long lastUpdate = System.nanoTime();
            public void handle(long now)
            {            
            	if (now - lastUpdate >= 30_000_000) {
            		lastUpdate = System.nanoTime();
            		update();
                	render();                	
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
