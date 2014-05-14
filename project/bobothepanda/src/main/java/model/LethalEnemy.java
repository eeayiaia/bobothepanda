package model;

public class LethalEnemy extends AbstractFixedObject {
	
	public LethalEnemy(Position position, Size size) {
		super(position, size);
	}
	/**
	 * Kill the character on collision
	 */
	public void doCollision(Character character) {
		character.die();
	}
}
