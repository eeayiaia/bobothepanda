package model;

public class MovingEnemyRENAME extends AbstractMovingObject {
	
	private float velocity = 0.25f;
	private boolean dead;

	public MovingEnemyRENAME(Position position, Size size) {
		super(position, size);
		dead = false;
	}
	
	/**
	 * reverses the velocity
	 */
	private void setReverseVelocity(){
		velocity *= -1;
	}
	
	/**
	 * Returns the current direction the enemy is moving, in the x-axis
	 * @return negative = moving left, positive = moving right
	 */
	public float getVelocity(){
		return velocity;
	}
	
	/**
	 * Returns the current status of the enemy
	 * @return true if dead, false if alive 
	 */
	public boolean isDead(){
		return dead;
	}
	
	public void visit(MovingEnemyRENAME movingEnemy){
		setReverseVelocity();
	}
	
	public void visit(Terrain terrain){
		setReverseVelocity();
	}
	
	public void visit(FixedEnemy fixedEnemy){
		setReverseVelocity();
	}
	
	public void visit(Character character){
		//If the character comes from above, object will remove itself
		if(getPosition().getY() > character.getPosition().getY()){
			dead = true;
			//TODO remove self 
		}
	}
}
