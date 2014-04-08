package model;

import utilities.Position;
import utilities.Size;

public interface IMapObject {
	
	public enum ObjectType {
		TERRAIN, LETHAL, KEY;
	}
	
	public Position getPosition();
	
	public Size getSize();
	
	public ObjectType getObjectType();
}
