package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

public class Door extends Terrain {

	public Door(Position position, Size size) {
		super(position, size);
	}
	
	/**
	 * If the character has the key it lets the next level be loaded
	 * otherwise it just blocks the character
	 */
	@Override
	public void doCollision(Character character){
		if(character.getKeyPickedUp()) {
			character.levelComplete();
		} else {
			super.doCollision(character);
		}
	}
}
