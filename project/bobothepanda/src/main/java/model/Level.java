package model;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Level{
	
	private Character playerCharacter;
	private ArrayList <IMapObject> objectList;
	private PropertyChangeSupport pcs;
	
	
	public Level(ArrayList<IMapObject> objectList, Character playerCharacter){
		this.playerCharacter = playerCharacter;
		this.objectList = objectList;
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
}
