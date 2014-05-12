package model;

import java.awt.Rectangle;

public interface IMapObject {

	Position getPosition();

	Size getSize();
	
	Rectangle getHitbox();
	
	void setX(float xValue);
	
	void setY(float yValue);
	
	void setPosition(Position position);
	
	void doCollision(Character character);
}
