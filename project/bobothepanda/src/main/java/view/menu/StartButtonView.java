package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.menu.MenuState;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class StartButtonView implements PropertyChangeListener{
	
	private final Image defaultImage;
	private final Image hoverImage;
	private final Image pressedImage;
	private final static String LOCATION = "data/img/Buttons/";
	
	public StartButtonView() throws SlickException {
		defaultImage = new Image(LOCATION + "startbutton.png");
		hoverImage = new Image(LOCATION + "hoverStartButton.png");
		pressedImage = new Image(LOCATION + "pressedStartButton.png");
	}
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")//It is not incorrect to set x,y to new values everytime
	public void propertyChange(PropertyChangeEvent evt) {
		final String source = evt.getPropertyName();
		final Position buttPos = (Position)evt.getOldValue();
		final float x = buttPos.getX();
		final float y = buttPos.getY();
		switch(MenuState.valueOf(source)){
		case CURSOR_ON_START:
			hoverImage.draw(x, y);
			break;
		case START_DEFAULT:
			defaultImage.draw(x, y);
			break;
		case START_BUTTON_PRESSED:
			pressedImage.draw(x, y);
			break;
		default:
			break;
		
		}
	}

}
