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
	 * @return The hitbox surrounding the character.
	 */
//	public Rectangle getHitbox() {
//		return new Rectangle((int)Math.round(position.getX()),(int)Math.round(position.getY()),
//				(int)Math.round(size.getWidth()), (int)Math.round(size.getHeight()));
//	}
	
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
	 * Checks if the character is on the ground
	 * @return true if on ground level.
	 */
//	public boolean onGround(){
//		final Position nextPosition = new Position(position.getX(),position.getY()+yVelocity);
//		final Rectangle collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPosition.getX()), 
//				(int)Math.round(nextPosition.getY()), (int)size.getWidth(), (int)size.getHeight()));
//		// if there is a collision the position remains the same and bobo stands still
//		if(collisionHitbox != null && (collision.getObjectType().getClass().equals(BlockingObject.class) || collision.getObjectType().getClass() == Door.class)){
//			return true;
//		} else{
//			return false;
//		}		
//	}
	
	/**
	 * Applies gravity to the character if it isn't touching the ground.
	 * @param delta 1000millis divided by the frame rate.
	 */
	public void applyGravity(int delta){
		//Change velocity due to gravity
		yVelocity = gravity.getNewVelocity(yVelocity, delta);
		
		getPosition().setY(gravity.getNewYPosition(getPosition().getY(), yVelocity, delta));
//		Position nextPosition;
//		// Set next Y due to gravity
////		float yAndYVelocity = gravity.getNewYPosition(position.getY(), yVelocity, delta);/*position.getY() + yVelocity;*/ 
////		if(yAndYVelocity >= 0){
////			nextPosition = new Position(position.getX(),yAndYVelocity);
////		}else{
////			nextPosition = new Position(position.getX(), position.getY());
////		}	
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
		pcs.firePropertyChange("key", null, null);
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

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}

//	public void setNewX(int delta) {
//		Rectangle collisionHitbox;
//		Position nextPosition;
//		
//		
//		if(characterState==CharacterState.MOVING_RIGHT) {
//			nextPosition = new Position(position.getX()+XVELOCITY*delta, position.getY());
//		} else {
//			float xMinusVelocity = position.getX()-XVELOCITY*delta;
//			if(xMinusVelocity>= 0){
//				nextPosition = new Position(xMinusVelocity, position.getY());
//			}else{
//				nextPosition = new Position(position.getX(), position.getY());
//			}
//		}
//		float nextPositionXValue = nextPosition.getX();
//		collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPositionXValue), 
//				(int)Math.round(nextPosition.getY()), WIDTH, HEIGHT));
//		float collisionHitboxXValue = 0;
//		if(collisionHitbox != null){
//			collisionHitboxXValue = (float) collisionHitbox.getX();
//		}
//		if(collisionHitbox != null) {	
//			if(collision.getObjectType().getClass() == BlockingObject.class || collision.getObjectType().getClass() == Door.class || collision.getObjectType().getClass() == Key.class) {
//				if(collision.getObjectType().getClass() == Key.class){
//					keyPickedUp = true;
//					//possible to go right through the key
//					position.setX(nextPositionXValue);
//				}else{
//					//should be ObjectType.OPEN_DOOR
//					if(collision.getObjectType().getClass() == Door.class && keyPickedUp){
//						pcs.firePropertyChange("loadLevel", null, null);
//					}
//					
//					if(characterState==CharacterState.MOVING_RIGHT) {
//						if((nextPositionXValue + WIDTH) >= collisionHitboxXValue) {
//							position.setX(collisionHitboxXValue - WIDTH);
//						}
//					}else if(characterState==CharacterState.MOVING_LEFT) {
//						double newX = collisionHitboxXValue + collisionHitbox.getWidth();
//						if(nextPositionXValue <= newX) {
//							position.setX((float)newX);
//						}
//					}
//				}
//			} else if(collision.getObjectType().getClass() == LethalEnemy.class) {
//				pcs.firePropertyChange("reloadLevel", null, null);
//			}
//		}
//		else {
//			position.setX(nextPositionXValue);
//		}
//	}
//	/**
//	 * Sets the next y value if the next position is valid
//	 * @param delta
//	 */
//	public void setNewY(int delta){
//		Rectangle collisionHitbox;
//		//Change velocity due to gravity
//		yVelocity = gravity.getNewVelocity(yVelocity, delta);
//		Position nextPosition;
//		// Set next Y due to gravity
//		float yAndYVelocity = gravity.getNewYPosition(position.getY(), yVelocity, delta);/*position.getY() + yVelocity;*/ 
//		if(yAndYVelocity >= 0){
//			nextPosition = new Position(position.getX(),yAndYVelocity);
//		}else{
//			nextPosition = new Position(position.getX(), position.getY());
//		}
//		
//		collisionHitbox = collision.collidedWith(new Rectangle((int)Math.round(nextPosition.getX()), 
//				(int)Math.round(nextPosition.getY()), WIDTH, HEIGHT));
//		// if there is a collision the position remains the same and bobo stands still
//		if(collisionHitbox != null){
//			if(collision.getObjectType().getClass() == Key.class){
//				keyPickedUp = true;
//				//possible to go right through the key
//				position.setY(nextPosition.getY());
//			}
//			if(collision.getObjectType().getClass() == BlockingObject.class || collision.getObjectType().getClass() == Door.class) {
//				if(yVelocity < 0) {
//					position.setY((float)(collisionHitbox.getY() + collisionHitbox.getHeight()));
//					yVelocity = 0;
//				} else if(yVelocity > 0) {
//					position.setY((float)(collisionHitbox.getY() - HEIGHT));
//				}
//			} else if(collision.getObjectType().getClass() == LethalEnemy.class) {
//				pcs.firePropertyChange("reloadLevel", null, null);
//			}
//		} else {
//			position.setY(nextPosition.getY());
//		}
//	}
	/**
	 * Changes the velocity. Makes sure that the velocity does not exceed a certain amount.
	 * @param delta
	 */
//	private void setYVelocity(int delta){
//		float gravity = 0.05f;
//		Float nextYVelocity = yVelocity + gravity * delta;
//		if(nextYVelocity > 15f){
//			yVelocity = 15f;
//		}else{
//			yVelocity = nextYVelocity;
//		}
//	}
}
