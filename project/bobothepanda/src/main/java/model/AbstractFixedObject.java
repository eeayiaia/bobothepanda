package model;

/**
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

public abstract class AbstractFixedObject implements IVisitable {
	
	private final Position position;
	private final Size size;
	
	public AbstractFixedObject(Position position, Size size) {
		this.position = position;
		this.size = size;
	}

	public Position getPosition() {
		return position;
	}

	public Size getSize() {
		return size;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
	}
	
	public void setX(float xValue) {
		position.setX(xValue);
	}
	
	public void setY(float yValue) {
		position.setY(yValue);
	}
	
	public void setPosition(Position position) {
		setX(position.getX());
		setY(position.getY());
	}
}
