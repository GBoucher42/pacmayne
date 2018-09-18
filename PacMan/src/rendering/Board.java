package rendering;

import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.TILE_SIZE;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import entities.Animatable;
import entities.CollisionType;
import entities.Direction;
import entities.EntityManager;
import entities.GameEntity;
import entities.IGameEntity;
import entities.Maze;
import entities.PacMan;
import entities.Tile;
import entities.TileType;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Board extends Pane implements IBoardRenderer{

	private PacMan pacman;
	private Maze map;
	private Collection<Sprite> movingSprites = new LinkedList<Sprite>();
	private Collection<Sprite> staticSprites = new LinkedList<Sprite>();
	
	private Text scoreText;
	int score;
	
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
					staticSprites.add(sprite);					
				}
			}
		}

        scoreText = new Text(GAME_WIDTH /2 - 50 , GAME_HEIGHT /2, "Score: 0");
        scoreText.setFont(new Font(20));
        this.getChildren().addAll(staticSprites);
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
		for (Sprite sprite : staticSprites)
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
		Tile spriteTile = map.getTile(pacman.getCurrentY(), pacman.getCurrentX());
		if (spriteTile.hasCollectable()) {
			
			updateScore(spriteTile.getCollectable().getScoreValue());	
			spriteTile.consumeCollectable();
		}
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		// TODO: adopt behavior to current state of state machine 
		
		switch(keyCode) {
			case UP:
				pacman.setDirection(Direction.UP);
				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
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
		CollisionType type = map.validateMove(pacman);
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
