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

public class ShootingEnemyView implements PropertyChangeListener{

	private Image EnemyLeft;
	private Image EnemyRight;
	
	public ShootingEnemyView() throws SlickException{
	
		//TODO use Animation
		EnemyRight = new Image("/data/Bobo-01.png");
		EnemyLeft = new Image("/data/Bobo-01.png").getFlippedCopy(true, false);
	}
	
	public void drawEnemyLeft(float x, float y){
		EnemyLeft.draw(x, y);
	}
	
	public void drawEnemyRight(float x, float y){
		EnemyRight.draw(x, y);
	}

	public enum DrawObject{
		ENEMY_LEFT,
		ENEMY_RIGHT
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		
		if(Position.class == evt.getNewValue().getClass()){
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
}
