package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import utilities.Position;

public class KeyModel {
	
	private Position position;
	private boolean isPickedUp;
	private PropertyChangeSupport pcs;
	
	public KeyModel(Position position){
		this.position = position;
		isPickedUp = false;
		pcs = new PropertyChangeSupport(this);
	}
	public enum KeyState{
		PICKED_UP;
	}
	public void addListener(PropertyChangeListener listener){
		pcs.addPropertyChangeListener(listener);
	}
	public Position getPosition(){
		return position;
	}
	public void pickUp(){
		isPickedUp = true;
		pcs.firePropertyChange(KeyState.PICKED_UP.toString(), true, false);
	}
	public boolean isPickedUp(){
		return isPickedUp;
	}
	
}
