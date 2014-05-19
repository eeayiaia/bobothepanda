package model;

/**
 * A class to calculates an objects velocity in Y, and it's Y position.
 * @author Sebastian
 *
 */

public class Gravity {
	private final float GRAVITY_CONSTANT;
	private final float TERMINAL_VELOCITY = 1f;
	
	public Gravity(float gravity){
		this.GRAVITY_CONSTANT = gravity;
	}
	
	public float getGravity(){
		return this.GRAVITY_CONSTANT;
	}
	
	/**
	 * Calculates the new yVelocity of the object
	 * 
	 * @param YVelocity The current yVelocity of the object
	 * @param delta The time difference
	 * @return The new Velocity of the object
	 */
	public float getNewVelocity(float yVelocity, int delta){
		//TODO add check, so that the maximum velocity is at certain speed
		//velocity formula
		final float nextYVelocity =  yVelocity+this.getGravity() * delta;
		if(nextYVelocity > TERMINAL_VELOCITY){
			return TERMINAL_VELOCITY;
		}else{
			return nextYVelocity;
		}
	}
	
	/**
	 * Calculates the new yPosition of the object. This method should 
	 * be called AFTER getNewVelocity has been called
	 * 
	 * @param yPosition The current yPosition of the object
	 * @param yVelocity	The current yVelocity of the object
	 * @param delta	The difference in time
	 * @return The new yPosition of the object
	 */
	public float getNewYPosition(float yPosition, float yVelocity, int delta){
		//position formula
		return (float) (yPosition + yVelocity * delta + 0.5 * this.getGravity() * delta * delta);
		
	}
}
