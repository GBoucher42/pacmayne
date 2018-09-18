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
	private Collection<Sprite> animatedSprites = new LinkedList<Sprite>();
	
	private Text scoreText;
	private Map<Integer, Shape> gums = new HashMap<>();
	private Map<Integer, Shape> pacGums = new HashMap<>();
	int score;
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void drawMaze(Maze map) 
	{		
		this.map = map;
		int j = 0;
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
					// TODO: these classes should contain their image that we simply display
					 if (tiles[i][k].isTileSuperGum()) {
			        		Circle gum = new Circle(tiles[i][k].getX() * TILE_SIZE + TILE_SIZE / 2, tiles[i][k].getY() * TILE_SIZE + TILE_SIZE / 2, TILE_SIZE / 2);
			        		gum.setFill(Color.WHITE);
			        		this.getChildren().add(gum);
			        		pacGums.put(j, gum);
					 } else if (tiles[i][k].isTileGum()){
			        		Circle gum = new Circle(tiles[i][k].getX() * TILE_SIZE + TILE_SIZE / 2, tiles[i][k].getY() * TILE_SIZE + TILE_SIZE / 2, TILE_SIZE / 4);
			        		gum.setFill(Color.WHITE);
			        		this.getChildren().add(gum);
			        		gums.put(j, gum);
					 }
				}
				++j;
			}
		}

        scoreText = new Text(GAME_WIDTH /2 - 50 , GAME_HEIGHT /2, "Score: 0");
        scoreText.setFont(new Font(20));
        this.getChildren().add(scoreText);
	}
	
	public void spawnAnimatables(EntityManager entityManager)
	{
		for (int i = 0; i < entityManager.count(); ++i)
		{		
			IGameEntity entity = entityManager.getEntity(i);
			if (entity.getAnimatable() != null)
			{
				animatedSprites.add(new Sprite(entity, i));
				
				if (pacman == null && entity.getClass() == PacMan.class)
				{
					pacman = (PacMan) entity;
					eatGum(pacman.getTileIndex()-1);
				}
			}				
		}
		
		this.getChildren().addAll(animatedSprites);
	}
	
	public void spawnStaticEntities(EntityManager entityManager)
	{
		// TODO:
	}
	
	public void refreshView()
	{
		animate();
		for (Sprite sprite : animatedSprites)
		{
			sprite.updatePosition();
			detectGums(sprite.getEntity().getTileIndex() - 1);
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
	
	private void detectGums(int index) {
		if(gums.containsKey(index)) {
			eatGum(index);
		} else if(pacGums.containsKey(index)) {
			eatPacGum(index);
		}
	}
	
	private void eatGum(int index) {
		this.getChildren().remove(gums.get(index));
		gums.remove(index);
		score+=10;
		updateScore();
	}
	
	private void eatPacGum(int index) {
		this.getChildren().remove(pacGums.get(index));
		pacGums.remove(index);
		score+=50;
		updateScore();
	}
	
	private void updateScore() {
		scoreText.setText("Score: " + score);
	}
}
