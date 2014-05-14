package model;

public class FixedEnemy extends AbstractFixedObject {
	
	public FixedEnemy(Position position, Size size) {
		super(position, size);
	}
	/**
	 * Kill the character on collision
	 */
	public void doCollision(Character character) {
		character.die();
	}
	public void accept(IVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
