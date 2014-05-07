package model;

import java.awt.Rectangle;

public interface IMapObject {
	public void doCollision();

	public Position getPosition();

	public Size getSize();
	
	public Rectangle getHitbox();
	
	public void setX(float xValue);
	
	public void setY(float yValue);
	
	public void setPosition(Position position);
}
