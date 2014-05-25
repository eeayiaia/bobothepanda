package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * 
 * @author Victor Larsson
 *
 */
//This class has many methods because of visitor pattern
@SuppressWarnings("PMD.TooManyMethods")
public class Projectile extends AbstractMovingObject{
	
	private final PropertyChangeSupport pcs;
	private float velocity = -0.35f;
	private Position startPos;
	
	public Projectile(Position position, Size size) {
		super(position, size);
		startPos = new Position(position);
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * Updates the position of the projectile.
	 * @param delta The time between each update.
	 */
	public void update(int delta){
		setNewX(delta, velocity);
		if(0 >= getPosition().getX()){
			reset();
		}	
	}
	
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
	
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
	
	/**
	 * Removes the projectile
	 */
	public void reset(){
		getPosition().setX(startPos.getX());
		//pcs.firePropertyChange("REMOVE_PROJECTILE", null, this);
	}
	
	public void render(){
		pcs.firePropertyChange("PROJECTILE", null, getPosition());
	}
	
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}
	
	public void visit(Character character){
		reset();
	}
	
	public void visit(Terrain terrain){
		reset();
	}
	
	public void visit(FixedEnemy fixedEnemy){
		reset();
	}
	
	public void visit(Door d) {
		reset();
	}
	
	public void visit(ShootingEnemy s) {}
	public void visit(Key k) {}
	public void visit(Projectile p) {}
	public void visit(MovingEnemy m) {}
}
