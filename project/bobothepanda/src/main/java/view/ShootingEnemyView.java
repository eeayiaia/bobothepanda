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

public class ShootingEnemyView implements PropertyChangeListener{

	private Image enemyLeft;
	private Image enemyRight;
	
	public ShootingEnemyView() throws SlickException{
	
		//TODO use Animation
		enemyRight = new Image("/data/img/Cannon-02.png");
		enemyLeft = new Image("/data/img/Cannon-02.png").getFlippedCopy(true, false);
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
		Object tmp = evt.getNewValue();
		if(Position.class == tmp.getClass()){
			final Position pos = (Position)tmp;
			
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
