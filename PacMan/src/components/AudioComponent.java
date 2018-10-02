package components;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineEvent.Type;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import threads.MessageEnum;

public class AudioComponent implements IComponent{
	
	private final Map<MessageEnum, AudioInputStream> soundMap;
	private Clip clip;
	private boolean playing = false;
	private LineListener listener;
	
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
}
