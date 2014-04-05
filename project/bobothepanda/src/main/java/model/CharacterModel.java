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
		pcs.firePropertyChange(CharacterState.RENDER.toString(), null, position);
	}
	
	public enum CharacterState{
		MOVING_RIGHT, MOVING_LEFT, JUMP, RENDER
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
	
	
	
	private float gravity = 2;
	private float groundLevel_Y_Value = 416;
	
	
	/**
	 * 
	 * @return
	 */
	public void jump(int delta){
		if(position.getY() == groundLevel_Y_Value){
			position.setY(position.getY() - 0.3f*delta);
			
		}
	}
	
	public Position getPosition(){
		return position;
	}
}
