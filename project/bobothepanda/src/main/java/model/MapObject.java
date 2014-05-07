package model;

/**
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

public abstract class MapObject {
	
	private final Position position;
	private final Size size;
	private final Rectangle hitbox;
	
	public MapObject(Position position, Size size) {
		this.position = position;
		this.size = size;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
								(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));

	}

	public Position getPosition() {
		return new Position(position);
	}

	public Size getSize() {
		return new Size(size);
	}
	
	public Rectangle getHitbox() {
		return new Rectangle(hitbox);
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
