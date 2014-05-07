package model;

import java.awt.Rectangle;
import java.util.List;

public class Collision {
	private final List <MapObject> objectList;
	private MapObject currentObject;
	
	public Collision(List <MapObject> objectList) {
		this.objectList = objectList;
	}
	
	public Rectangle collidedWith(Rectangle characterHitbox){
		for(final MapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(characterHitbox)){
				currentObject = o;
				return currentObject.getHitbox();
			}
		}
		return null;
	}
	
	public MapObject getObjectType() {
		if(currentObject != null) {
			return currentObject;
		}
		return null;
	}
	
	public Position getObjectPosition() {
		if(currentObject != null){
			return currentObject.getPosition();
		}
		return null;
	}
}
