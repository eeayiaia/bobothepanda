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
	
	public void setNewX(int delta, float velocity){
		final float nextPositionX = getPosition().getX() - velocity * delta;
		setX(nextPositionX);
	}
}
