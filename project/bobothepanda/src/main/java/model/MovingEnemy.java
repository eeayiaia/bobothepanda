package model;

public class MovingEnemy extends AbstractMovingObject {
	
	private int xDirection = 1;
	private boolean dead;

	public MovingEnemy(Position position, Size size) {
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
	
	public void visit(MovingEnemy movingEnemy){
		setReverseXDirection();
	}
	
	public void visit(Terrain terrain){
		setReverseXDirection();
	}
	
	public void visit(Character character){
		if(getPosition().getY() > character.getPosition().getY()){
			dead = true;
			//TODO remove self 
		}
	}
}
