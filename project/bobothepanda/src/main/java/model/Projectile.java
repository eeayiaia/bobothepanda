package model;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends MovingEnemy{

	private static float VELOCITY = 0.25f;
	
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
			setNewX(delta);
		} catch (IllegalArgumentException e) {
			// TODO: remove itself
			//changes direction
			VELOCITY *= -1;
			setNewX(delta);	
		}
	}
	
	public void setNewX(int delta) throws IllegalArgumentException{
		final float nextPositionX = getPosition().getX() - VELOCITY * delta;
		//Position nextPosition = new Position(nextPositionX, getPosition().getY());
		setX(nextPositionX);
	}
}
