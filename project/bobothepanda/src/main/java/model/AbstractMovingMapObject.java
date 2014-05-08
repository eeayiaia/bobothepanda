package model;

public abstract class AbstractMovingMapObject extends AbstractMapObject {
	
	public AbstractMovingMapObject(Position position, Size size) {
		super(position, size);
	}
	
	abstract public void update(int delta);
	
}
