package model;

import java.awt.Rectangle;

public abstract class AbstractMapObject implements IVisitable{

	private final Position position;
	private final Size size;
	
	public AbstractMapObject(Position position, Size size) {
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
		if(position == null) {
			throw new NullPointerException(this.getClass().toString());
		}else {
			setX(position.getX());
			setY(position.getY());
		}
	}
	
	
	
}
