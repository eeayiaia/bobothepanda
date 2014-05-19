package model;

/**
 * 
 * @author Oscar Muhr
 *
 */

public class Terrain extends AbstractFixedObject {

	public Terrain(Position position, Size size) {
		super(position, size);
	}

	public void accept(IVisitor visitor) {
		visitor.visit(this);
	}
}
