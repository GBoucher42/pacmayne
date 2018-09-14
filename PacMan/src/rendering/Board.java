package rendering;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;

import java.util.Collection;
import java.util.LinkedList;

import entities.Direction;
import entities.EntityManager;
import entities.GameMap;
import entities.PacMan;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public class Board extends Pane implements IBoardRenderer{

	private PacMan pacman;
	private GameMap map;
	private Collection<Sprite> animatedSprites = new LinkedList<Sprite>();
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void drawMaze() 
	{		
		// TODO:
	}
	
	public void spawnAnimatables(EntityManager entityManager)
	{
		for (int i = 0; i < entityManager.count(); ++i)
		{			
			animatedSprites.add(new Sprite(entityManager.getEntity(i), i));
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
		
		// TODO: adopt behavior to current state of state machine 
		
		/*switch(keyCode) {
			case UP:
				pacman.setDirection(Direction.UP);
				candidateTileIndex = pacman.getTileIndex() - GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
				}

				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				candidateTileIndex = pacman.getTileIndex() + GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
				}
				
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				candidateTileIndex = pacman.getTileIndex() - 2;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
				}
				
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
				candidateTileIndex = pacman.getTileIndex();
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
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
		}*/
	}
}
