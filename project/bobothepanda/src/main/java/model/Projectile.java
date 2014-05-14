package model;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends MovingEnemy{

	private float velocity = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
	}

	/**
	 * Updates the position of the projectile.
	 * @param delta
	 */
	public void update(int delta){
		//checks for legal position
		try {
			setNewX(delta, velocity);
		} catch (IllegalArgumentException e) {
			// TODO: remove itself
			//changes direction
			velocity *= -1;
			setNewX(delta, velocity);	
		}
	}
	
	public void visit(Character character){
		//TODO Remove self
	}
}
