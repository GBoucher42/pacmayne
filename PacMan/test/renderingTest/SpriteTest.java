package renderingTest;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.GameEntity;
import rendering.Sprite;

@TestInstance(Lifecycle.PER_CLASS)
public class SpriteTest {
	private Sprite sprite;
	private GameEntity entity;

	
	@BeforeAll
	public void setup() {
		sprite = new Sprite(entity, 1);
	}
	
	@Test
	public void Test() {
		
	}
	
	@AfterAll
	public void AfterAllTests() {
		
	}
}
