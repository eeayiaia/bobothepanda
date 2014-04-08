package model;

/*
 * @author Oscar Muhr
 */

import java.awt.Rectangle;

import utilities.ObjectType;
import utilities.Position;
import utilities.Size;

public class MapObject implements IMapObject {
	
	private Position position;
	private Size size;
	private ObjectType type;
	private Rectangle hitbox;
	
	public MapObject(Position position, Size size, ObjectType type) {
		this.position = position;
		this.size = size;
		this.type = type;
		hitbox = new Rectangle((int)Math.round(position.getX()), (int)Math.round(position.getY()), 
								(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
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
	
	public Rectangle getHitbox() {
		return hitbox;
	}
	public void setObjectType(ObjectType type){
		this.type = type;
	}
}
