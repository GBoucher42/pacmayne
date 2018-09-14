package rendering;

import static configs.GameConfig.GAME_TILE_WIDTH_COUNT;
import static configs.GameConfig.GAME_TOTAL_TILE_COUNT;
import static configs.GameConfig.GAME_TILE_HEIGHT_COUNT;
import static configs.GameConfig.GAME_HEIGHT;
import static configs.GameConfig.GAME_WIDTH;
import static configs.GameConfig.TILE_SIZE;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import entities.Animatable;
import entities.Direction;
import entities.EntityManager;
import entities.GameEntity;
import entities.GameMap;
import entities.PacMan;
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
	private GameMap map = new GameMap();
	private Collection<Sprite> animatedSprites = new LinkedList<Sprite>();
	
	private Text scoreText;
	private Map<Integer, Shape> gums = new HashMap<>();
	private Map<Integer, Shape> pacGums = new HashMap<>();
	int score;
	
	public Board()
	{
		this.setStyle("-fx-background-color: black;");
	}
	
	public void drawMaze() 
	{		
		// TODO: import maze image and slap it on the board instead of drawing shitty rectangles
		int i = 0;
		for (int y = 0; y < GAME_TILE_HEIGHT_COUNT; y++) {
            for (int x = 0; x < GAME_TILE_WIDTH_COUNT; x++) {
            	if (map.tileGrid[i] == 0) {            		
            		Rectangle wall = new Rectangle(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
            		wall.setFill(Color.BLUE);
            		this.getChildren().add(wall);
            	} else if (map.tileGrid[i] == 2) {
            		Circle gum = new Circle(x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE+ TILE_SIZE/2, TILE_SIZE/2);
            		gum.setFill(Color.WHITE);
            		this.getChildren().add(gum);
            		pacGums.put(i, gum);
            	} else {
            		Circle gum = new Circle(x * TILE_SIZE + TILE_SIZE/2, y * TILE_SIZE+ TILE_SIZE/2, TILE_SIZE/4);
            		gum.setFill(Color.WHITE);
            		this.getChildren().add(gum);
            		gums.put(i, gum);
            	}
               
                i++;
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
			GameEntity entity = entityManager.getEntity(i);
			if (entity instanceof Animatable)
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
		
	}
	
	// To be called by the game thread update() method
	public void refreshView()
	{
		for (Sprite sprite : animatedSprites)
		{
			sprite.updatePosition();
		}
		
		// TODO: force a refresh of the scene when the game thread updates
	}
	
	public void onKeyPressed(KeyCode keyCode) {
		int candidateTileIndex = 0;
		pacman.setIsMoving(true);
		// TODO: adopt behavior to current state of state machine 
		
		switch(keyCode) {
			case UP:
				pacman.setDirection(Direction.UP);
				candidateTileIndex = pacman.getTileIndex() - GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
					detectGums(candidateTileIndex);
				}

				break;
			case DOWN:
				pacman.setDirection(Direction.DOWN);
				candidateTileIndex = pacman.getTileIndex() + GAME_TILE_WIDTH_COUNT - 1;
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
					detectGums(candidateTileIndex);
				}
				
				break;
			case LEFT:
				pacman.setDirection(Direction.LEFT);
				candidateTileIndex = pacman.getTileIndex() - 2;
				if (candidateTileIndex > 0 && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
					detectGums(candidateTileIndex);
				}
				
				break;
			case RIGHT:
				pacman.setDirection(Direction.RIGHT);
				candidateTileIndex = pacman.getTileIndex();
				if (candidateTileIndex < GAME_TOTAL_TILE_COUNT && map.tileGrid[candidateTileIndex] != 0){
					pacman.moveOneFrameBySpeed();
					detectGums(candidateTileIndex);
				}
				
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
		
		refreshView();
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
