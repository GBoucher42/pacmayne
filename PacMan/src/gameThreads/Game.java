package gameThreads;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import static configs.GameConfig.GAME_SIZE;
import static configs.GameConfig.TILE_SIZE;

public class Game {
	
	Rectangle pacman;
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
				if(pacman.getY() >= TILE_SIZE)
					pacman.setY(pacman.getY() - 25);
				break;
			case DOWN:
				if(pacman.getY() < GAME_SIZE - TILE_SIZE)
					pacman.setY(pacman.getY() + 25);
				break;
			case LEFT:
				if(pacman.getX() >= TILE_SIZE)
					pacman.setX(pacman.getX() - 25);
				break;
			case RIGHT:
				if(pacman.getX() < GAME_SIZE - TILE_SIZE)
					pacman.setX(pacman.getX() + 25);
				break;
			default:
				break;
		}
	}
	
	public void addPacMan() {
		pacman = new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE);
		pacman.setFill(Color.YELLOW);
		Pane root = (Pane) stage.getScene().getRoot();
		root.getChildren().add(pacman);
	}
}
