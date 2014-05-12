package model;

/**
 * @author Oscar Muhr
 */

import java.awt.Rectangle;
//import java.util.List;

public class Collision {
//	private final List <IMapObject> objectList;
//	private IMapObject currentObject;
//	
//	public Collision(List <IMapObject> objectList) {
//		this.objectList = objectList;
//	}
	
	public static boolean collision(Rectangle collider, Rectangle collidedWith){
		return collider.intersects(collidedWith);
	}
	
//	public Rectangle collidedWith(Rectangle characterHitbox){
//		for(final IMapObject o: objectList){
//			//Checks if bobo has collided with another object
//			if(o.getHitbox().intersects(characterHitbox)){
//				currentObject = o;
//				return o.getHitbox();
//			}
//		}
//		return null;
//	}
//	
//	public IMapObject getObjectType() {
//		if(currentObject != null) {
//			return currentObject;
//		}
//		return null;
//	}
//	
//	public Position getObjectPosition() {
//		if(currentObject != null){
//			return currentObject.getPosition();
//		}
//		return null;
//	}
}
