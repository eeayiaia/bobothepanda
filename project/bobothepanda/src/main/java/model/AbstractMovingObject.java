package model;

public class AbstractMovingObject extends AbstractMapObject implements IVisitor, IVisitable{

	public AbstractMovingObject(Position position, Size size) {
		super(position, size);
	}

	public void visit(IVisitable i) {
		// TODO Auto-generated method stub
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	/**
	 * Sets a new x-position for the object.
	 * @param delta The time between each update.
	 * @param velocity The current velocity of the object.
	 */
	public void setNewX(int delta, float velocity){
		final float nextPositionX = getPosition().getX() - velocity * delta;
		setX(nextPositionX);
	}
}
