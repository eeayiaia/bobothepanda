package view;

/**
 * 
 * @author Victor Larsson
 *
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class FixedEnemyView implements PropertyChangeListener{

	private Image enemyLeft;
	private Image enemyRight;
	
	public FixedEnemyView() throws SlickException{
		//enemyLeft = new Image("PATH");
		//enemyRight = new Image("PATH");
	}
	
	public void drawEnemyLeft(float x, float y){
		enemyLeft.draw(x, y);
	}
	
	public void drawEnemyRight(float x, float y){
		enemyRight.draw(x, y);
	}
	
	public enum DrawObject{
		ENEMY_LEFT,
		ENEMY_RIGHT
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case ENEMY_LEFT:
				drawEnemyLeft(x, y);
				break;
				
			case ENEMY_RIGHT:
				drawEnemyRight(x, y);
				break;
			}
		}
		
	}

}
