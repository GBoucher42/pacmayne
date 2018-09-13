package gameThreads;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static configs.GameConfig.TILE_SIZE;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;

import entities.Direction;
import entities.GameMap;
import entities.PacMan;
import entities.Velocity;

public class Game {
	
	PacMan pacman;
	Stage stage;
	GameMap map;

	public Game(Stage stage)
	{
		this.map = new GameMap();
		this.stage = stage;
		init();
	}
	
	private void init()
	{
		addPacMan();
		drawMaze();
	}
	
	public void run()
	{
		stage.getScene().setOnKeyPressed(this::onKeyPress);
	}
	
	private void onKeyPress(KeyEvent key) {
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
	}
	
	private void drawMaze() {

        short i = 0;
        int x, y;
        Pane root = (Pane) stage.getScene().getRoot();
        
        for (y = 0; y < GAME_TILE_HEIGHT_COUNT; y++) {
            for (x = 0; x < GAME_TILE_WIDTH_COUNT; x++) {
            	if (map.tileGrid[i] == 0)
            	{
            		Rectangle wall = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            		wall.setFill(Color.BLUE);
            		root.getChildren().add(wall);
            	}
               
                i++;
            }
        }
    }
	
	private void addPacMan() {
		pacman = new PacMan(0, 0);
		Rectangle pacmanView = new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
		pacmanView.setFill(Color.YELLOW);
		pacman.setShape(pacmanView);
		Velocity velocity = new Velocity();
		velocity.setSpeed(1);
		pacman.setVelocity(velocity);
		Pane root = (Pane) stage.getScene().getRoot();
		root.getChildren().add(pacman.getShape());
	}
}
