package view.menu;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class MenuView implements PropertyChangeListener{
	private final Animation characterAnimation;
	private final static String SPRITE_RIGHT_IMAGE = "/data/Bobo-01.png";
	private final StartButtonView start;
	private final QuitButtonView quit;
	private final float x;
	private final float y;
	

	public MenuView(float x, float y) throws SlickException{
		final Image [] boboRightAnimation = {new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboRightLeg-01.png"),
                new Image(SPRITE_RIGHT_IMAGE), new Image("/data/BoboLeftLeg-01.png")};
		characterAnimation = new Animation(boboRightAnimation, 125);
		start = new StartButtonView();
		quit = new QuitButtonView();
		this.x = x;
		this.y = y;
		
	}

	public StartButtonView getStartView(){
		return start;
	}
	public QuitButtonView getQuitView(){
		return quit;
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		characterAnimation.draw(x, y);
	}

}
