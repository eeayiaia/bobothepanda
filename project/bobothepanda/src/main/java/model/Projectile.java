package model;

/**
 * 
 * @author Victor Larsson
 *
 */
@edu.umd.cs.findbugs.annotations.SuppressFBWarnings()
public class Projectile extends MovingLethalEnemy{

	private final static float VELOCITY = 0.25f;
	
	public Projectile(Position position, Size size) {
		super(position, size);
	}

	/**
	 * Updates the position of the projectile.
	 * @param delta
	 */
	public void update(int delta){
		
		//setX(VELOCITY * delta);
		
		setNewX(delta);
		
	}

	public void setNewX(int delta) {
		
		final float nextPositionX = getPosition().getX() - VELOCITY * delta;
		
		//Position nextPosition = new Position(nextPositionX, getPosition().getY());
		
		setX(nextPositionX);
	}

	public void doCollision(Character character) {
		character.die();
	}

	public void doCollision(IMapObject mapObject) {
		// TODO Auto-generated method stub
		
	}
}
