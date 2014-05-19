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

public class MovingEnemyView implements PropertyChangeListener{

	private Animation enemyLeftAnimation;
	private Animation enemyRightAnimation;
	
	public MovingEnemyView() throws SlickException{
		final Image [] leftAnimation = {new Image("")};
		final Image [] rightAnimation = {new Image("")};
		
		enemyLeftAnimation = new Animation(leftAnimation, 125);
		enemyRightAnimation = new Animation(rightAnimation, 125);
	}
	
	public void drawEnemyAnimationLeft(float x, float y){
		enemyLeftAnimation.draw(x, y);
	}
	
	public void drawEnemyAnimationRight(float x, float y){
		enemyRightAnimation.draw(x, y);
	}
	
	public enum DrawObject{
		ENEMY_MOVING_LEFT,
		ENEMY_MOVING_RIGHT
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case ENEMY_MOVING_LEFT:
				drawEnemyAnimationLeft(x, y);
				break;
				
			case ENEMY_MOVING_RIGHT:
				drawEnemyAnimationRight(x, y);
				break;
			}
		}
	}

}
