package model;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends AbstractMapObject{

	private final static float VELOCITY = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
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

	public void doCollision(IMapObject mapObject) {
		// TODO Auto-generated method stub
		
	}
}
