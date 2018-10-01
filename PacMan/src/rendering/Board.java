package rendering;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import components.UserInputComponent;
import entities.Entity;
import entities.LivesImages;
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
import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.HEIGTH_FOOTER;
import static configs.GameConfig.HEIGTH_HEADER;;

public class Board extends BorderPane implements IBoardRenderer{
	private boolean isRunning = true;
	private FontRepository fontRepository = new FontRepository();
	private MediaPlayer pacmanEatingPlayer;
	@FXML private ImageView imglogo ;
    private	int lives;
	private Pane paneFooter= new Pane();
	private Pane paneHeader =new Pane();
	private Pane pane =new Pane();
	private Pane ScorePane= new Pane();
	private char[] pause = {'p', 'a', 'u', 's', 'e'};
	private ArrayList<Sprite> spritesPause;
	private ArrayList<Sprite> spritesScore;
	private Entity pacman;
	private char[] TextScore = {'s', 'c', 'o', 'r', 'e'};
	private char[] GameOver = {'g', 'a', 'm', 'e', 'o','v','r','e'};
	private ArrayList<Sprite> spritesGameOver;
	private ArrayList<Sprite> spritesTextScore;
	private ArrayList<Sprite> spritesNumScore;
	int[]number ;
    private  LivesImages imagelives;
	public Board()
	{	
		imagelives=new LivesImages(ScorePane);
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
    imglogo.setFitHeight(HEIGTH_HEADER);
    imglogo.setFitWidth(GAME_WIDTH);
    paneHeader.getChildren().add(imglogo);
    paneHeader.setStyle("-fx-background-color: black;");
    paneHeader.setPrefSize(GAME_WIDTH,HEIGTH_HEADER);   
    this.setTop(paneHeader);
}
	private void footer() {
		spritesTextScore= createWords(TextScore, 350, 0, ScorePane);
	    paneFooter.getChildren().add(ScorePane);
        paneFooter.setStyle("-fx-background-color: black;");
	    paneFooter.setPrefSize(GAME_WIDTH,HEIGTH_FOOTER);
	        this.setBottom(paneFooter);
	        
	      
	}
	
	public void refreshScore(int score) {
		 int []  number =Integer.toString(score).chars().map(c -> c-'0').toArray();
		  spritesNumScore=CreateScore(number,475,0, ScorePane);
		
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
	
	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}	
	
       private ArrayList<Sprite> CreateScore(int[] tabint,int x, int y, Pane myPane) {
		
		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int mySocre: tabint) { 
			try {
				Sprite num = new Sprite(fontRepository.getFont(mySocre), x , y); //12*TILE_SIZE, 17*TILE_SIZE
				x += TILE_SIZE;
				sprites.add(num);
				myPane.getChildren().add(num);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sprites;
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

	@Override
	public void refreshLives(int lives) {
		System.out.println(lives+"-3--");
      if(lives==2) {
    	  System.out.println(lives+"--2--");
    	  imagelives.hideImage(imagelives.getImg3());
      }
      if(lives==1) {
    	  System.out.println(lives+"--1--");
    	  imagelives.hideImage(imagelives.getImg2());
      }
     
      if(lives==0 && isRunning==true ) {
    	 this.setRunning(false);
    	  System.out.println(lives+"--0--");
    	 imagelives.hideImage(imagelives.getImg1());
         spritesGameOver = createWords(GameOver, 11*TILE_SIZE + TILE_SIZE/2, 17*TILE_SIZE, pane);
         displaySprites(spritesGameOver);
        
         
	}
      
	}
	
}
