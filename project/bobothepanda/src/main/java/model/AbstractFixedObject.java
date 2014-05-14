package model;

/**
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

public abstract class AbstractFixedObject extends AbstractMapObject implements IVisitable {
	
	private final Position position;
	private final Size size;
	
	public AbstractFixedObject(Position position, Size size) {
		super(position, size);
		this.position = position;
		this.size = size;
	}
	
	public void accept(IVisitor visitor){
		visitor.visit(this);
	}

}
