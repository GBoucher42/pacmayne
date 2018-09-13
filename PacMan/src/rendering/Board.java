package rendering;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;

import java.util.Collection;
import java.util.LinkedList;

import entities.Direction;
import entities.GameMap;
import entities.PacMan;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

public class Board extends Pane{

	private PacMan pacman;
	private GameMap map;
	private Collection<Sprite> animatedSprites;
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
		map = new GameMap();
		pacman = new  PacMan(0, 0); // TODO: give correct coordinests
		//this.getChildren().add(map);
		animatedSprites = new LinkedList<Sprite>();
		
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		int candidateTileIndex = 0;
		
		switch(keyCode) {
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
		}
	}
}
