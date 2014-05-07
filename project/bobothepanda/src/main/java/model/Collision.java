package model;

import java.awt.Rectangle;
import java.util.List;

public class Collision {
	private final List <AbstractMapObject> objectList;
	private AbstractMapObject currentObject;
	
	public Collision(List <AbstractMapObject> objectList) {
		this.objectList = objectList;
	}
	
	public Rectangle collidedWith(Rectangle characterHitbox){
		for(final AbstractMapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(characterHitbox)){
				currentObject = o;
				return currentObject.getHitbox();
			}
		}
		return null;
	}
	
	public AbstractMapObject getObjectType() {
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
