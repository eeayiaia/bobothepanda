package model;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Collision {
	ArrayList<IMapObject> objectList;
	IMapObject currentObject;
	
	public Collision(ArrayList<IMapObject> objectList) {
		this.objectList = objectList;
	}
	
	public Rectangle collidedWith(Rectangle characterHitbox){
		for(IMapObject o: objectList){
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
		} else {
			return null;
		}
	}
	
	public Position getObjectPosition() {
		if(currentObject != null){
			return currentObject.getPosition();
		}else{
			return null;
		}
		
	}
}
