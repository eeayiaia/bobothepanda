package model;

/**
 * @author Victor Larsson
 */

import model.Projectile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShootingEnemy extends AbstractFixedObject {

	private final Position centerPos = new Position((float)getHitbox().getCenterX(),(float)getHitbox().getCenterY());
	private final Size projectileSize = new Size(4,4);
	private PropertyChangeSupport pcs;
	private long lastTimedFired;
	
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
			lastTimedFired = System.currentTimeMillis();
		}
	}
}
