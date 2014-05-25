package model;

/**
 * @author Victor Larsson
 */

import model.Projectile;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class ShootingEnemy extends AbstractFixedObject {

	private Position projectilePos;
	private final Size projectileSize = new Size(4,4);
	private PropertyChangeSupport pcs;
	//private long lastTimedFired;
	private boolean fired;
	private List<Projectile>projectiles;
	private int count;
	
	public ShootingEnemy(Position position, Size size) {
		super(position, size);
		projectilePos = new Position(getPosition().getX(), getPosition().getY());
		//lastTimedFired = System.currentTimeMillis();
		projectiles = new ArrayList<Projectile>();
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
			projectiles.add(new Projectile(projectilePos, projectileSize));
			pcs.firePropertyChange("ADD_PROJECTILE", null, projectiles.get(count));
			//lastTimedFired = System.currentTimeMillis();
			fired = true;
			count++;
		}
		
		/*
		if(lastTimedFired + 1000 <= System.currentTimeMillis()){
			projectiles.add(new Projectile(centerPos, projectileSize));
			pcs.firePropertyChange("ADD_PROJECTILE", null, projectiles.get(count));
			//lastTimedFired = System.currentTimeMillis();
			count++;
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
