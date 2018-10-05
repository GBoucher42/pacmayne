package rendering;

import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.HEIGTH_FOOTER;
import static configs.GameConfig.HEIGTH_HEADER;
import static configs.GameConfig.TILE_SIZE;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import components.AudioComponent;
import components.UserInputComponent;
import entities.Entity;
import entities.LivesImages;
import image.FontRepository;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.stage.Stage;
import systemThreads.MessageEnum;
import systemThreads.MessageQueue;;

public class Board extends BorderPane implements IBoardRenderer{
	private boolean isRunning = true;
	private FontRepository fontRepository = new FontRepository();
	private MediaPlayer pacmanEatingPlayer;
	@FXML private ImageView imglogo ;
	private	int life;
	private Pane paneFooter= new Pane();
	private Pane paneHeader =new Pane();
	private Pane pane =new Pane();
	private Pane ScorePane= new Pane();
	Pane livePane =new Pane();
	private char[] pause = {'p', 'a', 'u', 's', 'e'};
	private ArrayList<Sprite> spritesPause;
	private ArrayList<Sprite> spritesScore;
	private Entity pacman;
	private char[] textScore = {'s', 'c', 'o', 'r', 'e'};
	private char[] textLevel = {'l', 'e', 'v', 'e', 'l'};
	private char[] gameOver = {'g', 'a', 'm', 'e', ' ', 'o','v','e','r'};
	private char[] textFps = {'f', 'p', 's'};
	private ArrayList<Sprite> spritesfps;
	private ArrayList<Sprite> spritesLevel;
	private ArrayList<Sprite> spritesGameOver;
	private ArrayList<Sprite> spritesTextScore;
	private ArrayList<Sprite> spritesNumScore;
	private int[]number ;
	private LivesImages imagelives;
	private boolean isPaused = false;
	public Board()
	{	
		pane.setStyle("-fx-background-color: black;");
		loadSounds();		
		spritesPause = createWords(pause, 11*TILE_SIZE + TILE_SIZE/2, 17*TILE_SIZE, pane);
		spritesfps = createWords(textFps, 10, 51, paneHeader);
		hideSprites(spritesPause);
		spritesLevel = createWords(textLevel,170, 0 ,  paneFooter );
		
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
		imglogo.setFitHeight(50);
		imglogo.setFitWidth(GAME_WIDTH);
		paneHeader.getChildren().add(imglogo);
		paneHeader.setStyle("-fx-background-color: black;");
		paneHeader.setPrefSize(GAME_WIDTH,HEIGTH_HEADER);   
		this.setTop(paneHeader);
	}
	private void footer() {
		spritesTextScore= createWords(textScore, 350, 0, ScorePane);
		paneFooter.getChildren().add(ScorePane);
		paneFooter.getChildren().add(livePane);
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
			if(imagelives.getNblives() > 0) {
				isRunning = !isRunning;
				if(isRunning) {
					hideSprites(spritesPause);
				} else {
					displaySprites(spritesPause);
				}
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
			case MINUS:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.VOLUME_DOWN);
				break;
			case PLUS:
			case EQUALS:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.VOLUME_UP);
				break;
			case M:
				MessageQueue.addMessage(pacman, UserInputComponent.class.getName(), MessageEnum.MUTE);
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

	private ArrayList<Sprite> CreateScore(int[] Number,int x, int y, Pane myPane) {

		ArrayList<Sprite> sprites = new ArrayList<Sprite>();
		for(int myScore: Number) { 
			try {
				Sprite num = new Sprite(fontRepository.getFont(myScore), x , y); //12*TILE_SIZE, 17*TILE_SIZE
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
				if(myLetter != ' ') {
					Sprite letter = new Sprite(fontRepository.getFont(myLetter), x , y); //12*TILE_SIZE, 17*TILE_SIZE
					x += TILE_SIZE;
					sprites.add(letter);
					myPane.getChildren().add(letter);
				} else {
					x += TILE_SIZE;
				}
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
	public void initLives(int lives) {
		this.life = lives;
		imagelives = new LivesImages(livePane, this.life);
	}

	@Override
	public void refreshLives(int lives) {
		if (lives == 0 && isRunning == true) {
			this.setRunning(false);
			imagelives.removeLife();
			spritesGameOver = createWords(gameOver, 9 * TILE_SIZE + TILE_SIZE / 2, 17 * TILE_SIZE, pane);
			displaySprites(spritesGameOver);
		} else {

			imagelives.removeLife();

		}

	}
	@Override
	public void displayPause() {
		if(!isPaused) {
			displaySprites(spritesPause);
			isPaused = true;
		}
	}
	@Override
	public void hidePause() {
		if(isPaused) {
			hideSprites(spritesPause);
			isPaused = false;
		}
	}

	@Override
	public void refreshlevel(int level) {
		int []  numLevel =Integer.toString(level).chars().map(c -> c-'0').toArray();
		spritesNumScore=CreateScore(numLevel,280,0, ScorePane);
		
	}

	@Override
	public void refreshFps(int fps) {
		int []  numFPS =Integer.toString(fps).chars().map(c -> c-'0').toArray();
		spritesNumScore=CreateScore(numFPS,100,51,paneHeader);
		
	}


}
