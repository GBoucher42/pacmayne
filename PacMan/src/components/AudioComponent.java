package components;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;

import systemThreads.MessageEnum;

import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioComponent implements IComponent{
	
	private final Map<MessageEnum, AudioInputStream> soundMap;
	private Clip clip;
	private boolean playing = false;
	private LineListener listener;
	private FloatControl gainControl;
	private double volume = 0.5;
	private boolean isMuted = false;
	
	public AudioComponent(Map<MessageEnum, String> soundMap) {
		this.soundMap = new HashMap<MessageEnum, AudioInputStream>();
		
		try {
			AudioInputStream inputStream;			
			
			for (Map.Entry<MessageEnum, String> entry : soundMap.entrySet()) {
				inputStream = AudioSystem.getAudioInputStream(new File(entry.getValue()));
				this.soundMap.put(entry.getKey(), inputStream);
			}	
		
			clip = AudioSystem.getClip();
			clip.open(this.soundMap.get(MessageEnum.EATEN));
			gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

			listener = new LineListener() {
				
				@Override
				public void update(LineEvent event) {
					if (event.getType() != Type.STOP) {						
						return;
					} else if (event.getType() == Type.STOP) {
						clip.setFramePosition(0);
						playing = false;
					}
				}
			};	
			clip.addLineListener(listener);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if(!playing) {
			clip.start();
		}
	}
	
	public void increaseVolume() {
		if(volume < 1.0) {
			volume += 0.1;
			roundVolume();
			setVolume(volume);
		}
	}
	
	public void decreaseVolume() {
		if(volume > 0.0) {
			volume -= 0.1;
		    roundVolume();
		    setVolume(volume);
		}
	}
	
	public void mute() {
		isMuted = !isMuted;
		if(isMuted) {
			setVolume(0.0);
		} else {
			setVolume(volume);
		}
	}
	
	private void roundVolume() { //without rounding, you may end up with very small divergence that make a big difference in DB calculation
		int scale = (int) Math.pow(10, 2);
	    volume = (double) Math.round(volume * scale) / scale;
	}
	
	public void setVolume(double volume) {
		float dB = (float) (Math.log(volume) / Math.log(10.0) * 20.0);
		gainControl.setValue(dB);
	}
}
