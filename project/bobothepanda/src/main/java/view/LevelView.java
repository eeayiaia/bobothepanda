package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import org.newdawn.slick.*;
import org.newdawn.slick.*;

import model.Position;

@SuppressWarnings("PMD")
public class LevelView implements PropertyChangeListener{

	
	private Image projectileSprite;
	
	public LevelView() throws SlickException{
		projectileSprite = new Image("/data/img/projectiles/testProjectile-01.png");
	}
	
	public void drawProjectile(float x, float y){
		projectileSprite.draw(x,y);
	}
		
	public void propertyChange(PropertyChangeEvent evt) {

		if("drawProjectile".equals(evt.getPropertyName())){
			Position pos = (Position)evt.getNewValue();
			drawProjectile(pos.getX(), pos.getY());
		}
	}
}
