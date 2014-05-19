package model;

public class MovingEnemy extends AbstractMovingObject {
	
	private float velocity = 0.25f;
	private boolean dead;

	public MovingEnemy(Position position, Size size) {
		super(position, size);
		dead = false;
	}
	
	public void update(int delta){
		setNewX(delta, velocity);
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
	
	public void remove(){
		//TODO Remove self
	}
	
	public void visit(MovingEnemy movingEnemy){
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
		if(this.getPosition().getY() > character.getPosition().getY()){
			dead = true;
			remove();
		}
	}
	
	public void visit(Door d) {
		setReverseVelocity();
	}
	
	public void visit(ShootingEnemy s) {
		setReverseVelocity();
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
	
	public void visit(Key k) {}
	public void visit(Projectile p) {}
}
