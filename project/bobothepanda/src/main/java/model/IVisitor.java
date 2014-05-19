package model;

public interface IVisitor {
	
	public void visit(Terrain t);
	
	public void visit(Character c);
	
	public void visit(Key k);
	
	public void visit(Door d);
	
	public void visit(FixedEnemy f);

	public void visit(Projectile p);
	
	public void visit(MovingEnemy m);
	
	public void visit(ShootingEnemy s);
}
