package model;

public class Door extends AbstractMapObject {

	public Door(Position position, Size size) {
		super(position, size);
	}
	
	@Override
	public void doCollision() {
		// TODO Auto-generated method stub

	}
	AbstractMapObject.doCollision(this);
	public void doCollision(Character character){
		character.setNewX(5);
	}

}
