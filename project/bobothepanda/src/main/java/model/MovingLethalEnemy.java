package model;

public class MovingLethalEnemy extends FixedEnemy {
	
	private int xDirection = 1;
	private boolean dead;

	public MovingLethalEnemy(Position position, Size size) {
		super(position, size);
		dead = false;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * If the character comes from above, object will remove itself,
	 * else character dies.
	 */
	public void doCollision(Character character){
		//if character comes from above
		if(this.getPosition().getY() > character.getPosition().getY()){
			dead = true;
			//TODO remove self
			//property change or update call? Or anything at all? 
			//Should level.update always iterate the list for enemy.isdead() == remove from list?
		}else{
			character.die();
		}
		
		
	}
	
	/**
	 * 
	 * @param mapObject the object the MovingLethalObject has collided with
	 * if mapObject is blockableObject and the collision is in the x direction, the movement direction of the movingLethalEnemy object will change its direction.
	 *
	 */
	public void doCollision(IMapObject mapObject){
		//TODO 
		//if collision is in the x direction
		this.setReverseXDirection();
	}
	
	public void setNewX(int delta){
		final float nextPositionX = getPosition().getX() * this.xDirection * delta;
		setX(nextPositionX);
	}
	
	/**
	 * reverses the xDirection
	 */
	private void setReverseXDirection(){
		this.xDirection *= -1;
	}
	
	public int getXDirection(){
		return this.xDirection;
	}
	
	public boolean isDead(){
		return this.dead;
	}
}
