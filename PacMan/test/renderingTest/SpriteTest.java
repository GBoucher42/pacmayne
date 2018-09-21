package renderingTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.Animatable;
import entities.Direction;
import entities.PacMan;
import rendering.Sprite;

@TestInstance(Lifecycle.PER_CLASS)
public class SpriteTest {
	private Sprite sprite;
	private PacMan pacman;
	int initX = 20;
	int initY = 20;
	private Animatable animatable;
	

	
	@BeforeAll
	public void setup() {
		animatable = new Animatable("pacman-d1");
		pacman = new PacMan(initX, initY, animatable);
		sprite = new Sprite(pacman, 1);
	}
	
	
	@Test
	public void updateSpritePositionCorrectly() {
		int newX = 25;
		int newY = 29;
		assertEquals(initX, sprite.getLayoutX());
		assertEquals(initY, sprite.getLayoutY());
		/*pacman.setCurrentX(newX);
		pacman.setCurrentY(newY);
		assertNotEquals(initX, pacman.getCurrentX());
		assertNotEquals(initY, pacman.getCurrentY());
		sprite.updatePosition();
		assertEquals(newX, sprite.getLayoutX());
		assertEquals(newY, sprite.getLayoutY());*/
	}
	
	/*@Test
	public void resetSpritePositionCorrectly() {
		double newX = 25;
		double newY = 29;
		sprite.setLayoutX(newX);
		sprite.setLayoutY(newY);
		sprite.resetPosition();
		assertEquals(sprite.getLayoutX(), initX);
		assertEquals(sprite.getLayoutY(), initY);
	}
	
	@Test
	public void isShowingCorrectly() {
		sprite.setVisible(false);
		assertEquals(sprite.isVisible(), false);
		sprite.show();
		assertEquals(sprite.isVisible(), true);
	}
	
	@Test
	public void isHidingCorrectly() {
		sprite.setVisible(true);
		assertEquals(sprite.isVisible(), true);
		sprite.hide();
		assertEquals(sprite.isVisible(), false);
	}*/
	
	@AfterEach
	void resetParameter() {
		pacman.setCurrentX(initX);
		pacman.setCurrentY(initY);
	}
}
