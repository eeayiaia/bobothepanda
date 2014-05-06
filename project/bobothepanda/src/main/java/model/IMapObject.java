package model;

/*
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

public interface IMapObject {
	
	Position getPosition();
	
	Size getSize();
	
	ObjectType getObjectType();
	
	Rectangle getHitbox();
	
	void setObjectType(ObjectType type);
}
