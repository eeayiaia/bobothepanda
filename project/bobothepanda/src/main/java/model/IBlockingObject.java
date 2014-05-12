package model;

public interface IBlockingObject {
	
	void doCollision(Projectile projectile);
	
	void doCollision(MovingLethalEnemy enemy);
	
}
