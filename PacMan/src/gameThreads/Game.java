package gameThreads;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.TILE_SIZE;

import java.util.HashMap;
import java.util.Map;

import entities.Direction;
import entities.GameMap;
import entities.PacMan;
import entities.Velocity;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game {
	
	PacMan pacman;
	Stage stage;
	Pane root;
	Text scoreText;
	GameMap map;
	Map<Integer, Shape> gums;
	Map<Integer, Shape> pacGums;
	int score;

	public Game(Stage stage)
	{
		this.map = new GameMap();
		this.stage = stage;
		this.gums = new HashMap<>();
		this.pacGums = new HashMap<>();
		this.score = 0;
		init();
	}
	
	private void init()
	{
		drawMaze();
		addPacMan();
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
					detectGums(candidateTileIndex);
				}

				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				candidateTileIndex = pacman.getTileIndex() + GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
					detectGums(candidateTileIndex);
				}
				
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				candidateTileIndex = pacman.getTileIndex() - 2;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
					detectGums(candidateTileIndex);
				}
				
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
				candidateTileIndex = pacman.getTileIndex();
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.startMoving();
					detectGums(candidateTileIndex);
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
	
	private void detectGums(int index) {
		if(gums.containsKey(index)) {
			eatGum(index);
		} else if(pacGums.containsKey(index)) {
			eatPacGum(index);
		}
	}
	
	private void eatGum(int index) {
		root.getChildren().remove(gums.get(index));
		gums.remove(index);
		score+=10;
		updateScore();
	}
	
	private void eatPacGum(int index) {
		root.getChildren().remove(pacGums.get(index));
		pacGums.remove(index);
		score+=50;
		updateScore();
	}
	
	private void updateScore() {
		scoreText.setText("Score: " + score);
	}
	
	private void drawMaze() {

        int i = 0;
        int x, y;
        root = (Pane) stage.getScene().getRoot();
        
        for (y = 0; y < GAME_TILE_HEIGHT_COUNT; y++) {
            for (x = 0; x < GAME_TILE_WIDTH_COUNT; x++) {
            	if (map.tileGrid[i] == 0) {
            		Rectangle wall = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            		wall.setFill(Color.BLUE);
            		root.getChildren().add(wall);
            	} else if (map.tileGrid[i] == 2) {
            		Circle gum = new Circle(x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE+ TILE_SIZE/2, TILE_SIZE/2);
            		gum.setFill(Color.WHITE);
            		root.getChildren().add(gum);
            		pacGums.put(i, gum);
            	} else {
            		Circle gum = new Circle(x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE+ TILE_SIZE/2, TILE_SIZE/4);
            		gum.setFill(Color.WHITE);
            		root.getChildren().add(gum);
            		gums.put(i, gum);
            	}
               
                i++;
            }
        }
        
        scoreText = new Text(GAME_WIDTH /2 - 50 , GAME_HEIGHT /2, "Score: 0");
        scoreText.setFont(new Font(20));
        root.getChildren().add(scoreText);
    }
	
	private void addPacMan() {
		pacman = new PacMan();
		Rectangle pacmanView = new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
		pacmanView.setFill(Color.YELLOW);
		pacman.setShape(pacmanView);
		Velocity velocity = new Velocity();
		velocity.setSpeed(1);
		pacman.setVelocity(velocity);
		Pane root = (Pane) stage.getScene().getRoot();
		root.getChildren().add(pacman.getShape());
		eatGum(pacman.getTileIndex()-1);
	}
}
