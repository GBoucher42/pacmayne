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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import audio.AudioRepository;
import components.Entity;
import components.EntityFactory;
import components.EntityManager;
import components.GraphicsComponent;
import components.GraphicsSystem;
import components.MoveComponent;
import components.MoveSystem;
import components.PhysicsSystem;
import components.Sprite;
import components.UserInputSystem;
import entities.CollisionType;
import entities.Direction;
import entities.Maze;
import factories.MazeFactory;
import image.FontRepository;
import javafx.animation.AnimationTimer;
import rendering.IBoardRenderer;

public class Game {

	private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	private IBoardRenderer board;
	private FontRepository fontRepository = new FontRepository();
	
	private EntityManager entityManager;
	private UserInputSystem userInputSystem;
	private MoveSystem moveSystem;
	private PhysicsSystem physicsSystem;
	private GraphicsSystem graphicsSystem;
	private Entity pacman;
	
	Maze map;
	
	public Game(IBoardRenderer board)
	{
		this.board = board;
		this.init();
	}

	private void init()
	{
		entityManager = new EntityManager();
		
		try {
			map = MazeFactory.BuildMaze(entityManager);			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage());
		}
		
		buildMaze();
		createMovableEntities();
		initSystems();
		
		board.loadSounds();	
	}
	
	private void buildMaze() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(GraphicsComponent.class.getName());
		
		for(Entity entity: entities) {	
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			sprites.add(graphic.getSprite());			
		}	
		
		board.drawMaze(sprites);
	}
	
	private void initSystems() {
		userInputSystem = new UserInputSystem(entityManager);
		moveSystem = new MoveSystem(entityManager, map);
		physicsSystem = new PhysicsSystem(entityManager, pacman);
		graphicsSystem = new GraphicsSystem(entityManager);
	}
	
	private void createMovableEntities() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		pacman = new EntityFactory(entityManager).createPacMan(PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y, Direction.RIGHT);
		// Ghosts here
		
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(MoveComponent.class.getName());
		
		for(Entity entity: entities) {
			GraphicsComponent graphic = (GraphicsComponent) entityManager.getComponentOfClass(GraphicsComponent.class.getName(), entity);
			
			if (graphic == null) {
				continue;
			}
			
			sprites.add(graphic.getSprite());			
		}
		
		board.spawnAnimatables(sprites);
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
            	}
            }
        }.start();
	}
	
	private void update() {
		userInputSystem.update();
		moveSystem.update();
		physicsSystem.update();
		graphicsSystem.update();
	}
}
