package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuView implements PropertyChangeListener{
	private final Image startButton;
	private final Image quitButton;
	private final Animation characterAnimation;
	private final static String SPRITE_RIGHT_IMAGE = "/data/Bobo-01.png";
	private final float characterX;
	private final float characterY;

	public MenuView() throws SlickException{
		final Image [] boboRightAnimation = {new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboRightLeg-01.png"),
                new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboLeftLeg-01.png")};
		startButton = new Image("data/img/startButton.png");
		quitButton = new Image("data/img/quitButton.png");
		characterAnimation = new Animation(boboRightAnimation, 125);
	}

	public Image getStartButton() {
		return startButton;
	}

	public Image getQuitButton() {
		return quitButton;
	}

	public Animation getCharacterAnimation() {
		return characterAnimation;
	}
	public void drawCharacterAnimation(float x, float y){
		characterAnimation.draw(x,y);
		//TODO at position?
	}
	
	public enum MouseEvents{
		QUIT_BUTTON_CLICKED,
		START_BUTTON_CLICKED,
		CURSOR_ON_QUIT,
		CURSOR_ON_START,
		UPDATE
	}
	public void propertyChange(PropertyChangeEvent evt) {
		String source = evt.getPropertyName();
		
	}

}
