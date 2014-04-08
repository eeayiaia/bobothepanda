package model;

/*
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

import utilities.ObjectType;
import utilities.Position;
import utilities.Size;

public interface IMapObject {
	
	public Position getPosition();
	
	public Size getSize();
	
	public ObjectType getObjectType();
	
	public Rectangle getHitbox();
	
	public void setObjectType(ObjectType type);
}
