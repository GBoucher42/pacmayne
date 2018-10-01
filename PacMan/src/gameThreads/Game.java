package gameThreads;

import static configs.GameConfig.BLINKY_SPAWN_POINT_X;
import static configs.GameConfig.BLINKY_SPAWN_POINT_Y;
import static configs.GameConfig.CLYDE_SPAWN_POINT_X;
import static configs.GameConfig.CLYDE_SPAWN_POINT_Y;
import static configs.GameConfig.INKY_SPAWN_POINT_X;
import static configs.GameConfig.INKY_SPAWN_POINT_Y;
import static configs.GameConfig.PACMAN_SPAWN_POINT_X;
import static configs.GameConfig.PACMAN_SPAWN_POINT_Y;
import static configs.GameConfig.PINKY_SPAWN_POINT_X;
import static configs.GameConfig.PINKY_SPAWN_POINT_Y;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import components.GraphicsComponent;
import components.LifeComponent;
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
import systemThreads.AISystem;
import systemThreads.GraphicsSystem;
import systemThreads.LifeSystem;
import systemThreads.MoveSystem;
import systemThreads.PhysicsSystem;
import systemThreads.ScoreSystem;
import systemThreads.UserInputSystem;

public class Game {

	private static final Logger LOGGER = Logger.getLogger( Game.class.getName() );
	private IBoardRenderer board;
	
	private EntityManager entityManager;
	private UserInputSystem userInputSystem;
	private MoveSystem moveSystem;
	private PhysicsSystem physicsSystem;
	private GraphicsSystem graphicsSystem;
	private ScoreSystem scoreSystem;
	private AISystem aiSystem;
	private LifeSystem lifeSystem;
	private Entity pacman;
	private boolean isFocused = true;
	private boolean inView = true;
	private Thread physicsThread;
	
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
		aiSystem = new AISystem(entityManager);
		lifeSystem = new LifeSystem(entityManager);
		
	}
	
	private void createMovableEntities() {
		List<Sprite> sprites = new ArrayList<Sprite>();
		EntityFactory factory = new EntityFactory(entityManager);
		pacman = factory.createPacMan(PACMAN_SPAWN_POINT_X, PACMAN_SPAWN_POINT_Y, Direction.RIGHT);
		factory.createGhost(CLYDE_SPAWN_POINT_X, CLYDE_SPAWN_POINT_Y, Direction.LEFT, "clyde");
		factory.createGhost(BLINKY_SPAWN_POINT_X, BLINKY_SPAWN_POINT_Y, Direction.LEFT, "blinky");
		factory.createGhost(INKY_SPAWN_POINT_X, INKY_SPAWN_POINT_Y, Direction.RIGHT, "inky");
		factory.createGhost(PINKY_SPAWN_POINT_X, PINKY_SPAWN_POINT_Y, Direction.RIGHT, "pinky");
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
		physicsThread = new Thread(physicsSystem);
		physicsThread.start();
		new AnimationTimer()
        {
			long lastUpdate = System.nanoTime();
            public void handle(long now)
            {            
            	if (now - lastUpdate >= 30_000_000) {
            		lastUpdate = System.nanoTime();
            		update();  
            		render();
            		renderLives();
            	}
            }
        }.start();
	}
	
	private int counter = 0;
	private void update() {
		if(board.isRunning() && isFocused && inView) {

			userInputSystem.update();
			moveSystem.update();
			aiSystem.update();
			lifeSystem.update();
			if (counter == 3) {
				counter = 0;			
				graphicsSystem.update();
				scoreSystem.update();
			} else {
				++counter;
			}	
		}
	}
	
	private void render() {
		ScoreComponent score = (ScoreComponent) entityManager.getComponentOfClass(ScoreComponent.class.getName(), pacman);
		
		if (score != null) {
			board.refreshScore(score.getScore());
		}
	}
	private void renderLives() {
		LifeComponent life =(LifeComponent) entityManager.getComponentOfClass(LifeComponent.class.getName(), pacman);
		if (life.getLives()==1||life.getLives()==2||life.getLives()==3||life.getLives()==0) {
	    board.refreshLives(life.getLives());
	    
	   }
	}
	public void stopThreads() {
		physicsSystem.stopThread();
		try {
			physicsThread.join(33);
			if (physicsThread.isAlive())
			{
				physicsThread.interrupt();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setFocus(boolean focus) {
		isFocused = focus;
	}
	
	public void setInView(boolean inView) {
		this.inView = inView;
	}
}
