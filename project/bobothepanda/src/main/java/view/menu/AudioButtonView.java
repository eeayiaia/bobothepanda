package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.menu.MenuState;

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
		final String source = evt.getPropertyName();
		final Position pos = (Position) evt.getOldValue();
		final float x = pos.getX();
		final float y = pos.getY();
		switch(MenuState.valueOf(source)){
		case AUDIO_OFF_DEFAULT: case AUDIO_ON_RELEASED:
			audioOffDefault.draw(x, y);
			break;
		case AUDIO_OFF_HOVER:
			audioOffHover.draw(x, y);
			break;
		case AUDIO_OFF_PRESSED:
			audioOffPressed.draw(x, y);
			break;
		case AUDIO_ON_HOVER:
			audioOnHover.draw(x, y);
			break;
		case AUDIO_ON_PRESSED: case AUDIO_OFF_RELEASED:
			audioOnPressed.draw(x, y);
			break;
		case AUIDO_ON_DEFAULT:
			audioOnDefault.draw(x, y);
			break;
		default:
			break;
		
		}
		
	}

}
