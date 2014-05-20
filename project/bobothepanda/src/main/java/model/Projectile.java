package model;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends AbstractMovingObject{

	private float velocity = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
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
		//TODO Render itself
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
