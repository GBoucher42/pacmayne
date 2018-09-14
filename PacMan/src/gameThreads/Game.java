package gameThreads;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import rendering.Board;
import rendering.IBoardRenderer;

import static configs.GameConfig.TILE_SIZE;

import audio.AudioRepository;

import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;

import entities.Direction;
import entities.EntityManager;
import entities.GameMap;
import entities.Ghost;
import entities.PacMan;
import entities.Velocity;

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
		//addPacMan();
		//drawMaze();
		createEntities();
		board.drawMaze();
		board.spawnAnimatables(entityManager);
		board.spawnStaticEntities(entityManager);		
		
		// TODO: start thread
	}

	private void createEntities()
	{
		entityManager.addEntity(new PacMan(120, 204, 1.0, Direction.LEFT));
		entityManager.addEntity(new Ghost("Inky", 133, 134, 1.0, Direction.UP));
		entityManager.addEntity(new Ghost("Pinky", 124, 134, 1.0, Direction.UP));
		entityManager.addEntity(new Ghost("Clyde", 106, 134, 1.0, Direction.UP));
		entityManager.addEntity(new Ghost("Blinky", 115, 134, 1.0, Direction.UP));
	}
	
	public void run()
	{
		//stage.getScene().setOnKeyPressed(this::onKeyPress);
	}
	
	/*private void onKeyPress(KeyEvent key) {
		KeyCode keyCode = key.getCode();
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
	}*/

}
