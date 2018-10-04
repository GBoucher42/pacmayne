package componentsTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static java.time.Duration.ofMillis;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import entities.EntityManager;
import factories.EntityFactory;
import systemThreads.GameAudioSystem;

@TestInstance(Lifecycle.PER_CLASS)
class GameAudioSystemTest {

	private GameAudioSystem gameAudioSystem;
	private EntityManager entityManager;
	private Thread tGameAudioSystem;


	@BeforeAll
	void setUpBeforeClass() throws InterruptedException {
		entityManager = new EntityManager();
		gameAudioSystem = new GameAudioSystem(entityManager);
		tGameAudioSystem = new Thread(gameAudioSystem);
		tGameAudioSystem.start();
		tGameAudioSystem.join(100);
		assertTrue("thread dead", tGameAudioSystem.isAlive());
	}

	@AfterAll
	void tearDownAfterClass() throws Exception {
		boolean expectedStop = true;
		boolean actualStop = tGameAudioSystem.isAlive();
		if(actualStop) {
			gameAudioSystem.stopThread();
			try {
				tGameAudioSystem.join(1500);
				actualStop = !tGameAudioSystem.isAlive(); //check if thread is dead
			} catch (Exception e) {
				e.printStackTrace();
			}
			assertEquals("thread not stopped" , expectedStop, actualStop);
		}
	}

	@Test
	void playBackgroundMusicTest() {
		assertTimeout(ofMillis(2000), () -> {
			boolean actualPlaying = gameAudioSystem.isPlayingBackground();
			assertTrue("audio is not playing in background", actualPlaying);
						
			Thread.sleep(500);
		});
	}
	
	@Test
	void updateMusic() {
		assertTimeout(ofMillis(2000), () -> {
			boolean actualUpdateMusic = gameAudioSystem.isUpdatingMusic();
			assertTrue("audio is not playing in background", actualUpdateMusic);
			gameAudioSystem.stopThread();
			try {
				tGameAudioSystem.join(3000);
				assertFalse("Thread stopped", tGameAudioSystem.isAlive());
				actualUpdateMusic = gameAudioSystem.isUpdatingMusic();
				assertFalse("audio is playing in background", actualUpdateMusic);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Thread.sleep(500);
		});
	}

}
