package rendering;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.TILE_SIZE;

import java.util.Collection;
import java.util.LinkedList;

import entities.Animatable;
import entities.Direction;
import entities.EntityManager;
import entities.GameEntity;
import entities.GameMap;
import entities.PacMan;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Board extends Pane implements IBoardRenderer{

	private PacMan pacman;
	private GameMap map = new GameMap();
	private Collection<Sprite> animatedSprites = new LinkedList<Sprite>();
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void drawMaze() 
	{		
		int i = 0;
		for (int y = 0; y < GAME_TILE_HEIGHT_COUNT; ++y) {
            for (int x = 0; x < GAME_TILE_WIDTH_COUNT; ++x) {
            	if (map.tileGrid[i] == 0)
            	{
            		Rectangle wall = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            		wall.setFill(Color.BLUE);
            		this.getChildren().add(wall);
            	}
               
                i++;
            }
        }
	}
	
	public void spawnAnimatables(EntityManager entityManager)
	{
		for (int i = 0; i < entityManager.count(); ++i)
		{		
			GameEntity entity = entityManager.getEntity(i);
			if (entity instanceof Animatable)
			{
				animatedSprites.add(new Sprite(entity, i));
				
				if (pacman == null && entity.getClass() == PacMan.class)
				{
					pacman = (PacMan) entity;
				}
			}				
		}
		
		this.getChildren().addAll(animatedSprites);
	}
	
	public void spawnStaticEntities(EntityManager entityManager)
	{
		
	}
	
	// To be called by the game thread update() method
	public void refreshView()
	{
		for (Sprite sprite : animatedSprites)
		{
			sprite.updatePosition();
		}
		
		// TODO: force a refresh of the scene when the game thread updates
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		int candidateTileIndex = 0;
		pacman.setIsMoving(true);
		// TODO: adopt behavior to current state of state machine 
		
		switch(keyCode) {
			case UP:
				pacman.setDirection(Direction.UP);
				candidateTileIndex = pacman.getTileIndex() - GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
				}

				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				candidateTileIndex = pacman.getTileIndex() + GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
				}
				
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				candidateTileIndex = pacman.getTileIndex() - 2;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
				}
				
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
				candidateTileIndex = pacman.getTileIndex();
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
				}
				
				break;
			case F:
				pacman.setSpeed(2);
				break;
			case S:
				pacman.setSpeed(1);
				break;
			default:
				break;
		}
		
		refreshView();
	}
}
