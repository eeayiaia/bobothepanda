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

	private final Image enemyLeft;
	private final Image enemyRight;
	
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
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")//It is not incorrect to set x,y to new values everytime
	public void propertyChange(PropertyChangeEvent evt) {
		final Object obj = evt.getNewValue();
		if(Position.class == obj.getClass()){
			final Position pos = (Position)obj;
			
			final float x = pos.getX();
			final float y = pos.getY();
			final DrawObject drawObj = DrawObject.valueOf(evt.getPropertyName());
			
			if(drawObj == DrawObject.ENEMY_LEFT){
				drawEnemyLeft(x, y);
			}else if(drawObj == DrawObject.ENEMY_RIGHT){
				drawEnemyRight(x, y);
			}
			
		}
	}
}
