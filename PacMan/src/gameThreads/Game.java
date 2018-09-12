package gameThreads;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static configs.GameConfig.TILE_SIZE;

import entities.Direction;
import entities.PacMan;
import entities.Velocity;

public class Game {
	
	PacMan pacman;
	Stage stage;

	public Game(Stage stage)
	{
		this.stage = stage;
		init();
	}
	
	public void init()
	{
		addPacMan();
	}
	
	public void run()
	{
		stage.getScene().setOnKeyPressed(this::onKeyPress);
	}
	
	public void onKeyPress(KeyEvent key) {
		KeyCode keyCode = key.getCode();
		
		switch(keyCode) {
			case UP:
				pacman.setDirection(Direction.UP);
				pacman.startMoving();
				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				pacman.startMoving();
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				pacman.startMoving();
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
				pacman.startMoving();
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
	
	public void addPacMan() {
		pacman = new PacMan();
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
