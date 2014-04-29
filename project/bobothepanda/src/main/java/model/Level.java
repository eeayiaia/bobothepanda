package model;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Level{
	
	private Character playerCharacter;
	private ArrayList <IMapObject> objectList;
	private IMapObject door;
	private boolean keyPickedUp;
	private PropertyChangeSupport pcs;
	
	
	public Level(ArrayList<IMapObject> objectList, Character playerCharacter){
		this.playerCharacter = playerCharacter;
		this.objectList = objectList;
		for(IMapObject o: objectList){
			if(o.getObjectType().equals(ObjectType.DOOR)){
				door = o;
			}
		}
	}
	public Rectangle collidedWith(Rectangle characterHitbox){
		for(IMapObject o: objectList){
			//Checks if bobo has collided with another object
			if(o.getHitbox().intersects(playerCharacter.getHitbox())){
				return o.getHitbox();
			}
		}
		return null;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener){
		pcs.removePropertyChangeListener(listener);
	}
	
	public void loadNext(Level level){
	}
	
}
