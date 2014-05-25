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

import view.ShootingEnemyView.DrawObject;

public class KeyView implements PropertyChangeListener{

	private final Animation keyAnimation;
	private boolean keyPickedUp;
	
	public KeyView() throws SlickException{
		final Image[] keyImages = {new Image("/data/img/keyDown-01.png"),
									new Image("/data/img/keyMid-01.png"),
									new Image("/data/img/keyUp-01.png")};
		
		keyAnimation = new Animation(keyImages, 150);
	}
	
	public void drawKeyAnimation(float x, float y){
		if(!keyPickedUp){
			keyAnimation.draw(x, y);
		}
		
	}
	
	public enum DrawObject{
		KEY_ANIMATION, KEY_PICKED_UP
	}
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")//It is not incorrect to set x,y to new values everytime
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			final float x = pos.getX();
			final float y = pos.getY();
			final DrawObject drawObj = DrawObject.valueOf(evt.getPropertyName());
			
			if(drawObj == DrawObject.KEY_ANIMATION){
				drawKeyAnimation(x, y);
				
			}else if(drawObj == DrawObject.KEY_PICKED_UP){
				keyPickedUp = true;
			}
		}	
	}
}
