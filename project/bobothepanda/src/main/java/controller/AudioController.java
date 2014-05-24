package controller;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.openal.SoundStore;

import utilities.AudioHandler;

public class AudioController implements PropertyChangeListener{
	private AudioHandler audio;
	
	
	public AudioController(){
		audio = new AudioHandler();
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		SoundStore.get().poll(0);
		String propertyName = evt.getPropertyName();
		if("jump".equals(propertyName)){
			audio.playJumpSound();
		}
		if("die".equals(propertyName)){
			audio.playDeathSound();
		}
		if("door".equals(propertyName)){
			audio.playDoorSound();
		}
		if("key".equals(propertyName)){
			audio.playKeySound();
		}
		SoundStore.get().poll(0);
	}

}
