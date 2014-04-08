package model;

/*
 * @author Oscar Muhr
 */

import utilities.ObjectType;
import utilities.Position;
import utilities.Size;

public interface IMapObject {
	
	public Position getPosition();
	
	public Size getSize();
	
	public ObjectType getObjectType();
}
