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
import components.GraphicsComponent;
import components.MoveComponent;
import components.ScoreComponent;
import entities.Direction;
import entities.Entity;
import entities.EntityManager;
import entities.Maze;
import factories.EntityFactory;
import factories.MazeFactory;
import javafx.animation.AnimationTimer;
import rendering.IBoardRenderer;
import rendering.Sprite;
import systems.GraphicsSystem;
import systems.MoveSystem;
import systems.PhysicsSystem;
import systems.ScoreSystem;
import systems.UserInputSystem;

public class Game {

	private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	private IBoardRenderer board;
	
	private EntityManager entityManager;
	private UserInputSystem userInputSystem;
	private MoveSystem moveSystem;
	private PhysicsSystem physicsSystem;
	private GraphicsSystem graphicsSystem;
	private ScoreSystem scoreSystem;
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
		scoreSystem = new ScoreSystem(entityManager);
	}
	
	private void createMovableEntities() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		EntityFactory factory = new EntityFactory(entityManager);
		pacman = factory.createPacMan(PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y, Direction.RIGHT);
		factory.createGhost(CLYDE_SPAWN_POINT_X, CLYDE_SPAWN_POINT_Y, Direction.DOWN, "clyde");
		factory.createGhost(BLINKY_SPAWN_POINT_X, BLINKY_SPAWN_POINT_Y, Direction.UP, "blinky");
		factory.createGhost(INKY_SPAWN_POINT_X, INKY_SPAWN_POINT_Y, Direction.RIGHT, "inky");
		factory.createGhost(PINKY_SPAWN_POINT_X, PINKY_SPAWN_POINT_Y, Direction.LEFT, "pinky");
		board.setPacManEntity(pacman);
		
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
            		render();
            	}
            }
        }.start();
	}
	
	private int counter = 1;
	private void update() {
		
		userInputSystem.update();
		moveSystem.update();
		physicsSystem.update();
		
		if (counter % 3== 0) {
			counter = 1;			
			graphicsSystem.update();
			scoreSystem.update();
		} else {
			++counter;
		}		
	}
	
	private void render() {
		ScoreComponent score = (ScoreComponent) entityManager.getComponentOfClass(ScoreComponent.class.getName(), pacman);
		
		if (score != null) {
			board.refreshScore(score.getScore());
		}
	}
}
