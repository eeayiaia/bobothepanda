package model;

/*
 * @author Oscar Muhr
 */

import utilities.ObjectType;
import utilities.Position;
import utilities.Size;

public class MapObject implements IMapObject {
	
	private Position position;
	private Size size;
	private ObjectType type;
	
	public MapObject(Position position, Size size, ObjectType type) {
		this.position = position;
		this.size = size;
		this.type = type;
	}

	public Position getPosition() {
		return position;
	}

	public Size getSize() {
		return size;
	}

	public ObjectType getObjectType() {
		return type;
	}
}
