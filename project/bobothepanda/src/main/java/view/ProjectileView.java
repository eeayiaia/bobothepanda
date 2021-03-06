package view;

/**
 * @author Victor Larsson
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import view.ShootingEnemyView.DrawObject;

public class ProjectileView implements PropertyChangeListener{

	private final Image projectileSprite;
	
	public ProjectileView() throws SlickException{
		projectileSprite = new Image("/data/img/projectiles/testProjectile-01.png"); 
	}
	
	public void drawProjectile(float x, float y){
		projectileSprite.draw(x,y);
	}
	
	public enum DrawObject{
		PROJECTILE
	}
	
	@SuppressWarnings("PMD.DataflowAnomalyAnalysis")//It is not incorrect to set x,y to new values everytime
	public void propertyChange(PropertyChangeEvent evt) {
		final Object obj = evt.getNewValue();
		if(Position.class == obj.getClass()){
			final Position pos = (Position)obj;
			
			final float x = pos.getX();
			final float y = pos.getY();
			final DrawObject drawObj = DrawObject.valueOf(evt.getPropertyName());	
			
			if(drawObj == DrawObject.PROJECTILE){
				drawProjectile(x, y);
			}
		}
	}
}
