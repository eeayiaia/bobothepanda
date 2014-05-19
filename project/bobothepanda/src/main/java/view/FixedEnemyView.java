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

public class FixedEnemyView implements PropertyChangeListener{

	private Animation sawAnimation;
	
	public FixedEnemyView() throws SlickException{
		final Image [] sawImages = {new Image("/data/saw1.png"), 
									new Image("/data/saw2.png"), 
									new Image("/data/saw3.png"), 
									new Image("/data/saw4.png")};
		
		sawAnimation = new Animation(sawImages, 125); 
	}
	
	public void drawSawAnimation(float x, float y){
		sawAnimation.draw(x, y);
	}
	
	public enum DrawObject{
		SAW_ANIMATION
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case SAW_ANIMATION:
				drawSawAnimation(x, y);
				break;
			}
		}
	}
}
