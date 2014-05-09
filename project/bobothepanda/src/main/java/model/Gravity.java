package model;

public class Gravity {
	private final int GRAVITY;

	public Gravity(int gravity){
		this.GRAVITY = gravity;
	}
	
	public int getGravity(){
		return this.GRAVITY;
	}
	
	/**
	 * Calculates the new yVelocity of the object
	 * 
	 * @param YVelocity The current yVelocity of the object
	 * @param delta The time difference
	 * @return The new Velocity of the object
	 */
	public float getNewVelocity(float yVelocity, int delta){
		//velocity formula
		return yVelocity + this.getGravity() * delta;
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
	public float getNewPosition(int yPosition, float yVelocity, int delta){
		//position formula
		return (float) (yPosition + yVelocity * delta + 0.5 * this.getGravity() * delta * delta);
		
	}
}
