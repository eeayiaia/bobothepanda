package utilities;

import java.io.IOException;
import java.util.List;

import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.AudioLoader;
import org.newdawn.slick.util.ResourceLoader;

public class AudioHandler {
	
	private final static String AUDIO_LOCATION = "/data/audio/";
	private Audio jump;
	private Audio death;
	private Audio door;
	private Audio key;
	private Audio background;
//	private List <Audio> soundEffects;
	
	public AudioHandler(){
		loadAudio();
	}

	private void loadAudio() {
		try {
			jump = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "jump.wav"));
			death = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "death.wav"));
			door = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "door.wav"));
			key = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "key.wav"));
			background = AudioLoader.getAudio("WAV", ResourceLoader.getResourceAsStream(AUDIO_LOCATION + "background.wav"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		//due to error with the key soundfile, the death sound is played
		key.playAsSoundEffect(1.0f, 1.0f, false);
	}
	
	
	
	
}
