package rendering;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import components.Sprite;
import entities.CollisionType;
import entities.Direction;
import entities.Maze;
import entities.Tile;
import entities.TileType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Board extends BorderPane implements IBoardRenderer{
	private boolean isRunning = true;

	private Direction awaitingDirection;
	private int score;
	MediaPlayer pacmanEatingPlayer;
	private Label scoreText;
	@FXML private ImageView imglogo ;
	Pane paneFooter= new Pane();
	Pane paneHeader =new Pane();
	Pane pane =new Pane();
	
	public Board()
	{
		pane.setStyle("-fx-background-color: black;");
		
	}
	
	public void loadSounds() {
		String musicFile = "ressource/audio/pacman-eating.wav"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		pacmanEatingPlayer = new MediaPlayer(sound);
	}
	
	public void drawMaze(List<Sprite> sprites) 
	{	
		this.setCenter(pane);		

       pane.getChildren().addAll(sprites);
       footer();
       header();
	}
	
    private void header() {
	 
    Image image = new Image("file:ressource/sprites/logo.png");
    imglogo = new ImageView();
    imglogo.setImage(image);
    imglogo.setFitHeight(75);
    paneHeader.getChildren().add(imglogo);
    paneHeader.setStyle("-fx-background-color: black;");
    paneHeader.setPrefSize(700,75);   
    this.setTop(paneHeader);
}
	private void footer() {
		 scoreText = new Label(); 
	        scoreText.setStyle("-fx-font-size: 32px;"
	        		+ "-fx-font-family: \"Comic Sans MS\";"
	                + "-fx-effect: innershadow( three-pass-box , rgba(0,0,0,0.7) , 6, 0.0 , 0 , 2 );"
	        		+ "-fx-font-weight: bold");
	        scoreText.setTextFill(Color.RED);
	        scoreText.setText("Score: 0");
	        scoreText.setAlignment(Pos.CENTER);
	        paneFooter.getChildren().add(scoreText);
	        paneFooter.setStyle("-fx-background-color: black;");
	        paneFooter.setPrefSize(700,50);        
	        this.setBottom(paneFooter);
	      
	}
	
	public void spawnAnimatables(List<Sprite> movingSprites)
	{		
		pane.getChildren().addAll(movingSprites);
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		// TODO: adopt behavior to current state of state machine 
		if(keyCode == keyCode.P) {
			isRunning = !isRunning;
		}
		if(isRunning) {
			switch(keyCode) {
			case UP:
				awaitingDirection = Direction.UP;
				break;
			case DOWN:
				awaitingDirection = Direction.DOWN;
				break;
			case LEFT:
				awaitingDirection = Direction.LEFT;
				break;
			case RIGHT:
				awaitingDirection = Direction.RIGHT;
				break;
			case F:
				Stage stage = (Stage) this.getScene().getWindow();
				stage.setFullScreen(!stage.isFullScreen());
				break;
			default:
				break;
			}		
		}
		
	}
	
	private void playEatingAudio() {
		if(!Status.PLAYING.equals(pacmanEatingPlayer.getStatus())) {
			pacmanEatingPlayer.play();
		}
	}
	
	private void stopEatingAudio() {
		if(Status.PLAYING.equals(pacmanEatingPlayer.getStatus())) {
			pacmanEatingPlayer.stop();
		}
	}
	
	private void updateScore(int value) {
		score += value;
		scoreText.setText("Score: " + score);
	}
}
