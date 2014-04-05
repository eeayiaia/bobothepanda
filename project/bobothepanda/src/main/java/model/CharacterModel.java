package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import position.Position;

public class CharacterModel {
	
	private double velocity;
	private Position position;
	private boolean isDead;
	private PropertyChangeSupport pcs;
	
	public CharacterModel(Position position){
		this.position = position;
		isDead = false;
		pcs = new PropertyChangeSupport(this);
	}
	
	public enum CharacterState{
		MOVING_RIGHT, MOVING_LEFT, JUMP
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
	
	public void moveLeft(int delta){
		position.setX(position.getX() - 0.3f*delta);
		pcs.firePropertyChange(CharacterState.MOVING_LEFT.toString(), null, position);
	}
	public void moveRight(int delta){
		position.setX(position.getX() + 0.3f*delta);
		pcs.firePropertyChange(CharacterState.MOVING_RIGHT.toString(), null, position);
	}
	
	public Position getPosition(){
		return position;
	}
}
