package model;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import utilities.IllegalMovingStateX;
import utilities.IllegalMovingStateY;
import utilities.Position;
import utilities.Size;

public class CharacterModel {
	
	private double velocity;
	private Position position;
	private boolean isDead;
	private PropertyChangeSupport pcs;
	private final int HEIGHT = 30;
	private final int WIDTH = 18;
	private final Size size;
	private Rectangle hitbox;
	private Facing facing;
	private CharacterState characterState;
	private long lastTimedMoved;
	private IllegalMovingStateX illegalMovingStateX;
	private IllegalMovingStateY illegalMovingStateY;

	/**
	 * Sets the starting position and assigns PropertyChangeSupport to this class
	 * @param position Starting position
	 */
	public CharacterModel(Position position){
		this.position = position;
		facing = Facing.RIGHT;
		isDead = false;
		pcs = new PropertyChangeSupport(this);	
		size = new Size(WIDTH,HEIGHT);
		hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		illegalMovingStateX = IllegalMovingStateX.NONE;
		illegalMovingStateY = IllegalMovingStateY.DOWN;
	}
	
	/**
	 * The current state of the Character
	 * MOVING_RIGHT, MOVING_LEFT, JUMP, RENDER
	 */
	public enum CharacterState{
		MOVING_RIGHT, MOVING_LEFT, JUMPING
	}
	
	/**
	 * The current direction the character is facing
	 * RIGH, LEFT
	 */
	public enum Facing{
		RIGHT, LEFT
	}
	
	/**
	 * Updates the current character state.
	 */
	public void update(){
		if(lastTimedMoved == System.currentTimeMillis()){
			pcs.firePropertyChange(characterState.toString(), null, position);
		
		}else{
			pcs.firePropertyChange(facing.toString(), null, position);
		}
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
		if(!illegalMovingStateX.equals(IllegalMovingStateX.LEFT)){
			position.setX(position.getX() - 0.3f*delta);
			characterState = CharacterState.MOVING_LEFT;
			hitbox.setLocation((int)Math.round(position.getX()), (int)Math.round(position.getY()));
			facing = Facing.LEFT;
			lastTimedMoved = System.currentTimeMillis();
		}
	}
	/**
	 * Moves the character to the right.
	 * 
	 * TODO Implement acceleration 
	 * 
	 * @param delta
	 */
	public void moveRight(int delta){
		if(!illegalMovingStateX.equals(IllegalMovingStateX.RIGHT)){
			position.setX(position.getX() + 0.3f*delta);
			characterState = CharacterState.MOVING_RIGHT;
			hitbox.setLocation((int)Math.round(position.getX()), (int)Math.round(position.getY()));
			facing = Facing.RIGHT;
			lastTimedMoved = System.currentTimeMillis();
		}
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
				pcs.firePropertyChange(CharacterState.JUMPING.toString(), null, position);
				
			}
			//when the ground is reached
			velocity_Y = 0;
			position.setY(position.getY() + velocity_Y * delta);
			pcs.firePropertyChange(CharacterState.JUMPING.toString(), null, position);
			//send property change
		}
		//TODO set bobos hitbox
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
	public Rectangle getHitbox(){
		return hitbox;
	}
	
	public void die() {
		isDead = true;
	}
		
	public void setIllegalMovingStateX(IllegalMovingStateX state){
		illegalMovingStateX = state;
	}
	public void setIllegalMovingStateY(IllegalMovingStateY state){
		illegalMovingStateY = state;
	}
	
	public void terrainCollision(Rectangle hitbox) {
		int bitmask = hitbox.outcode(this.hitbox.getX(),this.hitbox.getY());
		if(bitmask == Rectangle2D.OUT_LEFT){
			this.setIllegalMovingStateX(IllegalMovingStateX.RIGHT);
			this.setIllegalMovingStateY(IllegalMovingStateY.DOWN);
		} else if(bitmask == Rectangle2D.OUT_RIGHT){
			this.setIllegalMovingStateX(IllegalMovingStateX.LEFT);
			this.setIllegalMovingStateY(IllegalMovingStateY.DOWN);
		} else if(bitmask == Rectangle2D.OUT_BOTTOM){
			this.setIllegalMovingStateX(null);
			this.setIllegalMovingStateY(IllegalMovingStateY.UP);
		} else if(bitmask == Rectangle2D.OUT_TOP){
			this.setIllegalMovingStateX(null);
			this.setIllegalMovingStateY(IllegalMovingStateY.DOWN);
		}
	}
	
	public void lethalCollision(Rectangle hitbox) {

	}
	
	public void keyCollision(Rectangle hitbox) {
		
	}
}
