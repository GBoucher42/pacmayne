package rendering;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import components.UserInputComponent;
import entities.Direction;
import entities.Entity;
import entities.Maze;
import entities.Tile;
import entities.TileType;
import image.FontRepository;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import threads.MessageEnum;
import threads.MessageQueue;
import static configs.GameConfig.TILE_SIZE;


public class Board extends BorderPane implements IBoardRenderer{
	private boolean isRunning = true;
	private FontRepository fontRepository = new FontRepository();
	private int score;
	private MediaPlayer pacmanEatingPlayer;
	private Label scoreText;
	@FXML private ImageView imglogo ;
	private Pane paneFooter= new Pane();
	private Pane paneHeader =new Pane();
	private Pane pane =new Pane();
	
	private char[] pause = {'p', 'a', 'u', 's', 'e'};
	private ArrayList<Sprite> spritesPause;
	
	private Entity pacman;
	
	public Board()
	{
		pane.setStyle("-fx-background-color: black;");
		loadSounds();		
		spritesPause = createWords(pause, 11*TILE_SIZE + TILE_SIZE/2, 17*TILE_SIZE, pane);
		hideSprites(spritesPause);
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
	
	public void refreshScore(int score) {
		scoreText.setText("Score: " + Integer.toString(score));
	}
	
	private void loadSounds() {
		String musicFile = "ressource/audio/pacman-eating.wav"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		pacmanEatingPlayer = new MediaPlayer(sound);
	}
	
	public void spawnAnimatables(List<Sprite> movingSprites)
	{		
		pane.getChildren().addAll(movingSprites);
	}
	
	public void setPacManEntity(Entity pacman) {
		this.pacman = pacman;
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		// TODO: adopt behavior to current state of state machine 
		if(keyCode == keyCode.P) {
			isRunning = !isRunning;
			if(isRunning) {
				hideSprites(spritesPause);
			} else {
				displaySprites(spritesPause);
			}
		}
		if(keyCode == keyCode.F) {
			Stage stage = (Stage) this.getScene().getWindow();
			stage.setFullScreen(!stage.isFullScreen());
		}
		
		if(isRunning) {
			switch(keyCode) {
			case UP:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.UP);
				break;
			case DOWN:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.DOWN);
				break;
			case LEFT:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.LEFT);
				break;
			case RIGHT:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.RIGHT);
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
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}
	
	
	private ArrayList<Sprite> createWords(char[] letters, int x, int y, Pane myPane) {
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(char myLetter: letters) {
			try {
				Sprite letter = new Sprite(fontRepository.getFont(myLetter), x , y); //12*TILE_SIZE, 17*TILE_SIZE
				x += TILE_SIZE;
				sprites.add(letter);
				myPane.getChildren().add(letter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sprites;
	}
	
	private void hideSprites(ArrayList<Sprite> sprites) {
		for(Sprite sprite : sprites) {
			sprite.setVisible(false);
		}
	}
	
	private void displaySprites(ArrayList<Sprite> sprites) {
		for(Sprite sprite : sprites) {
			sprite.setVisible(true);
		}
	}
	
}
