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

	private final Animation enemyLeftAnimation;
	private final Animation enemyRightAnimation;
	
	public MovingEnemyView() throws SlickException{
		final Image [] leftAnimation = {new Image("data/img/saw1.png"),
										new Image("data/img/saw2.png"),
										new Image("data/img/saw3.png"),
										new Image("data/img/saw4.png")};
		final Image [] rightAnimation = leftAnimation;
		
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
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")//It is not incorrect to set x,y to new values everytime
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			final float x = pos.getX();
			final float y = pos.getY();
			final DrawObject drawObj = DrawObject.valueOf(evt.getPropertyName());
			
			if(drawObj == DrawObject.ENEMY_MOVING_LEFT){
				drawEnemyAnimationLeft(x, y);
				
			}else if(drawObj == DrawObject.ENEMY_MOVING_RIGHT){
				drawEnemyAnimationRight(x, y);
				
			}
		}
	}
}
