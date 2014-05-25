package model;

/**
 * @author Victor Larsson
 */

import model.Projectile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class ShootingEnemy extends AbstractFixedObject {

	private Position centerPos;
	private final Size projectileSize = new Size(4,4);
	private PropertyChangeSupport pcs;
	private long lastTimedFired;
	private boolean fired;
	
	public ShootingEnemy(Position position, Size size) {
		super(position, size);
		centerPos = new Position((float)getHitbox().getCenterX(),(float)getHitbox().getCenterY());
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
		
		if(!fired){
			System.out.println("shooting");
			System.out.println(centerPos.getX());
			Projectile projectile = new Projectile(centerPos, projectileSize);
			pcs.firePropertyChange("ADD_PROJECTILE", null, projectile);
			lastTimedFired = System.currentTimeMillis();
			fired = true;
		}
		
		/*
		if(lastTimedFired + 1000 <= System.currentTimeMillis()){
			System.out.println("shooting");
			System.out.println(centerPos.getX());
			Projectile projectile = new Projectile(centerPos, projectileSize);
			pcs.firePropertyChange("ADD_PROJECTILE", null, projectile);
			lastTimedFired = System.currentTimeMillis();
		}
		*/
	}
	
	public void update(int delta){
		fireProjectile();
	}
	
	public void render(){
		pcs.firePropertyChange("ENEMY_RIGHT", null, this.getPosition());
	}
}
