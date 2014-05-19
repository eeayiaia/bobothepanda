package view;

/**
 * @author Victor Larsson
 */

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import model.Position;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class ProjectileView implements PropertyChangeListener{

	private Image projectileSprite;
	
	public ProjectileView() throws SlickException{
		projectileSprite = new Image("/data/img/projectiles/testProjectile-01.png"); 
	}
	
	public void drawProjectile(float x, float y){
		projectileSprite.draw(x,y);
	}
	
	public enum DrawObject{
		PROJECTILE
	}
	
	public void propertyChange(PropertyChangeEvent evt) {
		final Position pos = (Position)evt.getNewValue();
		
		if(pos != null){
			final float x = pos.getX();
			final float y = pos.getY();
			
			switch(DrawObject.valueOf(evt.getPropertyName())){
			case PROJECTILE:
				drawProjectile(x, y);
				break;
			}
		}
	}
}
