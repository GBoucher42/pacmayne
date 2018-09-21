package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import entities.Animatable;
import entities.Direction;
import entities.PacMan;

@TestInstance(Lifecycle.PER_CLASS)
class AnimatableTest {
	private Animatable animatable;
	private String defaultProp;
	private Direction direction;
	
	
	@BeforeAll
	void setup() {
		animatable = new Animatable("pacman-u1");
		 
	}
	
	@Test 
	void addAnimationTest() {
		assertTrue(animatable.hasAnimation() == false);
		List<String> images = new ArrayList<>();
		images.add("pacman-u2");
		animatable.addAnimation(Direction.UP, images);
		assertTrue(animatable.hasAnimation());
	}
	
	@Test
	void voirLaProchaineImage() {
		List<String> images = new ArrayList<>();
		images.add("pacman-u1");
		images.add("pacman-u2");
		animatable.addAnimation(Direction.UP, images);
		assertEquals(animatable.getNextImage(), "pacman-u1");
		assertEquals(animatable.getNextImage(), "pacman-u2");
		assertEquals(animatable.getNextImage(), "pacman-u1"); //vérifier qu'il revient bien à la première image
	}
	
	@Test
	void imageSelonDirection() {
		List<String> images = new ArrayList<>();
		images.add("pacman-u1");
		animatable.addAnimation(Direction.UP, images);
		images.add("pacman-d1");
		animatable.addAnimation(Direction.DOWN, images);
		animatable.setCurrentAnimation(Direction.UP);
		assertEquals(animatable.getCurrentAnimation(), images.indexOf("pacman-u1"));
	}
	
	@AfterEach
	void resetParameter() {
		animatable = new Animatable("pacman-u1");
	}
	
	
}
