package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import position.Position;

public class CharacterModel {
	
	private double velocity;
	private Position position;
	private boolean isDead;
	private PropertyChangeSupport pcs;
	
	/**
	 * Sets the starting position and assigns PropertyChangeSupport to this class
	 * @param position Starting position
	 */
	public CharacterModel(Position position){
		this.position = position;
		isDead = false;
		pcs = new PropertyChangeSupport(this);
		pcs.firePropertyChange(CharacterState.RENDER.toString(), null, position);
	}
	
	/**
	 * The current state of the Character
	 * MOVING_RIGHT, MOVING_LEFT, JUMP, RENDER
	 */
	public enum CharacterState{
		MOVING_RIGHT, MOVING_LEFT, JUMP, RENDER
	}
	
	/**
	 * Adds a PropertyChangeListener
	 * @param listener The listener
	 */
	public void addPropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.addPropertyChangeListener(listener);
    }
	
	/**
	 * Removes a PropertyChangeListener
	 * @param listener The listener
	 */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        this.pcs.removePropertyChangeListener(listener);
    }
	
    /**
     * Moves the character to the right.
     * 
     * TODO Implement acceleration 
     * 
     * @param delta
     */
	public void moveLeft(int delta){
		position.setX(position.getX() - 0.3f*delta);
		pcs.firePropertyChange(CharacterState.MOVING_LEFT.toString(), null, position);
	}
	/**
	 * Moves the character to the right.
	 * 
	 * TODO Implement acceleration 
	 * 
	 * @param delta
	 */
	public void moveRight(int delta){
		position.setX(position.getX() + 0.3f*delta);
		pcs.firePropertyChange(CharacterState.MOVING_RIGHT.toString(), null, position);
	}
	
	
	
	private float gravity = 2;
	private float velocity_Y = 0;
	private float maxDownwardsVelocityY = 20;
	private float accelaration_Y;
	private float groundLevel_Y_Value = 416; //value at level 1 atm. 
	
	
	/**
	 * Changes the velocity in the y direction of the character. 
	 * Goes upwards, and then down, until the ground is reached
	 * Character needs to be on the ground to jump.
	 */
	public void jump(int delta){
		//needs to be on the ground to jump
		if(onGround()){
			velocity_Y = -20;
			while(!onGround()){
				if(velocity_Y < maxDownwardsVelocityY){
					velocity_Y += gravity;
				}else{
					velocity_Y = maxDownwardsVelocityY;
				}
				position.setY(position.getY() + velocity_Y * delta);
				//send property change
			}
			//when the ground is reached
			velocity_Y = 0;
			position.setY(position.getY() + velocity_Y * delta);	
			//send property change
		}
		
	}
	/**
	 * Checks if the character is on the ground
	 * @return true if on ground level.
	 */
	public boolean onGround(){
		return (position.getY()== groundLevel_Y_Value) ? true : false; 
	}
	
	/**
	 * The current position of the character
	 * @return The current position of the character as a Position object
	 */
	public Position getPosition(){
		return position;
	}
}
