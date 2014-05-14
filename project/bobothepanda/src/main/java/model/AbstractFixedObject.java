package model;

/**
 * @author Oscar Muhr
 */

public abstract class AbstractFixedObject extends AbstractMapObject implements IVisitable {
	
	public AbstractFixedObject(Position position, Size size) {
		super(position, size);
	}
	
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}

}
