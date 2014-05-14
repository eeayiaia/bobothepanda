package model;

@SuppressWarnings("PMD")
public class Key extends AbstractFixedObject {

	public Key(Position position, Size size) {
		super(position, size);
	}

	public void doCollision(Character character) {
		boolean testingMethod = true;
		character.setKeyPickedUp(testingMethod);
		
	}
}
