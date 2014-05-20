package model;

import java.beans.PropertyChangeSupport;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends AbstractMovingObject{
	
	PropertyChangeSupport pcs;
	private float velocity = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
		pcs = new PropertyChangeSupport(this);
	}

	/**
	 * Updates the position of the projectile.
	 * @param delta The time between each update.
	 */
	public void update(int delta){
		//checks for legal position
		try {
			setNewX(delta, velocity);
		} catch (IllegalArgumentException e) {
			remove();
			velocity *= -1;
			setNewX(delta, velocity);	
		}
	}
	
	/**
	 * Removes the projectile
	 */
	public void remove(){
		pcs.firePropertyChange("PROJECTILE_COLLISION", null, this);//TODO Render itself
	}
	
	public void render(){
		pcs.firePropertyChange("PROJECTILE", null, this.getPosition());
	}
	
	public void accept(IVisitor visitor){
		System.out.println("hit in Projectile");
		visitor.visit(this);
	}
	
	public void visit(Character character){
		remove();
	}
	
	public void visit(Terrain terrain){
		remove();
	}
	
	public void visit(FixedEnemy fixedEnemy){
		remove();
	}
	
	public void visit(Door d) {
		remove();
	}
	
	public void visit(ShootingEnemy s) {}
	public void visit(Key k) {}
	public void visit(Projectile p) {}
	public void visit(MovingEnemy m) {}
}
