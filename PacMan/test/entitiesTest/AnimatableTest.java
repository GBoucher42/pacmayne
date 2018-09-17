package entitiesTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import entities.Animatable;
import entities.Direction;
import entities.PacMan;

@TestInstance(Lifecycle.PER_CLASS)
class AnimatableTest {
	private Animatable animatable;
	double initX = 20;
	double initY = 20;
	double initSpeed = 20;
	Direction initDirection = Direction.DOWN;
	
	
	@BeforeAll
	void setup() {
		animatable = new PacMan(initX, initY, initSpeed, initDirection);
		
	}
	
	@Test 
	void willNotMoveIfImmobile() {
		animatable.setIsMoving(false);
		animatable.moveOneFrameBySpeed();
		assertEquals(initX, animatable.getCurrentX());
		assertEquals(initY, animatable.getCurrentY());
	}
	
	@Test 
	void MoveDownIfIsMoving() {
		animatable.setIsMoving(true);
		animatable.moveOneFrameBySpeed();
		assertEquals(initX, animatable.getCurrentX());
		assertTrue(initY < animatable.getCurrentY());
	}
	
	@Test 
	void MoveUpIfIsMoving() {
		animatable.setIsMoving(true);
		animatable.setDirection(Direction.UP);
		animatable.moveOneFrameBySpeed();
		assertEquals(initX, animatable.getCurrentX());
		assertTrue(initY > animatable.getCurrentY());
	}
	
	@Test 
	void MoveRigthIfIsMoving() {
		animatable.setIsMoving(true);
		animatable.setDirection(Direction.RIGHT);
		animatable.moveOneFrameBySpeed();
		assertTrue(initX < animatable.getCurrentX());
		assertEquals(initY, animatable.getCurrentY());
	}
	
	@Test 
	void MoveLeftIfIsMoving() {
		animatable.setIsMoving(true);
		animatable.setDirection(Direction.LEFT);
		animatable.moveOneFrameBySpeed();
		assertTrue(initX > animatable.getCurrentX());
		assertEquals(initY, animatable.getCurrentY());
	}
	
	@AfterEach
	void resetParameter() {
		animatable.setCurrentX(initX);
		animatable.setCurrentY(initY);
		animatable.setSpeed(initSpeed);
		animatable.setDirection(initDirection);
	}
	
	
}
