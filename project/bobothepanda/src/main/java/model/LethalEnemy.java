package model;

public class LethalEnemy extends AbstractMapObject {
	
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
