package model;

public class Gravity {
	private final int GRAVITY;

	public Gravity(int gravity){
		this.GRAVITY = gravity;
	}
	
	public int getGravity(){
		return this.GRAVITY;
	}
	public float applyGravity(int YPosition, float YVelocity, int delta){
		//velocity formula
		final float DY = YVelocity + this.getGravity() * delta;
		//position formula
		return (float) (YPosition + DY * delta + 0.5 * this.getGravity() * delta * delta);
		
	}
}
