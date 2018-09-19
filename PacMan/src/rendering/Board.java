package rendering;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;

import java.io.File;
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
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board extends Pane implements IBoardRenderer{

	private PacMan pacman;
	private Maze map;
	private Collection<Sprite> movingSprites = new LinkedList<Sprite>();
	private Map<Tile, Sprite> staticSprites = new HashMap<Tile, Sprite>();
	private boolean isRunning = true;

	private Direction awaitingDirection;
	private int score;
	private Text scoreText;
	MediaPlayer pacmanEatingPlayer;
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void loadSounds() {
		String musicFile = "ressource/audio/pacman-eating.wav"; 
		Media sound = new Media(new File(musicFile).toURI().toString());
		pacmanEatingPlayer = new MediaPlayer(sound);
	}
	
	public void drawMaze(Maze map) 
	{		
		this.map = map;
		Tile[][] tiles = map.getTiles();	
		
		for (int i = 0; i < tiles.length; ++i)
		{
			for (int k = 0; k < tiles[0].length; ++k)
			{
				Sprite sprite = null;
				try {
					// TODO: handle when 0
					sprite = tiles[i][k].getType() == TileType.WALL ? new Sprite(tiles[i][k].getGameEntity(), 1) : new Sprite(tiles[i][k].getCollectable(), 1);
				} catch (Exception e) {
					e.printStackTrace();
				}				 
				
				if (sprite != null) {
					staticSprites.put(tiles[i][k], sprite);
				}				
			}
		}

        scoreText = new Text(GAME_WIDTH /2 - 50 , GAME_HEIGHT /2, "Score: 0");
        scoreText.setFont(new Font(20));
        scoreText.setFill(Color.RED);
        this.getChildren().addAll(staticSprites.values());
        this.getChildren().add(scoreText);
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
		
		this.getChildren().addAll(movingSprites);
	}
	
	public void spawnStaticEntities(EntityManager entityManager)
	{
		// TODO:
	}
	
	public void refreshView()
	{
		animate();
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
			this.getChildren().remove(spriteToRemove);	
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
				pacman.setSpeed(2);
				break;
			case S:
				pacman.setSpeed(1);
				break;
			default:
				break;
			}		
		}
		
	}
	
	private void animate()
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
