package model;

public class FixedEnemy extends AbstractFixedObject {
	
	public FixedEnemy(Position position, Size size) {
		super(position, size);
	}
	
	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
