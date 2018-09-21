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
import java.util.Map;

import entities.CollisionType;
import entities.Direction;
import entities.EntityManager;
import entities.IGameEntity;
import entities.Maze;
import entities.PacMan;
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

	private PacMan pacman;
	private Maze map;
	private Collection<Sprite> movingSprites = new LinkedList<Sprite>();
	private Map<Tile, Sprite> staticSprites = new HashMap<Tile, Sprite>();
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
	
	public void drawMaze(Maze map) 
	{	
		this.setCenter(pane);
		this.map = map;
		Tile[][] tiles = map.getTiles();	
		
		for (int i = 0; i < tiles.length; ++i)
		{
			for (int k = 0; k < tiles[0].length; ++k)
			{
				if (tiles[i][k].getType() != TileType.VOID) {
					Sprite sprite = tiles[i][k].getType() == TileType.WALL ? new Sprite(tiles[i][k].getGameEntity(), 1) : new Sprite(tiles[i][k].getCollectable(), 1);				
					staticSprites.put(tiles[i][k], sprite);
				}								
			}
		}

       pane.getChildren().addAll(staticSprites.values());
       	footer();
       header();
	}
	
    void header() {
	 
    Image image = new Image("file:ressource/sprites/logo.png");
    imglogo = new ImageView();
    imglogo.setImage(image);
    imglogo.setFitHeight(75);
    paneHeader.getChildren().add(imglogo);
    paneHeader.setStyle("-fx-background-color: black;");
    paneHeader.setPrefSize(700,75);   
    this.setTop(paneHeader);
}
	void footer() {
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
	public void spawnAnimatables(EntityManager entityManager)
	{
		for (int i = 0; i < entityManager.count(); ++i)
		{		
			IGameEntity entity = entityManager.getEntity(i);
			if (entity.getAnimatable() != null)
			{
				movingSprites.add(new Sprite(entity, i));
				
				if (pacman == null && entity.getClass() == PacMan.class)
				{
					pacman = (PacMan) entity;
					consumeGums();
				}
			}				
		}
		
		pane.getChildren().addAll(movingSprites);
	}
	
	public void spawnStaticEntities(EntityManager entityManager)
	{
		// TODO:
	}
	
	public void refreshView()
	{
		//animate();
		for (Sprite sprite : staticSprites.values())
		{
			sprite.updateAvatar();
		}
		
		for (Sprite sprite : movingSprites)
		{
			sprite.updateAvatar();
			sprite.updatePosition();
			consumeGums();
		}
	}
	
	private void consumeGums() {
		Tile tile = map.getTile(pacman.getCurrentY(), pacman.getCurrentX());
		if(tile == null) { // when you pass through the tunnels
			return;
		}
		if (tile.hasCollectable()) {
			updateScore(tile.consumeCollectable());	
			playEatingAudio();
			Sprite spriteToRemove = staticSprites.get(tile);
			staticSprites.remove(tile);			
			pane.getChildren().remove(spriteToRemove);	
		} else {
			stopEatingAudio();
		}
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
			case S:
				pacman.setSpeed(1);
				break;
			default:
				break;
			}		
		}
		
	}
	
	public void animate()
	{
		if(isRunning) {			
		
		//TODO: Animate ALL animatable sprites if able/valid
		CollisionType type;
		
		if (awaitingDirection != null) {
			type = map.validateMove(pacman, awaitingDirection);
			
			if (type != CollisionType.COLLIDEWALL) { 
				pacman.setDirection(awaitingDirection);
				awaitingDirection = null;
			}
		} 

		type = map.validateMove(pacman, pacman.getDirection());			
		
		if (type == CollisionType.NONE)
		{
			pacman.setIsMoving(true);
			pacman.moveOneFrameBySpeed();
		} else if (type == CollisionType.COLLIDEWALL)
		{
			pacman.setIsMoving(false);
		} else if (type == CollisionType.OVERBOUND) {
			pacman.passTunnel();
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
