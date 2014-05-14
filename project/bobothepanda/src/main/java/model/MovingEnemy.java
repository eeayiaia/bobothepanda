package model;

public class MovingEnemy extends AbstractMovingObject {
	
	private int xDirection;
	private boolean dead;

	public MovingEnemy(Position position, Size size) {
		super(position, size);
		dead = false;
		xDirection = 1;
	}
	
	public void setNewX(int delta){
		final float nextPositionX = getPosition().getX() - xDirection * delta;
		setX(nextPositionX);
	}
	
	/**
	 * reverses the xDirection
	 */
	private void setReverseXDirection(){
		xDirection *= -1;
	}
	
	/**
	 * Returns the current direction the enemy is moving, in the x-axis
	 * @return -1 = moving left, 1 = moving left
	 */
	public int getXDirection(){
		return xDirection;
	}
	
	/**
	 * Returns the current status of the enemy
	 * @return true if dead, false if alive 
	 */
	public boolean isDead(){
		return dead;
	}
	
	public void visit(MovingEnemy movingEnemy){
		setReverseXDirection();
	}
	
	public void visit(Terrain terrain){
		setReverseXDirection();
	}
	
	public void visit(Character character){
		//If the character comes from above, object will remove itself
		if(getPosition().getY() > character.getPosition().getY()){
			dead = true;
			//TODO remove self 
		}
	}
}
