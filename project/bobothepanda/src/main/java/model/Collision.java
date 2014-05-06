package model;

import java.awt.Rectangle;
import java.util.List;

public class Collision {
	private final List <IMapObject> objectList;
	private IMapObject currentObject;
	
	public Collision(List <IMapObject> objectList) {
		this.objectList = objectList;
	}
	
	public Rectangle collidedWith(Rectangle characterHitbox){
		for(final IMapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(characterHitbox)){
				currentObject = o;
				return currentObject.getHitbox();
			}
		}
		return null;
	}
	
	public ObjectType getObjectType() {
		if(currentObject != null) {
			return currentObject.getObjectType();
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
