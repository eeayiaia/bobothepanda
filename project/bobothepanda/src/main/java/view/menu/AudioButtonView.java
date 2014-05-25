package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class AudioButtonView implements PropertyChangeListener{
	private final static String LOCATION = "data/img/Buttons/";
	private final Image audioOnDefault;
	private final Image audioOnHover;
	private final Image audioOnPressed;
	private final Image audioOffDefault;
	private final Image audioOffHover;
	private final Image audioOffPressed;
	
	public AudioButtonView() throws SlickException {
		audioOnDefault = new Image(LOCATION + "audioOn.png");
		audioOnHover = new Image(LOCATION + "hoverAudioOn.png");
		audioOnPressed = new Image(LOCATION + "pressedAudioOn.png");
		audioOffDefault = new Image(LOCATION + "audioOff.png");
		audioOffHover = new Image(LOCATION + "hoverAudioOff.png");
		audioOffPressed = new Image(LOCATION + "pressedAudioOff.png");
		
	}

	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		
	}

}
