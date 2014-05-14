package model;

/**
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

public abstract class AbstractFixedObject implements IMapObject {
	
	private final Position position;
	private final Size size;
	private final Rectangle hitbox;
//	private final double TOP_LEFT_ANGLE;
	
	public AbstractFixedObject(Position position, Size size) {
		this.position = position;
		this.size = size;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
								(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
//		TOP_LEFT_ANGLE = calculateTopLeftToMidAngle(position, size);
//		System.out.println(TOP_LEFT_ANGLE);
	}

	public Position getPosition() {
		return position;
	}

	public Size getSize() {
		return size;
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
	
//	private double calculateTopLeftToMidAngle(Position position, Size size) {
//		Position centerPosition = new Position(position.getX() + size.getWidth()/2, position.getY() + size.getHeight()/2);
//		return Math.atan((position.getY() - centerPosition.getY())/(position.getX() - centerPosition.getX())) + Math.PI;
//	}
}
