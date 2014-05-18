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
		if("jump".equals(evt.getPropertyName())){
			audio.playJumpSound();
		}
		if("die".equals(evt.getPropertyName())){
			audio.playDeathSound();
		}
		if("door".equals(evt.getPropertyName())){
			audio.playDoorSound();
		}
		if("key".equals(evt.getPropertyName())){
			audio.playKeySound();
		}
	}

}
