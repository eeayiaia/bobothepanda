package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import model.menu.MenuState;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuView implements PropertyChangeListener{
	private final Image startButtonImg;
	private final Image quitButtonImg;
	private final Image hoverStartButton;
	private final Image hoverQuitButton;
	private final Image startButtonPressed;
	private final Image quitButtonPressed;
	private final Animation characterAnimation;
	private final static String SPRITE_RIGHT_IMAGE = "/data/Bobo-01.png";
	private float startX;
	private float startY;
	private float quitY;
	private float quitX;
	private float characterX;
	private float characterY;

	public MenuView() throws SlickException{
		final Image [] boboRightAnimation = {new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboRightLeg-01.png"),
                new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboLeftLeg-01.png")};
		startButtonImg = new Image("data/img/startButton.png");
		quitButtonImg = new Image("data/img/quitButton.png");
		hoverStartButton = new Image("data/img/hoverStartButton.png");
		hoverQuitButton = new Image("data/img/hoverQuitButton.png");
		startButtonPressed = new Image("data/img/pressedStartButton.png");
		quitButtonPressed = new Image("data/img/pressedQuitButton.png");
		characterAnimation = new Animation(boboRightAnimation, 125);
		
	}

	public Image getStartButton() {
		return startButtonImg;
	}

	public Image getQuitButton() {
		return quitButtonImg;
	}

	public Animation getCharacterAnimation() {
		return characterAnimation;
	}
	public void drawCharacterAnimation(float x, float y){
		characterAnimation.draw(x,y);
		//TODO at position?
	}
	public void drawDefault(){
		characterAnimation.draw(characterX, characterY);
		startButtonImg.draw(startX, startY);
		quitButtonImg.draw(quitX, quitY);
	}
	public void drawHoverQuit(){
		characterAnimation.draw(characterX, characterY);
		startButtonImg.draw(startX, startY);
		hoverQuitButton.draw(quitX,quitY);
	}
	public void drawHoverStart(){
		characterAnimation.draw(characterX, characterY);
		hoverStartButton.draw(startX, startY);
		quitButtonImg.draw(quitX,quitY);
	}
	public void drawPressedStart(){
		characterAnimation.draw(characterX, characterY);
		quitButtonImg.draw(quitX,quitY);
		startButtonPressed.draw(startX, startY);

	}
	public void drawPressedQuit(){
		characterAnimation.draw(characterX, characterY);
		startButtonImg.draw(startX, startY);
		quitButtonPressed.draw(quitX, quitY);
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		String source = evt.getPropertyName();
		
		switch(MenuState.valueOf(source)){
		case START_UP:
			final Position cursorPos = (Position) evt.getOldValue();
			final float x = cursorPos.getX();
			final float y = cursorPos.getY();
			if("Start".equals(evt.getNewValue())){
				startX = x;
				startY = y;
			} else if ("Quit".equals(evt.getNewValue())){
				quitX = x;
				quitY = y;
			} else {
				characterX = x;
				characterY = y;
			}
			drawDefault();
			break;
		case CURSOR_ON_QUIT:
			drawHoverQuit();
			break;
		case CURSOR_ON_START:
			drawHoverStart();
			break;
		case START_BUTTON_PRESSED:
			drawPressedStart();
			break;
		case QUIT_BUTTON_PRESSED:
			drawPressedQuit();
			break;
		case QUIT_BUTTON_RELEASED:
			drawPressedQuit();
			break;
		case START_BUTTON_RELEASED:
			drawPressedStart();
			break;
		case UPDATE:
			drawDefault();
			break;
		default:
			break;
			
		}
		
	}

}
