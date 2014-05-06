package model;

import java.awt.Rectangle;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@SuppressWarnings("PMD")

public class Character {
	
	
	private float yVelocity = 0.15f;
	private Position position;
	private PropertyChangeSupport pcs;
	private final static int HEIGHT = 30;
	private final static int WIDTH = 18;
	private final Size size;
	private Rectangle hitbox;
	private Facing facing;
	private CharacterState characterState;
	private long lastTimedMoved; 
	private Collision collision;

	/**
	 * Sets the starting position and assigns PropertyChangeSupport to this class
	 * @param position Starting position
	 */
	public Character(Position position, Collision collision){
		this.position = position;
		facing = Facing.RIGHT;
		characterState = CharacterState.IDLE;
		pcs = new PropertyChangeSupport(this);	
		size = new Size(WIDTH,HEIGHT);
		hitbox = new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
		this.collision = collision;
	}
	
	/**
	 * The current state of the Character
	 * MOVING_RIGHT, MOVING_LEFT, JUMPING, IDLE
	 */
	public enum CharacterState{
		MOVING_RIGHT, MOVING_LEFT, JUMPING, IDLE
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
		if(lastTimedMoved + 150 >= System.currentTimeMillis()){
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
			characterState = CharacterState.MOVING_LEFT;
			setNewX(delta);
			hitbox.setLocation((int)Math.round(position.getX()), (int)Math.round(position.getY()));
			facing = Facing.LEFT;
			lastTimedMoved = System.currentTimeMillis();
	}
	/**
	 * Moves the character to the right.
	 * 
	 * TODO Implement acceleration 
	 * 
	 * @param delta
	 */
	public void moveRight(int delta){
			characterState = CharacterState.MOVING_RIGHT;
			setNewX(delta);
			hitbox.setLocation((int)Math.round(position.getX()), (int)Math.round(position.getY()));
			facing = Facing.RIGHT;
			lastTimedMoved = System.currentTimeMillis();
	}

	/**
	 * Changes the velocity in the y direction of the character. 
	 * Goes upwards, and then down, until the ground is reached
	 * Character needs to be on the ground to jump.
	 */	
	public void jump(int delta){
		//needs to be on the ground to jump
		if(onGround()){
			
			yVelocity = -15f;
		}
	}
	
	/**
	 * Checks if the character is on the ground
	 * @return true if on ground level.
	 */
	public boolean onGround(){
		Position nextPosition = new Position(position.getX(),position.getY()+yVelocity);
		Rectangle collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPosition.getX()), 
				(int)Math.round(nextPosition.getY()), (int)size.getWidth(), (int)size.getHeight()));
		// if there is a collision the position remains the same and bobo stands still
		if(collisionHitbox != null && ((collision.getObjectType()==ObjectType.TERRAIN) || (collision.getObjectType()==ObjectType.DOOR))){
			return true;
		} else{
			return false;
		}		
	}
	
	/**
	 * Applies gravity to the character if it isn't touching the ground.
	 * @param delta 1000millis divided by the frame rate.
	 */
	public void applyGravity(int delta){
		//if(!onGround()){
			setNewY(delta);
		//}		
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
		//TODO: handle death
	}
	
	public void setNewX(int delta) {
		Rectangle collisionHitbox;
		Position nextPosition;
		final float VELOCITY = 0.25f;
		
		if(characterState==CharacterState.MOVING_RIGHT) {
			nextPosition = new Position(position.getX()+VELOCITY*delta, position.getY());
		} else {
			nextPosition = new Position(position.getX()-VELOCITY*delta, position.getY());
		}
		float nextPositionXValue = nextPosition.getX();
		collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPositionXValue), 
				(int)Math.round(nextPosition.getY()), WIDTH, HEIGHT));
		float collisionHitboxXValue = 0;
		if(collisionHitbox != null){
			collisionHitboxXValue = (float) collisionHitbox.getX();
		}
		if(collisionHitbox != null && ((collision.getObjectType()==ObjectType.TERRAIN) || (collision.getObjectType()==ObjectType.DOOR))){	
			
			if(collision.getObjectType() == ObjectType.DOOR){
				pcs.firePropertyChange("loadNewLevel", true, false);
			}
			
			if(characterState==CharacterState.MOVING_RIGHT) {
				if((nextPositionXValue + WIDTH) >= collisionHitboxXValue) {
					position.setX(collisionHitboxXValue - WIDTH);
				}
			}else if(characterState==CharacterState.MOVING_LEFT) {
				double newX = collisionHitboxXValue + collisionHitbox.getWidth();
				if(nextPositionXValue <= newX) {
					position.setX((float)newX);
				}
			}
		}
		else {
			position.setX(nextPositionXValue);
		}
	}
	/**
	 * Sets the next y value if the next position is valid
	 * @param delta
	 */
	public void setNewY(int delta){
		Rectangle collisionHitbox;
		//Change velocity due to gravity
		setYVelocity(delta);
		Position nextPosition;
		nextPosition = new Position(position.getX(),position.getY()+yVelocity);
		collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPosition.getX()), 
				(int)Math.round(nextPosition.getY()), WIDTH, HEIGHT));
		// if there is a collision the position remains the same and bobo stands still
		if(collisionHitbox != null && ((collision.getObjectType()==ObjectType.TERRAIN) || (collision.getObjectType()==ObjectType.DOOR))){
			if(yVelocity < 0) {
				position.setY((float)(collisionHitbox.getY() + collisionHitbox.getHeight()));
				yVelocity = 0;
			} else if(yVelocity > 0) {
				position.setY((float)(collisionHitbox.getY() - HEIGHT));
			}
		} else {
			position.setY(nextPosition.getY());
		}
	}
	/**
	 * Changes the velocity. Makes sure that the velocity does not exceed a certain amount.
	 * @param delta
	 */
	private void setYVelocity(int delta){
		float gravity = 0.05f;
		Float nextYVelocity = yVelocity + gravity * delta;
		if(nextYVelocity > 15f){
			yVelocity = 15f;
		}else{
			yVelocity = nextYVelocity;
		}
	}
}
