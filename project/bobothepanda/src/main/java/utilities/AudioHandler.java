package utilities;

import java.io.IOException;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.Log;
import org.newdawn.slick.util.ResourceLoader;

//background can't be resolved if not as a field variable
@SuppressWarnings("PMD.SingularField")
public class AudioHandler {
	
	private final static String AUDIO_LOCATION = "/data/audio/";
	private final static String FILE_TYPE = "WAV";
	private Audio jump;
	private Audio death;
	private Audio door;
	private Audio key;
	private Audio background;
	
	public AudioHandler(){
		loadAudio();
	}

	private void loadAudio() {
		try {
			jump = AudioLoader.getAudio(FILE_TYPE, ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "jump.wav"));
			death = AudioLoader.getAudio(FILE_TYPE, ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "death.wav"));
			door = AudioLoader.getAudio(FILE_TYPE, ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "door.wav"));
			key = AudioLoader.getAudio(FILE_TYPE, ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "key.wav"));
			background = AudioLoader.getAudio(FILE_TYPE, ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "background.wav"));
			
		} catch (IOException e) {
			Log.error(e);
		}
		background.playAsMusic(1.0f, 1.0f, true);
		
	}

	public void playJumpSound(){
		jump.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	public void playDeathSound(){
		death.playAsSoundEffect(1.0f, 1.0f, false);
	}

	public void playDoorSound() {
		
		door.playAsSoundEffect(1.0f, 1.0f, false);
		
	}
	
	public void playKeySound() {
		key.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	
	
	
}
