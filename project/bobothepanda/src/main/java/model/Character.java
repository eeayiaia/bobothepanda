package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


public class Character extends AbstractMovingObject{
	
	private CharacterState characterState;
	private Gravity gravity = new Gravity (0.01f);
	private float oldX;
	private final PropertyChangeSupport pcs;
	private Facing facing;
	private float yVelocity = 0.15f;
	private long lastTimedMoved; 
	private boolean keyPickedUp;
	private boolean onGround;

	/**
	 * Sets the starting position and assigns PropertyChangeSupport to this class
	 * @param position Starting position
	 */
	public Character(Position position, Size size){
		super(position, size);
		facing = Facing.RIGHT;
		pcs = new PropertyChangeSupport(this);	
	}
	
	/**
	 * The states that a character can have.
	 */
	public enum CharacterState {
		MOVING_RIGHT,
		MOVING_LEFT;
	}

	/**
	 * Get whether or not the key has been picked up
	 */
	public boolean getKeyPickedUp() {
		return keyPickedUp;
		
	}
	
	/**
	 * Makes Bobo move on to the next level
	 */
	public void levelComplete() {
		pcs.firePropertyChange("LOAD_LEVEL", null, null);
	}
	
	/**
	 * Get the velocity of the character
	 */
	public float getXVelocity() {
		if(characterState == CharacterState.MOVING_LEFT){
			return -0.25f;
		}else{
			return 0.25f;
		}
	}
	
	public float getYVelocity() {
		return yVelocity;
	}
	
	public void setYVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
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
			pcs.firePropertyChange(characterState.toString(), null, getPosition());
		
		}else{
			pcs.firePropertyChange(facing.toString(), null, getPosition());
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
     * Moves the character to the left.
     * 
     * TODO Implement acceleration 
     * 
     * @param delta
     */
	public void moveLeft(int delta){
		if(delta < 0) {
			throw new IllegalArgumentException(this.getClass().toString() + "   :Time since last update can not be negative");
		}else {
			characterState = CharacterState.MOVING_LEFT;
			this.oldX = this.getPosition().getX();
			setNewX(delta, getXVelocity());
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
		if(delta < 0) {
			throw new IllegalArgumentException(this.getClass().toString() + "   :Time since last update can not be negative");
		}else {
			characterState = CharacterState.MOVING_RIGHT;
			this.oldX = this.getPosition().getX();
			setNewX(delta, getXVelocity());
			facing = Facing.RIGHT;
			lastTimedMoved = System.currentTimeMillis();
		}
	}

	/**
	 * Changes the velocity in the y direction of the character. 
	 * Goes upwards, and then down, until the ground is reached
	 * Character needs to be on the ground to jump.
	 */	
	public void jump(int delta){
		if(delta < 0) {
			throw new IllegalArgumentException(this.getClass().toString() + "   :Time since last update can not be negative");
		}else {
			//needs to be on the ground to jump
			if(onGround){
				setYVelocity(-1.7f);
				pcs.firePropertyChange("jump", null, null);
				onGround = false;
			}
		}
	}
	
	/**
	 * Applies gravity to the character if it isn't touching the ground.
	 * @param delta 1000millis divided by the frame rate.
	 */
	public void applyGravity(int delta){
		if(delta < 0) {
			throw new IllegalArgumentException(this.getClass().toString() + "   :Time since last update can not be negative");
		}else {
			//Change velocity due to gravity
			setYVelocity(gravity.getNewVelocity(yVelocity, delta));
			final Position pos = getPosition();
			pos.setY(gravity.getNewYPosition(pos.getY(), getYVelocity(), delta));
		}
	}
	
	public void die() {
		pcs.firePropertyChange("RELOAD_LEVEL", null, null);
	}
	
	public void visit(FixedEnemy fixedEnemy){
		die();
	}
	
	public void visit(MovingEnemy movingEnemy){
		die();
	}
	
	public void visit(Projectile projectile){
		die();
	}
	
	public void visit(Terrain terrain){
		collisionWithFixedObject(terrain);
	}
	
	public void collisionWithFixedObject(AbstractFixedObject afo){
		onGround = true;
		
		final Position pos = getPosition();
		final float characterXPos = pos.getX();
		final float characterYPos = pos.getY();
		final Size size = getSize();
		final float characterWidth = size.getWidth();
		final float characterHeight = size.getHeight();
		
		final Position objPos = afo.getPosition();
		final float objectXPos = objPos.getX();
		final float objectYPos = objPos.getY();
		final float objectHeight = afo.getSize().getHeight();

		if((characterYPos <= objectYPos || characterYPos + characterWidth <= objectYPos) && getYVelocity() > 0){
			pos.setY(objectYPos - characterHeight);
			setYVelocity(0f);
			
		}else if((characterYPos <= objectYPos + objectHeight ||
				characterYPos + characterWidth <= objectYPos + objectHeight) && getYVelocity() < 0){
			
			pos.setY(objectYPos + characterHeight);
			setYVelocity(0f);
			
		}else if(characterXPos <= objectXPos || characterXPos + characterHeight >= objectXPos){
			pos.setX(oldX);
		}
	}
	
	public void visit(Key key){
		if(!keyPickedUp){
			pcs.firePropertyChange("key", null, null);
		}
		
		keyPickedUp = true;
	}

	public void visit(Character c) {}

	public void visit(Door door) {
		if(keyPickedUp){
			levelComplete();
		}else{
			collisionWithFixedObject(door);
		}
	}
	
	public void visit(ShootingEnemy s) {
		collisionWithFixedObject(s);
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
