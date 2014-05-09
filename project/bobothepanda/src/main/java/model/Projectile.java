package model;

/**
 * 
 * @author Victor Larsson
 *
 */

public class Projectile extends AbstractMapObject{

	private final float VELOCITY = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
	}
	
	@Override
	public void doCollision() {
		// TODO Auto-generated method stub

	}

	/**
	 * Updates the position of the projectile.
	 * @param delta
	 */
	public void update(int delta){
		
		setX(VELOCITY * delta);
		
	}

	public void doCollision(Character character) {
		// TODO Auto-generated method stub
		
	}
}
