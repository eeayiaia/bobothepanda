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

	private Animation enemyAnimation;
	
	public FixedEnemyView() throws SlickException{
		final Image [] enemyImages = {new Image("")};
		
		enemyAnimation = new Animation(enemyImages, 125); 
	}
	
	public void drawEnemyAnimation(float x, float y){
		enemyAnimation.draw(x, y);
	}
	
	public enum DrawObject{
		ENEMY_ANIMATION
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case ENEMY_ANIMATION:
				drawEnemyAnimation(x, y);
				break;
			}
		}
	}
}
