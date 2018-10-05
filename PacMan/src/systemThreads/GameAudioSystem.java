package systemThreads;

import java.io.File;
import java.util.List;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineEvent.Type;

import components.AudioComponent;
import entities.Entity;
import entities.EntityManager;

public class GameAudioSystem extends SystemBase implements Runnable{
	
	private volatile boolean isRunning = true;
	private Clip clip;
	private boolean playingBackground = false;
	private boolean updatingMusic = false;
	

	private LineListener listener;

	public GameAudioSystem(EntityManager entityManager) {
		super(entityManager);
	}

	@Override
	public void update() {
		
		List<Entity> entities = entityManager.getAllEntitiesPosessingComponentOfClass(AudioComponent.class.getName());
		for(Entity entity: entities) {
			AudioComponent audio = (AudioComponent) entityManager.getComponentOfClass(AudioComponent.class.getName(), entity);
			MessageEnum message = MessageQueue.consumeEntityMessages(entity, AudioComponent.class.getName());
			if (message != null) {
				audio.play();
				if(updatingMusic != true) {
					updatingMusic = true;
				}
			}
			
		}
		
	}
	
	private void playBackgroundMusic()
	{
		try {
	        AudioInputStream audioInputStream; 
	        
	        audioInputStream = AudioSystem.getAudioInputStream(new File("ressource/audio/background-music.wav"));
	        clip = AudioSystem.getClip();
	        clip.open(audioInputStream);
	        clip.loop(Clip.LOOP_CONTINUOUSLY);
	        playingBackground = true;
	    } catch(Exception error) {           
	        System.out.println("Error with playing sound." + error);
	    }
	}
	
	private void stopBackgroundMusic() {
		clip.stop();
	}
	
	public void stopThread() {
		isRunning = false;	
		stopBackgroundMusic();
	}

	@Override
	public void run() {
		System.out.println("Start audio Thread");
		playBackgroundMusic();
		while(isRunning) {
			update();
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		updatingMusic = false;
		System.out.println("Stop audio Thread!");			
	}
	
	public boolean isPlayingBackground() {
		return playingBackground;
	}
	
	public boolean isUpdatingMusic() {
		return updatingMusic;
	}
}
