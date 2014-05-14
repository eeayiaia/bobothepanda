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
	 * @param delta The time between each update.
	 */
	public void update(int delta){
		//checks for legal position
		try {
			setNewX(delta, velocity);
		} catch (IllegalArgumentException e) {
			remove();
			//changes direction
			velocity *= -1;
			setNewX(delta, velocity);	
		}
	}
	
	/**
	 * Removes the projectile
	 */
	public void remove(){
		//TODO Remove self
	}
	
	@Override
	public void visit(Character character){
		remove();
	}
	
	@Override
	public void visit(Terrain terrain){
		remove();
	}
	
	@Override
	public void visit(FixedEnemy fixedEnemy){
		remove();
	}
}
