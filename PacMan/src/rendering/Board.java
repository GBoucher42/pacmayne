package rendering;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.TILE_SIZE;

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
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board extends Pane implements IBoardRenderer{

	private PacMan pacman;
	private Maze map;
	private Collection<Sprite> movingSprites = new LinkedList<Sprite>();
	private Map<Tile, Sprite> staticSprites = new HashMap<Tile, Sprite>();

	private Direction awaitingDirection;
	private int score;
	private Text scoreText;
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void drawMaze(Maze map) 
	{		
		this.map = map;
		Tile[][] tiles = map.getTiles();	
		
		for (int i = 0; i < tiles.length; ++i)
		{
			for (int k = 0; k < tiles[0].length; ++k)
			{
				if (tiles[i][k].getType() == TileType.WALL)
				{
					Rectangle wall = new Rectangle(tiles[i][k].getX() * TILE_SIZE, tiles[i][k].getY() * TILE_SIZE, TILE_SIZE, TILE_SIZE);
	        		wall.setFill(Color.BLUE);
	        		this.getChildren().add(wall);
				} else {
					Sprite sprite = new Sprite(tiles[i][k].getCollectable(), 1);
					staticSprites.put(tiles[i][k], sprite);
				}
			}
		}

        scoreText = new Text(GAME_WIDTH /2 - 50 , GAME_HEIGHT /2, "Score: 0");
        scoreText.setFont(new Font(20));
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
		if (tile.hasCollectable()) {
			updateScore(tile.consumeCollectable());	
			Sprite spriteToRemove = staticSprites.get(tile);
			staticSprites.remove(tile);			
			this.getChildren().remove(spriteToRemove);		
		}
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		// TODO: adopt behavior to current state of state machine 
		
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
	
	private void animate()
	{
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
			// TODO: tunnel
		}
	}
	
	private void updateScore(int value) {
		score += value;
		scoreText.setText("Score: " + score);
	}
}
