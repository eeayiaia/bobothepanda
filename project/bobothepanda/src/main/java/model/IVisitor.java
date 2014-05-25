package model;

public interface IVisitor {
	
	void visit(Terrain t);
	
	void visit(Character c);
	
	void visit(Key k);
	
	void visit(Door d);
	
	void visit(FixedEnemy f);

	void visit(Projectile p);
	
	void visit(MovingEnemy m);
	
	void visit(ShootingEnemy s);
}
