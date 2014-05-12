package model;

public class MovingLethalEnemy extends LethalEnemy {
	
	private float xDirection = 1f;

	public MovingLethalEnemy(Position position, Size size) {
		super(position, size);
		// TODO Auto-generated constructor stub
	}
	
	public void doCollision(Character character){
		//insert check if character comes from above
		
		character.die();
		
	}

	public void doCollision(IMapObject mapObject){
		//TODO 
		//if collision is in the x direction
		this.setXDirection();
	}
	
	public void setNewX(int delta){
		final float nextPositionX = getPosition().getX() * this.xDirection * delta;
		setX(nextPositionX);
	}
	
	private void setXDirection(){
		this.xDirection *= -1;
	}
	
	public float getXDirection(){
		return this.xDirection;
	}
}
