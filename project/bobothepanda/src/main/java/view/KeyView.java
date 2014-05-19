package view;

/**
 * 
 * @author Victor Larsson
 *
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class KeyView implements PropertyChangeListener{

	private Animation keyAnimation;
	
	public KeyView() throws SlickException{
		final Image[] keyImages = {new Image("/data/img/keyDown-01.png"),
									new Image("/data/img/keyMid-01.png"),
									new Image("/data/img/keyUp-01.png")};
		
		keyAnimation = new Animation(keyImages, 150);
	}
	
	public void drawKeyAnimation(float x, float y){
		keyAnimation.draw(x, y);
	}
	
	public enum DrawObject{
		KEY_ANIMATION
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case KEY_ANIMATION:
				drawKeyAnimation(x, y);
				break;
			}
		}	
	}
}
