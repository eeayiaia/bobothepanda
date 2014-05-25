package model;

import java.awt.Rectangle;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

import model.Projectile;
import model.Position;

@SuppressWarnings("PMD")
public class Level implements PropertyChangeListener{
	
	private final PropertyChangeSupport pcs;
	private Projectile projectile;
	private List <AbstractMapObject> abstractMapObjects;
	
	
	public Level(List <AbstractMapObject> abstractMapObjects, Character player){
		this.abstractMapObjects = abstractMapObjects;
		abstractMapObjects.add(player);
		projectile = new Projectile(new Position(392.0f, 470.0f), new Size(4,4));
		this.pcs = new PropertyChangeSupport(this);
		
	}

	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	public void propertyChange(PropertyChangeEvent evt) {
		if("loadLevel".equals(evt.getPropertyName())){
			pcs.firePropertyChange("loadLevel", null, null);
		}else if("reloadLevel".equals(evt.getPropertyName())){
			pcs.firePropertyChange("reloadLevel", null, null);
		}
	}
	
	
	public void update(){
		checkCollisions();		
	}
	
	
	/**
	 * Loops through all objects on the map and checks if any collisions has occured
	 * then takes action accordingly.
	 */
	public void checkCollisions() {
		
		for(AbstractMapObject o: abstractMapObjects){
			if(o instanceof IVisitor){
				
				for(AbstractMapObject i: abstractMapObjects)
					if(collision(o.getHitbox(), i.getHitbox())){
						IVisitor visitor = (IVisitor) o;
						i.accept(visitor);
					}
			}
			
		}

	}
	
	public boolean collision(Rectangle collider, Rectangle collidedWith){
		//test for null
		if(collider == null || collidedWith == null){
			return false;
		}
		if(collider.equals(collidedWith)){
			return false;
		}
		return collider.intersects(collidedWith);
	}
	
	
}
