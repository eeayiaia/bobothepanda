package model;

/**
 * @author Victor Larsson
 */

import model.Projectile;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

public class ShootingEnemy extends AbstractFixedObject implements PropertyChangeListener {

	private final Position centerPos = new Position((float)getHitbox().getCenterX(),(float)getHitbox().getCenterY());
	private final Size projectileSize = new Size(4,4);
	private PropertyChangeSupport pcs;
	private long lastTimedFired;
	private List<Projectile> projectiles;
	
	public ShootingEnemy(Position position, Size size) {
		super(position, size);
		lastTimedFired = System.currentTimeMillis();
		pcs = new PropertyChangeSupport(this);
	}
	
	/**
	 * Adds a PropertyChangeListener
	 * @param listener The listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
	
	/**
	 * Removes a PropertyChangeListener
	 * @param listener The listener
	 */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Fires a projectile at one second intervals.
	 */
	public void fireProjectile(){
		if(lastTimedFired + 1000 >= System.currentTimeMillis()){
			Projectile projectile = new Projectile(centerPos, projectileSize);
			pcs.firePropertyChange("projectile", null, projectile);
			projectiles.add(projectile);
			lastTimedFired = System.currentTimeMillis();
		}
	}
	
	public void update(int delta){
		for(Projectile p: projectiles){
			p.update(delta);
		}
	}
	
	public void render(){
		for(Projectile p: projectiles){
			p.render();
		}
		
	}
	
	public void propertyChange(PropertyChangeEvent evt){
		if("PROJECTILE_COLLISION".equals(evt.getPropertyName())){
			Projectile projectile =(Projectile) evt.getNewValue();
			for(Projectile p: projectiles){
				if(p.equals(projectile)){
					projectiles.remove(p);
				}
			}
		}
	}
	
}
