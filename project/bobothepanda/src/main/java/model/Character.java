	package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

@SuppressWarnings("PMD")

public class Character extends AbstractMovingObject{
	
	private CharacterState characterState;
	private Gravity gravity = new Gravity (0.01f);
	private float oldX;
	private final PropertyChangeSupport pcs;
	private Facing facing;
	private float yVelocity = 0.15f;
	private float xVelocity = 0;
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
		pcs.firePropertyChange("loadLevel", null, null);
	}
	
	/**
	 * Get the velocity of the character
	 */
	public float getXVelocity() {
		return xVelocity;
	}
	
	/**
	 * The current direction the character is facing
	 * RIGH, LEFT
	 */
	public enum Facing{
		RIGHT, LEFT
	}
	
	public Facing getFacing(){
		return this.facing;
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
		characterState = CharacterState.MOVING_LEFT;
		xVelocity = -0.25f;
		this.oldX = this.getPosition().getX();
		getPosition().setX(getPosition().getX() + xVelocity * delta );
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
		xVelocity = 0.25f;
		this.oldX = this.getPosition().getX();
		getPosition().setX(getPosition().getX() + xVelocity * delta );
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
		if(yVelocity == 0f && onGround){
			yVelocity = -1.7f;
			onGround = false;
			pcs.firePropertyChange("jump", null, null);
		}


	}
	
	/**
	 * Applies gravity to the character if it isn't touching the ground.
	 * @param delta 1000millis divided by the frame rate.
	 */
	public void applyGravity(int delta){
		//Change velocity due to gravity
		yVelocity = gravity.getNewVelocity(yVelocity, delta);	
		getPosition().setY(gravity.getNewYPosition(getPosition().getY(), yVelocity, delta));
	}
	
	public void die() {
		pcs.firePropertyChange("die", null, null);
		pcs.firePropertyChange("reloadLevel", null, null);
	}
	
	public void visit(FixedEnemy fixedEnemy){
		die();
	}
	
	public void visit(MovingEnemy movingEnemy){
		die();
	}
	
	public void visit(Projectile projectile){
		System.out.println("hit in character");
		die();
	}
	
	public void visit(Terrain terrain){
		collisionWithFixedObject(terrain);
	}
	
	public void collisionWithFixedObject(AbstractFixedObject afo){
		onGround = true;
		
		final float characterXPos = getPosition().getX();
		final float characterYPos = getPosition().getY();
		final float characterWidth = getSize().getWidth();
		final float characterHeight = getSize().getHeight();
		
		final float objectXPos = afo.getPosition().getX();
		final float objectYPos = afo.getPosition().getY();
		final float objectHeight = afo.getSize().getHeight();

		if((characterYPos <= objectYPos || characterYPos + characterWidth <= objectYPos) && yVelocity > 0){
			getPosition().setY(objectYPos - characterHeight);
			yVelocity = 0f;
			
		}else if((characterYPos <= objectYPos + objectHeight ||
				characterYPos + characterWidth <= objectYPos + objectHeight) && yVelocity < 0){
			
			getPosition().setY(objectYPos + characterHeight);
			yVelocity = 0f;
			
		}else if(characterXPos <= objectXPos || characterXPos + characterHeight >= objectXPos){
			getPosition().setX(oldX);
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
			pcs.firePropertyChange("door", null, null);
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
