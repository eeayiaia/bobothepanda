package model;

public abstract class AbstractMovingObject extends AbstractMapObject implements IVisitor{

	public AbstractMovingObject(Position position, Size size) {
		super(position, size);
	}

	/**
	 * Sets a new x-position for the object.
	 * @param delta The time between each update.
	 * @param velocity The current velocity of the object.
	 */
	public void setNewX(int delta, float velocity){
		final float nextPositionX = getPosition().getX() + velocity * delta;
		setX(nextPositionX);
	}
}
